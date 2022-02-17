package org.overlake.mat803.fragmentexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import java.lang.Math;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.overlake.mat803.fragmentexample.databinding.FragmentCountdownTimerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountdownTimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountdownTimerFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TIME = "time";
    public static final String TIMER_DONE = "timer_done";


    private int mTime;

    private FragmentCountdownTimerBinding mBinding;
    private CountDownTimer mTimer;

    public CountdownTimerFragment() {
        // Required empty public constructor
    }


    public static CountdownTimerFragment newInstance(int time) {
        CountdownTimerFragment fragment = new CountdownTimerFragment();
        Bundle args = new Bundle();
        args.putInt(TIME, time);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTime = getArguments().getInt(TIME);

        }



        mTimer = new CountDownTimer(mTime*1000, 1000) {
            @Override
            public void onTick(long l) {
                mBinding.timerTime.setText(String.valueOf(l/1000 + 1));
                System.out.println(l);
            }

            @Override
            public void onFinish() {
                getParentFragmentManager().setFragmentResult(TIMER_DONE, null);
                mBinding.timerTime.setText("0");
                MainActivity.showReset();
            }
        };

        FragmentCountdownTimerBinding mBinding = FragmentCountdownTimerBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentCountdownTimerBinding.inflate(getLayoutInflater());
        mBinding.timerTime.setText(String.valueOf(mTime));
        return mBinding.getRoot();
    }

    public void start() {
        mTimer.start();
    }

    public void resetTime() {
        mBinding.timerTime.setText("5");
    }
}