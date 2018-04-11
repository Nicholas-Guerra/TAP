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

public class receiveNFC extends AsyncTask<Void, Void, Void> {

    private Activity activity;


    public receiveNFC(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void  doInBackground(Void... voids) {
        doStuff();
        return null;
    }


    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
    }


    private void doStuff(){

    }


}


