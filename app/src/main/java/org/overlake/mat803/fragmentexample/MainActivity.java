package org.overlake.mat803.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("initial_string", "Hello Countdown Timer Fragment");
        fm.beginTransaction()
                .add(R.id.fragmentContainerView, CountdownTimerFragment.class, args)
                .commit();

    }

}