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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Callback;

public class SignUpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_signup, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText firstName = view.findViewById(R.id.etFirstName);
        final EditText lastName = view.findViewById(R.id.etLastName);
        final EditText signUpEmail = view.findViewById(R.id.etSignUpEmail);
        final EditText signUpPw = view.findViewById(R.id.etSignUpPw);
        final Button signUpButton = view.findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new Thread(){
                    String email = signUpEmail.getText().toString();
                    String pw = signUpPw.getText().toString();
                    String name = firstName.getText().toString() + " " + lastName.getText().toString();
                    public void run(){
                        signUp("http://www.kaca5.com/expedia/user", email, pw, name);
                    }
                }.start();
            }
        });
    }
    public void signUp(String url, String email, String pw, String name){
        OkHttpClient client = new OkHttpClient();
        JSONObject loginInput = new JSONObject();
        try {
            loginInput.put("Email", email).put("Pw", pw).put("Name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody postBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                loginInput.toString());
        Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                .url(url).post(postBody).build();

        client.newCall(request).enqueue(signUpCallback);

    }
    private Callback signUpCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("Error", "Message : " + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String responseData = response.body().string();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable(){
                @Override
                public void run(){
                    if(responseData.contains("500")){
                        Toast.makeText(getContext(), "빈 칸을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else if(responseData.contains("501")){
                        Toast.makeText(getContext(), "잘못된 이메일 형식입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else if(responseData.contains("502")){
                        Toast.makeText(getContext(), "이미 존재하는 회원입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else if(responseData.contains("100")){
                        Toast.makeText(getContext(), "회원가입에 성공하였습니다!", Toast.LENGTH_SHORT).show();
                    }
                }
            },0);
            Log.e("Success", "responseData : " + responseData);
        }
    };
}
