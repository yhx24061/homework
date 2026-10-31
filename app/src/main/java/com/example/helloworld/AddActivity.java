package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CheckBox;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String name = getIntent().getStringExtra("USER_NAME");
        TextView tvReceive = findViewById(R.id.tv_receive);
        tvReceive.setText("收到姓名：" + name);

        Spinner spinner = findViewById(R.id.spinner_job);
        String[] jobs = {"公务员", "企业员工", "自由职业", "其他"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btnSave = findViewById(R.id.btn_save);
        RadioGroup rgType = findViewById(R.id.rg_type);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.获取基础信用状态
                int baseScore = (rgType.getCheckedRadioButtonId() == R.id.rb_good) ? 700 : 500;

                //2.根据职业加分
                String job = spinner.getSelectedItem().toString();
                if (job.equals("公务员")) {
                    baseScore += 50;
                } else if (job.equals("企业员工")) {
                    baseScore += 20;
                }

                //3.根据是否有房产加分
                CheckBox cbHouse = findViewById(R.id.cb_house);
                if (cbHouse.isChecked()) {
                    baseScore += 30;
                }

                //3.限制最高分
                if (baseScore > 800) baseScore = 800;

                //4.生成最终的评估结果
                String finalResult = baseScore + " 分";

                Intent intent = new Intent();
                intent.putExtra("RETURN_NAME", name);
                intent.putExtra("RETURN_RESULT", finalResult); // 回传算出来的分数
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}