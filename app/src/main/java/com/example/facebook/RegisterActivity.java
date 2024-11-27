package com.example.facebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView registerButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // ربط العناصر بملفات XML
        usernameEditText = findViewById(R.id.username55);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.pass2);
        registerButton = findViewById(R.id.register_button);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // تعيين مستمع لزر التسجيل
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // التحقق من صحة البيانات
                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    // حفظ حالة تسجيل الدخول
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    // الانتقال إلى MainActivity بعد التسجيل الناجح
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // اغلق النشاط الحالي
                } else {
                    // عرض رسالة خطأ إذا كانت البيانات غير صحيحة
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
