package com.software_engineering.tap.TransactionPage;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.R;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;


public class DialogFragment_Authentication extends DialogFragment implements View.OnClickListener{

    private KeyStore keyStore;
    // Variable used for storing the key in the Android Keystore container
    private static final String KEY_NAME = "androidHive";
    private Cipher cipher;
    private ImageView close;
    private boolean success = false;
    private View rootView;
    private TextView usePinText;
    private EditText input;

    private Context context;


    public DialogFragment_Authentication(){
    }


    @TargetApi(Build.VERSION_CODES.M)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_fragment_authentication, container, false);

        close = rootView.findViewById(R.id.close_button);
        close.setOnClickListener(this);

        usePinText = rootView.findViewById(R.id.use_pin);
        usePinText.setOnClickListener(this);

        context = getContext();

        setCancelable(false);

        final KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);
        final FingerprintManager fingerprintManager = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);

        if(!fingerprintManager.isHardwareDetected()){
            Log.e("Error", "Your Device does not have a Fingerprint Sensor");
            usePinLayout();
        }else if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Log.e("Error", "Fingerprint authentication permission not enabled");
            usePinLayout();
        }else if (!fingerprintManager.hasEnrolledFingerprints()) {
            Log.e("Error", "Register at least one fingerprint in Settings");
            usePinLayout();
        }else if (!keyguardManager.isKeyguardSecure()) {
            Log.e("Error", "Lock screen security not enabled in Settings");
            usePinLayout();
        }else{
            generateKey();
            if (cipherInit()) {
                try {
                    final boolean[] fingerprint = new boolean[1];
                    Thread thread = new Thread(new Runnable() {
                        public void run() {
                            fingerprint[0] = AppDatabase.getInstance(getContext()).userDao().getUser().useFingerprint;
                        }
                    });
                    thread.start();
                    thread.join();
                    if (fingerprint[0]) {
                        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                        FingerprintHandler helper = new FingerprintHandler(context, DialogFragment_Authentication.this);
                        helper.startAuth(fingerprintManager, cryptoObject);
                    } else{
                        usePinLayout();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }



        return rootView;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public boolean getSuccess(){
        return this.success;
    }

    private void usePinLayout(){

        RelativeLayout fingerprintLayout = rootView.findViewById(R.id.fingerprint_layout);
        fingerprintLayout.setVisibility(View.GONE);
        RelativeLayout pinLayout = rootView.findViewById(R.id.pin_layout);
        pinLayout.setVisibility(View.VISIBLE);

        input = rootView.findViewById(R.id.pin_edit_text);


        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    try {
                        final boolean[] pin = new boolean[1];
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                if(AppDatabase.getInstance(context).userDao().getUser().pin == Integer.parseInt(input.getText().toString())){
                                    pin[0] = true;
                                } else{
                                    pin[0] = false;
                                }

                            }
                        });

                        thread.start();
                        thread.join();

                        if (pin[0]) {
                            success = true;
                            dismiss();
                        } else{
                            input.setError("Incorrect pin");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    handled = true;
                }
                return handled;
            }
        });

    }


    @Override
    public void onClick(View v){
        if(v == close){
            dismiss();
        } else if(v == usePinText){
            usePinLayout();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }


        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }


        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }
}



