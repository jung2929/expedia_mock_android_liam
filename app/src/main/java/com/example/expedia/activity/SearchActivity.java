package com.example.expedia.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.expedia.R;
import com.example.expedia.SearchPersonDialog;

public class SearchActivity extends AppCompatActivity {

    private TextView hotelSearchName;
    private TextView hotelSearchDateStartMonth;
    private TextView hotelSearchDateArriveMonth;
    private TextView hotelSearchDateStartDay;
    private TextView hotelSearchDateArriveDay;
    private TextView hotelSearchPerson;
    private Button hotelSearchNameBtn;
    private Button hotelSearchStartDateBtn;
    private Button hotelSearchArriveDateBtn;
    private Button hotelSearchPersonBtn;
    private Button hotelSearchSearchBtn;
    private ImageButton backBtn;

    public int personCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        hotelSearchName = findViewById(R.id.hotel_search_destination_name);

        hotelSearchPerson = findViewById(R.id.hotel_search_member_num);
        hotelSearchNameBtn = findViewById(R.id.hotel_search_destination_btn);
        hotelSearchStartDateBtn = findViewById(R.id.hotel_search_calendar_start_btn);
        hotelSearchArriveDateBtn = findViewById(R.id.hotel_search_calendar_arrive_btn);
        hotelSearchPersonBtn = findViewById(R.id.hotel_search_person_btn);
        hotelSearchSearchBtn = findViewById(R.id.hotel_search_search_btn);
        hotelSearchDateStartMonth = findViewById(R.id.hotel_search_calendar_start_month);
        hotelSearchDateArriveMonth = findViewById(R.id.hotel_search_calendar_arrive_month);
        hotelSearchDateStartDay = findViewById(R.id.hotel_search_calendar_start_day);
        hotelSearchDateArriveDay = findViewById(R.id.hotel_search_calendar_arrive_day);
        backBtn = findViewById(R.id.hotel_search_exitButton);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        Intent intent = getIntent();

        if(intent.getStringExtra("hotel_name") != null){
            hotelSearchName.setText(intent.getStringExtra("hotel_name"));
            //hotelSearchDate.setText(intent.getStringExtra("hotel_date"));
            hotelSearchPerson.setText(intent.getStringExtra("hotel_person"));
        }
        hotelSearchNameBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
        hotelSearchStartDateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        hotelSearchDateStartMonth.setText(String.valueOf(month+1));
                        hotelSearchDateStartDay.setText(String.valueOf(day));
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(SearchActivity.this, listener, 2019, 4, 6);
                dialog.show();
            }
        });
        hotelSearchArriveDateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        hotelSearchDateArriveMonth.setText(String.valueOf(month+1));
                        hotelSearchDateArriveDay.setText(String.valueOf(day));
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(SearchActivity.this, listener, 2019, 4, 6);
                dialog.show();
            }
        });
        hotelSearchPersonBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SearchPersonDialog searchPersonDialog = new SearchPersonDialog(SearchActivity.this);
                searchPersonDialog.callFunction(hotelSearchPerson);
            }
        });
        hotelSearchSearchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SearchActivity.this, HotelSearchActivity.class);
                startActivity(intent);
            }
        });
    }

}