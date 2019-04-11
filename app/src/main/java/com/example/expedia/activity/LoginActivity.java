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
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}