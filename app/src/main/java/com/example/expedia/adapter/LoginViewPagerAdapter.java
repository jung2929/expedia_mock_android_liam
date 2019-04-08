package com.example.expedia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.expedia.fragment.LoginFragment;
import com.example.expedia.fragment.SignUpFragment;

public class LoginViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public LoginViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginFragment loginTab = new LoginFragment();
                return loginTab;
            case 1:
                SignUpFragment signUpTab = new SignUpFragment();
                return signUpTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}