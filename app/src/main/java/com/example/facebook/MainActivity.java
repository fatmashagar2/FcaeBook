package com.example.facebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط العناصر بملفات XML
        logoutButton = findViewById(R.id.logout_button);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // تعيين مستمع لزر تسجيل الخروج
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // تنظيف حالة تسجيل الدخول
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                // الانتقال إلى شاشة تسجيل الدخول
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // اغلق النشاط الحالي
            }
        });
    }
}
