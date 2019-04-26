package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;

import com.example.myapplication.bean.Consume;
import com.example.myapplication.bean.student;
import com.example.myapplication.dao.Dao;
import com.example.myapplication.sqlite_person.DbManger_person;
import com.example.myapplication.sqlite_person.MySqliteHelper_person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class loginactivity extends Activity {
    private CalendarView calendarView;
    public MyAdapter myAdapter;
    private MySqliteHelper_person helper_person;
    private Button add=null;
    public int Year;
    public int Month;
    public int Day;
    public String zhanghao;
    public List<student> studentList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        studentList=new ArrayList<student>();
//        add=findViewById(R.id.login_btn);
        helper_person= DbManger_person.getIntance(this);
        ListView lv = (ListView) this.findViewById(R.id.listview);
        String user = getIntent().getStringExtra("user");
        String account = getIntent().getStringExtra("account");
        if(account==null)
        {
            zhanghao=user;
        }
        if(user==null)
        {
            zhanghao=account;
        }
        List<student> studentList = Dao.check(zhanghao, helper_person);
//        if(studentList.size()>0) {
//            Log.i("tag","------555555555555------");
//            myAdapter = new MyAdapter(loginactivity.this);
//            lv.setAdapter(myAdapter);
//        }
//        else{
//            Log.i("tag","------66666666666666------");
//        }
        Cursor cursor=Dao.check_1(zhanghao, helper_person);
        SimpleCursorAdapter simple=new SimpleCursorAdapter(this,R.layout.list_item,cursor,new String[]{"type","money"},new int[]{R.id.type_text,R.id.money_text});
        lv.setAdapter(simple);
//        calendarView = (CalendarView)findViewById(R.id.cal);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
//        {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month,
//                                            int dayOfMonth) {
//                month = month + 1;
//                Year = year;
//                Month = month;
//                Day = dayOfMonth;
//                String y = Integer.toString(Year);
//                String m = Integer.toString(Month);
//                String d = Integer.toString(Day);
//            }
//        });

    }
    class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;


        public MyAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return studentList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 从personList取出Person
            student p = studentList.get(position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_item, null);
                viewHolder.text_type = (TextView) convertView
                        .findViewById(R.id.type_text);
                viewHolder.text_money = (TextView) convertView
                        .findViewById(R.id.money_text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //向TextView中插入数据
            viewHolder.text_type.setText(p.getType());
            viewHolder.text_money.setText(p.getMoney());
            return convertView;
        }
    }
    public void onadd(View view)
    {
        Intent intent = new Intent();
        String y=Integer.toString(Year);
        String m=Integer.toString(Month);
        String d=Integer.toString(Day);
        String account=getIntent().getStringExtra("account");
        intent.setClass(loginactivity.this,addactivity.class);
        intent.putExtra("account",account);
        intent.putExtra("year",y);
        intent.putExtra("month",m);
        intent.putExtra("day",d);
        startActivity(intent);

    }
}
