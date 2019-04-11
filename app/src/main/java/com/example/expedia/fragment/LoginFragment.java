package com.example.expedia.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expedia.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Callback;

public class LoginFragment extends Fragment {

    private String token, name;
    private LinearLayout beforeLogin, afterLogin;
    private Button logoutButton;
    private TextView userName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beforeLogin = view.findViewById(R.id.layoutBeforeLogin);
        afterLogin = view.findViewById(R.id.layoutAfterLogin);
        userName = view.findViewById(R.id.tvMemberName);
        final EditText loginEmail = view.findViewById(R.id.etLoginEmail);
        final EditText loginPw = view.findViewById(R.id.etLoginPw);
        final Button loginButton = view.findViewById(R.id.btnLogin);
        logoutButton = view.findViewById(R.id.btnLogout);

        Context context = getContext();

        final SharedPreferences sf = context.getSharedPreferences("loginStatus",Context.MODE_PRIVATE);
        token = sf.getString("token","");
        name = sf.getString("name", "");
        Log.e("tokencheck : ", token);

        if(!token.equals("")){
            TokenAsyncTask tokenAsyncTask = new TokenAsyncTask();
            tokenAsyncTask.execute();
        }

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final String email = loginEmail.getText().toString();
                final String pw = loginPw.getText().toString();

                new Thread(){
                    public void run(){
                        try {
                            login("http://www.kaca5.com/expedia/login", email, pw);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("token", "");
                editor.putString("name", "");
                editor.apply();

                afterLogin.setVisibility(View.GONE);
                beforeLogin.setVisibility(View.VISIBLE);

                Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void login(String url, String email, String pw) throws JSONException {
        OkHttpClient client = new OkHttpClient();

        JSONObject loginInput = new JSONObject();
        loginInput.put("Email", email).put("Pw", pw);
        RequestBody postBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                String.valueOf(loginInput));
        Log.e("loginInput : ", loginInput.toString());
        Log.e("postBody : ", postBody.toString());

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
                    else if(responseData.contains("508")){
                        Toast.makeText(getContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else if(responseData.contains("100")){
                        JsonParser jsonParser = new JsonParser();
                        JsonElement responseJson = jsonParser.parse(responseData);

                        token = responseJson.getAsJsonObject().get("token").getAsJsonObject().get("jwt").getAsString();
                        name = responseJson.getAsJsonObject().get("result").getAsJsonArray().get(0).getAsJsonObject().get("Name").getAsString();
                        Log.e("token : ", token);

                        SharedPreferences sf = getContext().getSharedPreferences("loginStatus", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sf.edit();
                        editor.putString("token", token);
                        editor.putString("name", name);
                        editor.apply();

                        Toast.makeText(getContext(), "로그인에 성공하였습니다!", Toast.LENGTH_SHORT).show();

                        TokenAsyncTask tokenAsyncTask = new TokenAsyncTask();
                        tokenAsyncTask.execute();
                    }
                }
            },0);
            Log.e("Success", "responseData : " + responseData);
        }
    };

    public class TokenAsyncTask extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... strings) {
            String url = "http://www.kaca5.com/expedia/token";

            Request request = new Request.Builder().addHeader("x-access-token", token)
                    .addHeader("Content-Type", "application/json")
                    .url(url).build();
            Log.e("x-access-token : ", request.header("x-access-token"));
            Log.e("Content-Type : ", request.header("Content-Type"));
            try {
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();

                JsonParser jsonParser = new JsonParser();
                JsonElement responseJson = jsonParser.parse(responseData);

                String code = responseJson.getAsJsonObject().get("code").toString();
                Log.e("Token Check Code : ", code);
                return code;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (result.equals("100")){
                userName.setText(name + "님 환영합니다!");
                beforeLogin.setVisibility(View.GONE);
                afterLogin.setVisibility(View.VISIBLE);
            }
            else{
                afterLogin.setVisibility(View.GONE);
                beforeLogin.setVisibility(View.VISIBLE);
            }
        }
    }
}
