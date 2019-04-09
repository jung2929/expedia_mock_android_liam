package com.example.expedia.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expedia.R;
import com.example.expedia.activity.LoginActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Callback;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText loginEmail = view.findViewById(R.id.etLoginEmail);
        final EditText loginPw = view.findViewById(R.id.etLoginPw);
        final Button loginButton = view.findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final String email = loginEmail.getText().toString();
                final String pw = loginPw.getText().toString();

                new Thread(){
                    public void run(){
                        login("http://www.kaca5.com/expedia/user", email, pw);
                    }
                }.start();
            }
        });
    }
    public void login(String url, String email, String pw){
        OkHttpClient client = new OkHttpClient();

        RequestBody postBody = new FormBody.Builder()
                .add("Email", email)
                .add("Pw", pw)
                .build();
        Log.e("postbody content1 : ", ((FormBody) postBody).name(0) + " " + ((FormBody) postBody).value(0));
        Log.e("postbody content2 : ", ((FormBody) postBody).name(1) + " " + ((FormBody) postBody).value(1));
        Request request = new Request.Builder().url(url).post(postBody).build();
        client.newCall(request).enqueue(loginCallback);
    }
    private Callback loginCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("Error", "Message : " + e.getMessage());
        }

        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            final String responseData = response.body().string();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(responseData.contains("500")){
                        Toast.makeText(getContext(), "빈 칸을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else if(responseData.contains("501")){
                        Toast.makeText(getContext(), "잘못된 이메일 형식입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else if(responseData.contains("503")){
                        Toast.makeText(getContext(), "존재하지 않는 회원입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else if(responseData.contains("100")){
                        Toast.makeText(getContext(), "로그인에 성공하였습니다!", Toast.LENGTH_SHORT).show();
                    }
                }
            },0);
            Log.e("Success", "responseData : " + responseData);
        }
    };
}
