package org.overlake.mat803.fragmentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.View;

import org.overlake.mat803.fragmentexample.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        FragmentManager fm = getSupportFragmentManager();
        CountdownTimerFragment fragment = CountdownTimerFragment.newInstance(5);
        fm.beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();
        mBinding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.start();
                mBinding.timerDoneText.setVisibility(View.INVISIBLE);
            }
        });
        mBinding.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.startButton.setVisibility(View.VISIBLE);
                mBinding.resetButton.setVisibility(View.INVISIBLE);
                mBinding.timerDoneText.setVisibility(View.INVISIBLE);
                fragment.resetTime();
            }
        });
        fm.setFragmentResultListener(CountdownTimerFragment.TIMER_DONE, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                mBinding.timerDoneText.setVisibility(View.VISIBLE);
            }
        });


    }

    public static void showReset() {
        mBinding.startButton.setVisibility(View.INVISIBLE);
        mBinding.resetButton.setVisibility(View.VISIBLE);
    }

}