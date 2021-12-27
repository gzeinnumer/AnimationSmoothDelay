package com.gzeinnumer.animationsmoothdelay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.gzeinnumer.animationsmoothdelay.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initType1();
        initType2();
        initType3();
        initType4();
    }

    private void initType1() {
        binding.btnTest.setOnClickListener(v -> {
            binding.btnTest.setEnabled(false);
            actionNowV1();

            new Handler().postDelayed(() -> {
                actionNowV1();
                binding.btnTest.setEnabled(true);
            }, 4000);
        });
    }

    private void actionNowV1() {
        if (binding.llExampleHide.getVisibility() == View.VISIBLE) {
            TransitionManager.beginDelayedTransition(binding.edExampleP, new AutoTransition());
            TransitionManager.beginDelayedTransition(binding.llExampleHide, new AutoTransition());
            binding.llExampleHide.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(binding.edExampleP, new AutoTransition());
            TransitionManager.beginDelayedTransition(binding.llExampleHide, new AutoTransition());
            binding.llExampleHide.setVisibility(View.VISIBLE);
        }
    }

    private void initType2() {
        binding.btnTestV2.setOnClickListener(v -> {
            binding.btnTestV2.setEnabled(false);
            actionNowV2(binding.edExamplePV2, binding.llExampleHideV2);

            new Handler().postDelayed(() -> {
                actionNowV2(binding.edExamplePV2, binding.llExampleHideV2);
                binding.btnTestV2.setEnabled(true);
            }, 4000);
        });
    }

    private void actionNowV2(ViewGroup keep, ViewGroup showOrHide) {
        TransitionManager.beginDelayedTransition(keep, new AutoTransition());
        TransitionManager.beginDelayedTransition(showOrHide, new AutoTransition());
        showOrHide.setVisibility(showOrHide.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    private void initType3() {
        binding.btnTestV3.setOnClickListener(v -> {
            binding.btnTestV3.setEnabled(false);
            actionNowV3(binding.edExamplePV3, binding.llExampleHideV3);
        });
        binding.btnLoadV3.setOnClickListener(v -> {
            binding.btnLoadV3.setText("Loading...");

            new Handler().postDelayed(() -> {
                actionNowV3(binding.edExamplePV3, binding.llExampleHideV3);
                binding.btnLoadV3.setText("Hide...");
                binding.btnTestV3.setEnabled(true);
            }, 4000);
        });
    }

    private void actionNowV3(ViewGroup keep, ViewGroup showOrHide) {
        TransitionManager.beginDelayedTransition(keep, new AutoTransition());
        TransitionManager.beginDelayedTransition(showOrHide, new AutoTransition());
        showOrHide.setVisibility(showOrHide.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    int count = 0;
//        int count = 1;

    private void initType4() {
        binding.btnRetriV4.setVisibility(count == 0 ? View.VISIBLE : View.GONE);
        binding.btnRetriV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnRetriV4.setVisibility(View.GONE);
                actionNowV3(binding.edExamplePV4, binding.llExampleHideV4);

                new Handler().postDelayed(() -> {
                    actionNowV2(binding.edExamplePV4, binding.llExampleHideV4);
                    binding.btnRetriV4.setVisibility(View.VISIBLE);
                    //dump action
                    count++;
                    initType4();
                    //end dump action
                }, 4000);
            }
        });
    }
}