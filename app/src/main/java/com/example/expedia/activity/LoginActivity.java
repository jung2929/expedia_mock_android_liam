package com.example.expedia.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.expedia.R;
import com.example.expedia.adapter.LoginViewPagerAdapter;

public class LoginActivity extends AppCompatActivity {

    private ImageButton exitButton;
    private EditText firstName, lastName, signUpEmail, signUpPw, loginEmail, loginPw;
    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        exitButton = findViewById(R.id.btnLoginExit);

        TabLayout tabLayout = findViewById(R.id.tabLayoutLogin);
        final ViewPager viewPager = findViewById(R.id.vpLogin);
        final LoginViewPagerAdapter loginViewPagerAdapter = new LoginViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(loginViewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){finish();}
        });
        /*signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = signUpEmail.getText().toString();
                String pw = signUpPw.getText().toString();
                String name = firstName.getText().toString() + " " + lastName.getText().toString();
                signUp("www.kaca5.com/expedia/user", email, pw, name);
            }
        });*/
    }
    /*public void signUp(String url, String email, String pw, String name){
        try{
            OkHttpClient client = new OkHttpClient();

            RequestBody postBody = new FormBody.Builder()
                    .add("Email", email)
                    .add("Pw", pw)
                    .add("Name", name)
                    .build();
            Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                    .url(url).post(postBody).build();
            Response response = client.newCall(request).execute();

            String message = response.body().string();
            Log.e("SignUp Message", message);
            Toast.makeText(LoginActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Error Message", e.toString());
            Toast.makeText(LoginActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }*/
    /*public void login(String url, String email, String pw){
        try{
            OkHttpClient client = new OkHttpClient();

            RequestBody postBody = new FormBody.Builder()
                    .add("Email", email)
                    .add("Pw", pw)
                    .build();
            Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                    .url(url).post(postBody).build();
            Response response = client.newCall(request).execute();

            String message = response.body().string();
            Log.e("Login Message", message);
            Toast.makeText(LoginActivity.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Error Message", e.toString());
            Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }*/
    /*private static class HttpAsyncTask extends AsyncTask<String, Void, String>{

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            String url = strings[0];
            try{
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                Log.e("SignUp Message", response.body().string());
                result = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
        }
    }*/
}