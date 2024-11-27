package com.example.facebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView loginButton;
    private TextView registerLink;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ربط العناصر بملفات XML
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.pass);
        loginButton = findViewById(R.id.login_button1);
        registerLink = findViewById(R.id.register_link);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // التحقق من حالة تسجيل الدخول عند بدء النشاط
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToMainActivity();
        }

        // تعيين مستمع لزر تسجيل الدخول
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // التحقق من صحة البيانات
                if (username.equals("correctUsername") && password.equals("correctPassword")) {
                    // حفظ حالة تسجيل الدخول
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    navigateToMainActivity();
                } else {
                    // عرض رسالة خطأ إذا كانت البيانات غير صحيحة
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // تعيين مستمع لرابط التسجيل
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // الانتقال إلى RegisterActivity عند النقر على رابط التسجيل
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // اغلق النشاط الحالي
    }
}
