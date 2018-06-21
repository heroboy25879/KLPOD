package com.hitachi.com.klpod.Utility;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Response;

public class FuncGetUserLogin extends AsyncTask<String,Void,String>{

    private Context context ;

    public FuncGetUserLogin(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            /*OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("pUser", strings[0])
                    .add("pPassword", strings[1])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[3]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();

            Log.d("KLTag","response ==> " + response.body().string());
            return  response.body().string();*/

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();

            Request request = builder
                    .url(strings[0] + "/" + strings[1] + "/" + strings[2])
                    .build();


            Response response = okHttpClient.newCall(request).execute();
            Log.d("KLTag","response ==> " + response);
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}