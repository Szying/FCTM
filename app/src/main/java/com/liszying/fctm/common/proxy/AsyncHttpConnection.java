package com.liszying.fctm.common.proxy;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpConnection extends AsyncTask<Void, Void, String> {
    IHttpCompleted callback;
    String action;
    String type;
    String data1;
    String data2;
    String URLMode;

    public AsyncHttpConnection(IHttpCompleted callback, String action, String type, String data1, String data2,String URLMode) {
        this.callback = callback;
        this.action = action;
        this.type = type;
        this.data1 = data1;
        this.data2 = data2;
        this.URLMode=URLMode;
    }

    @Override
    protected void onPostExecute(String s) {
        callback.doNotify(s);
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = "";
        try {
             //URL url = new URL("http://192.168.2.102:8080/FCTM/"+URLMode+"?action=" + action +
            URL url = new URL("http://100.64.89.212:8080/FCTM/" + URLMode + "?action=" + action +
                        "&type=" + type +
                        "&data1=" + data1 +
                        "&data2=" + data2);
            Log.e("TEST", url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result = line; // 最後一行才要的
            }
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }
}

