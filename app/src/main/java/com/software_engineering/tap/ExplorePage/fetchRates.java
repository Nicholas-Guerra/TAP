package com.software_engineering.tap.ExplorePage;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchRates extends AsyncTask<String,Void,String>
{
    String data="";
    String dataParsed;
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    @Override
    protected String doInBackground(String... params)
    {
        String inputLine;
        try
        {
            String StringUrl = params[0];
            URL url = new URL(StringUrl);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

            InputStreamReader inputStream = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(inputStream);
            StringBuilder strBuilder = new StringBuilder();

            //Set Methods and timeouts
            httpURLConnection.setRequestMethod(REQUEST_METHOD);
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);

            //connect to URL
            httpURLConnection.connect();

            while((inputLine = br.readLine())!= null)
            {
                strBuilder.append(inputLine);
            }

            //close InputStream and Buffered Reader
            br.close();
            inputStream.close();

            return strBuilder.toString();



        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(String.valueOf(aVoid));

    }
}
