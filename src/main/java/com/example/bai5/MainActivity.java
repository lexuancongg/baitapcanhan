package com.example.bai5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMaSV;
    private EditText editTextName;
    private EditText editTextLop;
    private Button btnSave;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        editTextMaSV = findViewById(R.id.masv);
        editTextName = findViewById(R.id.name);
        editTextLop = findViewById(R.id.lop);
        btnSave = findViewById(R.id.btnSave);
        textViewMessage = findViewById(R.id.textViewMessage);

        // Tạo đối tượng SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);

        // Xử lý sự kiện khi nhấn nút "Lưu"
        btnSave.setOnClickListener(view -> {
            String maSV = editTextMaSV.getText().toString().trim();
            String name = editTextName.getText().toString().trim();
            String lop = editTextLop.getText().toString().trim();

            if (maSV.isEmpty() || name.isEmpty() || lop.isEmpty()) {
                textViewMessage.setText("Vui lòng nhập đầy đủ thông tin");
                return;
            }

            // Lấy thông tin đã lưu trong SharedPreferences
            Set<String> savedInfos = sharedPreferences.getStringSet("infoSet", new HashSet<>());

            // Kiểm tra nếu thông tin đã tồn tại
            String newInfo = maSV + "_" + name + "_" + lop;
            if (savedInfos.contains(newInfo)) {
                textViewMessage.setText("Thông tin này đã tồn tại!");
            } else {
                // Nếu chưa tồn tại, thêm thông tin vào và lưu lại
                savedInfos.add(newInfo);
                sharedPreferences.edit().putStringSet("infoSet", savedInfos).apply();
                textViewMessage.setText("Lưu thông tin thành công!");

                // Xóa các trường sau khi lưu
                editTextMaSV.setText("");
                editTextName.setText("");
                editTextLop.setText("");
            }
        });
    }
}
