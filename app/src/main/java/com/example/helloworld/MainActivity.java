package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnJump = findViewById(R.id.btn_jump);
        EditText etName = findViewById(R.id.et_name);

        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("USER_NAME", name); // Intent传值
                startActivity(intent);
            }
        });
    }
}