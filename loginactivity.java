package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import android.widget.Toolbar;

import com.example.myapplication.bean.Consume;
import com.example.myapplication.bean.student;
import com.example.myapplication.dao.Dao;
import com.example.myapplication.sqlite_person.DbManger_person;
import com.example.myapplication.sqlite_person.MySqliteHelper_person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class loginactivity extends Activity {
    private CalendarView calendarView;
    private MySqliteHelper_person helper_person;
    private Button add=null;
    public int Year;
    public int Month;
    public int Day;
    SQLiteDatabase db;
    MyAdapter myAdapter;
    public String zhanghao;
    public List<student> studentList;;
    public Cursor cursor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        studentList=new ArrayList<student>();
        calendarView = (CalendarView)findViewById(R.id.cal);
        add=findViewById(R.id.login_btn);
        helper_person= DbManger_person.getIntance(this);
        ListView lv = (ListView) findViewById(R.id.listview);
        db = helper_person.getWritableDatabase();
        Query();
        myAdapter = new MyAdapter(this);
        // 向listview中添加Adapter
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                student  s = studentList.get(position);
                Toast.makeText(loginactivity.this,
                        s.getMoney(),Toast.LENGTH_SHORT).show();
            }
        });


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


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                month = month + 1;
                Year = year;
                Month = month;
                Day = dayOfMonth;
                String y = Integer.toString(Year);
                String m = Integer.toString(Month);
                String d = Integer.toString(Day);
                Toast.makeText(loginactivity.this,
                        "日期是"+year+"年"+month+"月"+dayOfMonth+"日", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void onadd(View view)
    {
        Intent intent = new Intent();
        String y=Integer.toString(Year);
        String m=Integer.toString(Month);
        String d=Integer.toString(Day);
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
        intent.setClass(loginactivity.this,addactivity.class);
        intent.putExtra("account",zhanghao);
        intent.putExtra("year",y);
        intent.putExtra("month",m);
        intent.putExtra("day",d);
        startActivity(intent);
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
                viewHolder.type_text = (TextView) convertView
                        .findViewById(R.id.type_text);
                viewHolder.text_money = (TextView) convertView
                        .findViewById(R.id.text_money);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //向TextView中插入数据
            viewHolder.type_text.setText(p.getType()+"");
            viewHolder.text_money.setText(p.getMoney()+"");

            return convertView;
        }
    }
    public void Query() {
        Cursor cursor = db.query("student", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String account = cursor.getString(1);
            String money = cursor.getString(2);
            String type = cursor.getString(3);
            String year = cursor.getString(4);
            String month = cursor.getString(5);
            String day = cursor.getString(6);
            student person = new student(_id, account, money, type,year,month,day);
            studentList.add(person);
        }
    }
    class ViewHolder {
        private TextView type_text;
        private TextView text_money;
    }
}
