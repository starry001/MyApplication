package com.starry.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager dbManager = DBManager.getInstance(this);
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(i * 3);
            user.setName("第" + i + "人");
            dbManager.insertUser(user);

            Student s = new Student(i + 100, "student" + i, i + 20);
            dbManager.insertStudent(s);
        }
    }
}
