package com.example.expedia.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expedia.R;
import com.example.expedia.activity.LoginActivity;
import com.example.expedia.adapter.HotelSaleRecyclerAdapter;
import com.example.expedia.entities.HotelSaleData;
import com.example.expedia.entities.HotelSaleRecyclerViewItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
    private EditText signUpEmail, signUpPw, firstName, lastName;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_signup, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName = view.findViewById(R.id.etFirstName);
        lastName = view.findViewById(R.id.etLastName);
        signUpEmail = view.findViewById(R.id.etSignUpEmail);
        signUpPw = view.findViewById(R.id.etSignUpPw);
        final Button signUpButton = view.findViewById(R.id.btnSignUp);
        viewPager = view.findViewById(R.id.vpLogin);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new Thread(){
                    public void run(){
                        SignUpAsyncTask signUpAsyncTask = new SignUpAsyncTask();
                        signUpAsyncTask.execute();
                    }
                }.start();
            }
        });
    }
    public class SignUpAsyncTask extends AsyncTask<String, Void, Response> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Response doInBackground(String... strings) {
            String url = "http://www.kaca5.com/expedia/user";

            String email = signUpEmail.getText().toString();
            String pw = signUpPw.getText().toString();
            String name = firstName.getText().toString() + " " + lastName.getText().toString();

            JSONObject signUpInput = new JSONObject();
            try {
                signUpInput.put("Email", email).put("Pw", pw).put("Name", name);
                RequestBody postBody = RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        String.valueOf(signUpInput));
                Request request = new Request.Builder().url(url).post(postBody).build();
                Response response = client.newCall(request).execute();
                return response;
            }
            catch (IOException e) {
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Response response){
            super.onPostExecute(response);
            String responseData = null;
            try {
                responseData = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (responseData.contains("500")) {
                Toast.makeText(getContext(), "빈 칸을 입력해주세요.", Toast.LENGTH_SHORT).show();
            } else if (responseData.contains("501")) {
                Toast.makeText(getContext(), "잘못된 이메일 형식입니다.", Toast.LENGTH_SHORT).show();
            } else if (responseData.contains("502")) {
                Toast.makeText(getContext(), "이미 존재하는 회원입니다.", Toast.LENGTH_SHORT).show();
            } else if (responseData.contains("Uno")) {
                Toast.makeText(getContext(), "회원가입에 성공하였습니다!", Toast.LENGTH_SHORT).show();
                signUpEmail.setText("");
                signUpPw.setText("");
                lastName.setText("");
                firstName.setText("");
                getActivity().finish();
            }
        }
    }
}
