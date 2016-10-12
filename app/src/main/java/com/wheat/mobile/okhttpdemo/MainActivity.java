package com.wheat.mobile.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String URL="http://az.newoa.cn/biso/azDwTdUser/list.json";
    private static final String TAG=MainActivity.class.getSimpleName();

    private Button btLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLoad=(Button)findViewById(R.id.bt_load);
        btLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData(){

        OkHttpClient mOkHttpClient=new OkHttpClient();

        RequestBody formBody=new FormEncodingBuilder()
                .add("pageIndex","0")
                .add("pageSize","10")
                .add("employeeCd","KCNV984")
                .build();
        Request request=new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();

        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i(TAG,"request fail");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i(TAG,response.body().string());
            }
        });
    }
}
