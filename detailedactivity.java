package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class detailedactivity extends AppCompatActivity {
    private EditText detailed_type;
    private EditText detailed_money;
    private EditText detailed_data;
    private String type;
    private String money;
    private String year;
    private String month;
    private String day;
    private String data;
    private int detailed_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        detailed_type=findViewById(R.id.detailed_type);
        detailed_money=findViewById(R.id.detailed_money);
        detailed_data=findViewById(R.id.detailed_data);
        type=getIntent().getStringExtra("detailed_type");
        money=getIntent().getStringExtra("detailed_money");
        year=getIntent().getStringExtra("year");
        month=getIntent().getStringExtra("month");
        day=getIntent().getStringExtra("day");
        detailed_id=getIntent().getIntExtra("detailed_id",1);
        data=year+"年"+month+"月"+day+"日";
        detailed_type.setText(type);
        detailed_money.setText(money);
        detailed_data.setText(data);

    }
}
