package com.example.helloworld;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String name = getIntent().getStringExtra("USER_NAME");
        TextView tvReceive = findViewById(R.id.tv_receive);
        tvReceive.setText("收到姓名：" + name);

        Spinner spinner = findViewById(R.id.spinner_job);
        String[] jobs = {"公务员", "企业员工", "自由职业"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}