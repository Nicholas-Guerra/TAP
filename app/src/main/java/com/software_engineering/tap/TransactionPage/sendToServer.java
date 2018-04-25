package com.software_engineering.tap.TransactionPage;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.software_engineering.tap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class sendToServer extends AsyncTask<Void, Void, JSONObject> {

    private Context context;
    private boolean progressDialog;
    private ProgressDialog pDialog;
    private String progressMessage;
    private JSONObject jsonMessage;


    public sendToServer(Context context, boolean progressDialog, String progressMessage, JSONObject jsonMessage) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.progressMessage = progressMessage;
        this.jsonMessage = jsonMessage;

        if(progressDialog)
            pDialog = new ProgressDialog(context);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(progressDialog) {
            pDialog.setMessage(progressMessage);
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(2);
            pDialog.show();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        return connect_and_send(jsonMessage);
    }


    @Override
    protected void onPostExecute(JSONObject receivedJSON) {
        super.onPostExecute(receivedJSON);
        if(progressDialog)
            pDialog.dismiss();
    }


    private JSONObject connect_and_send(JSONObject jsonMessage) {
        JSONObject receivedJSON;

        try {
            InputStream caInput = context.getResources().openRawResource(R.raw.truststore);
            KeyStore keyStore = KeyStore.getInstance("BKS");
            keyStore.load(caInput, "123456".toCharArray());

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
            TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, wrappedTrustManagers, null);

            InetAddress addr = InetAddress.getByName("jaredrattray.com");
            SSLSocket socket = (SSLSocket) sslContext.getSocketFactory().createSocket(addr, 9099);
            //SSLSocket socket = (SSLSocket) sslContext.getSocketFactory().createSocket("localhost", 9099);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // printWriter from socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(jsonMessage.toString());
            receivedJSON = new JSONObject(in.readLine());


            in.close();
            out.close();
            socket.close();


        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | JSONException e) {
            e.printStackTrace();
            receivedJSON = new JSONObject();
            try {
                receivedJSON.put("Error", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }

        return receivedJSON;
    }

    private TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
        return new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return originalTrustManager.getAcceptedIssuers();
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0) {
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkClientTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
                            Log.w("checkClientTrusted", e.toString());
                        }
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0) {
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkServerTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
                            Log.w("checkServerTrusted", e.toString());
                        }
                    }
                }
        };
    }


}

