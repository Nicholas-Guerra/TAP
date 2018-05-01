package com.software_engineering.tap.AccountPage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.software_engineering.tap.AccountPage.AppDatabase;
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

public class Load_Transactions extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ProgressDialog pDialog;




    public Load_Transactions(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog.setMessage("Clearing Data");
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(2);
        pDialog.show();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        AppDatabase.getInstance(context).userDao().deleteALL();
        AppDatabase.getInstance(context).transactionDao().deleteALL();
        AppDatabase.getInstance(context).transaction_notificationDao().deleteALL();

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        pDialog.dismiss();
    }



}


