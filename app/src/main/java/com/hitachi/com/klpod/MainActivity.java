package com.hitachi.com.klpod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hitachi.com.klpod.Fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // แสดงหน้า Login
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentMainFragment,new LoginFragment())
                    .commit();
        }
    }
}
