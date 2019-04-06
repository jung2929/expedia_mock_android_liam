package com.example.expedia;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchPersonDialog {
    private Context context;

    public SearchPersonDialog(Context context){
        this.context = context;
    }
    public void callFunction(final TextView hotelSearchPerson){
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.search_person_dlg);

        dlg.show();

        final ImageButton adultMinusBtn = dlg.findViewById(R.id.person_adult_minus_btn);
        final ImageButton adultPlusBtn = dlg.findViewById(R.id.person_adult_plus_btn);
        final ImageButton childMinusBtn = dlg.findViewById(R.id.person_child_minus_btn);
        final ImageButton childPlusBtn = dlg.findViewById(R.id.person_child_plus_btn);
        final Button completeBtn = dlg.findViewById(R.id.dialog_complete_btn);
        final TextView adultNum = dlg.findViewById(R.id.person_adult_n);
        final TextView childNum = dlg.findViewById(R.id.person_child_n);
        final TextView childSelectTx =dlg.findViewById(R.id.person_child_age_select);

        String[] childAge = new String[18];
        childAge[0] = "만 1세 미만";
        for(int i=1;i<childAge.length;i++){
            childAge[i] = "만 " + String.valueOf(i) + "세";
        }
        final Spinner[] spinners = new Spinner[6];

        spinners[0] = dlg.findViewById(R.id.spinner_child1);
        spinners[1] = dlg.findViewById(R.id.spinner_child2);
        spinners[2] = dlg.findViewById(R.id.spinner_child3);
        spinners[3] = dlg.findViewById(R.id.spinner_child4);
        spinners[4] = dlg.findViewById(R.id.spinner_child5);
        spinners[5] = dlg.findViewById(R.id.spinner_child6);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>
                (context, R.layout.child_spinner_item, childAge);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        for(int i=0;i<6;i++){
            spinners[i].setAdapter(spinnerAdapter);
        }

        SharedPreferences sf = context.getSharedPreferences("person_date",Context.MODE_PRIVATE);
        Log.e("sf", String.valueOf(sf.getInt("adultNumber",1)));
        adultNum.setText(String.valueOf(sf.getInt("adultNumber", 1)));
        childNum.setText(String.valueOf(sf.getInt("childNumber", 0)));

        adultMinusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(Integer.parseInt(adultNum.getText().toString()) > 1){
                    int adultNumber = Integer.parseInt(adultNum.getText().toString());
                    adultNumber--;
                    adultNum.setText(String.valueOf(adultNumber));
                }
            }
        });
        adultPlusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int adultNumber = Integer.parseInt(adultNum.getText().toString());
                adultNumber++;
                adultNum.setText(String.valueOf(adultNumber));
            }
        });
        childMinusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int childNumber = Integer.parseInt(childNum.getText().toString());
                if(childNumber > 0){
                    childNumber--;
                    childNum.setText(String.valueOf(childNumber));
                    spinners[childNumber].setVisibility(View.GONE);
                }
                if(childNumber==0) {
                    childSelectTx.setVisibility(View.GONE);
                }
            }
        });
        childPlusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int childNumber = Integer.parseInt(childNum.getText().toString());
                if(childNumber < 6){
                    childNumber++;
                    childNum.setText(String.valueOf(childNumber));
                    spinners[childNumber-1].setVisibility(View.VISIBLE);
                }
                if(childNumber==1){
                    childSelectTx.setVisibility(View.VISIBLE);
                }
            }
        });
        completeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int adultNumber = Integer.parseInt(adultNum.getText().toString());
                int childNumber = Integer.parseInt(childNum.getText().toString());
                int totalNumber = adultNumber + childNumber;

                SharedPreferences sf = context.getSharedPreferences("person_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editior = sf.edit();
                editior.putInt("adultNumber", adultNumber);
                editior.putInt("childNumber", childNumber);
                editior.apply();

                hotelSearchPerson.setText(String.valueOf(totalNumber));
                dlg.dismiss();
            }
        });

    }

}