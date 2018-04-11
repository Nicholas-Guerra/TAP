package com.software_engineering.tap.TransactionPage;

import android.Manifest;
import android.annotation.TargetApi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;



@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {


    private Context context;
    private DialogFragment_Authentication dialogFragment;


    // Constructor
    public FingerprintHandler(Context mContext, DialogFragment_Authentication dialogFragment) {
        context = mContext;
        this.dialogFragment = dialogFragment;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Log.e("Fingerprint error", "Fingerprint Authentication error.");
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Log.e("Fingerprint error", "Fingerprint Authentication help.");
    }


    @Override
    public void onAuthenticationFailed() {
        Log.e("Fingerprint error","Fingerprint Authentication failed.");
        Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        dialogFragment.setSuccess(true);
        dialogFragment.dismiss();
    }
}