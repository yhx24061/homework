package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> recordList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnJump = findViewById(R.id.btn_jump);
        EditText etName = findViewById(R.id.et_name);
        ListView listView = findViewById(R.id.list_view);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recordList);
        listView.setAdapter(adapter);

        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("USER_NAME", name);
                startActivityForResult(intent, 1); // 等待回传
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            String name = data.getStringExtra("RETURN_NAME");
            String result = data.getStringExtra("RETURN_RESULT");
            recordList.add(name + " 的信用评估结果：" + result);
            adapter.notifyDataSetChanged(); // 刷新列表
        }
    }
}