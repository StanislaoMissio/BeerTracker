package com.android.beertracker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.android.beertracker.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView imageLoadingHandLeft;
    ImageView imageLoadingHandRight;
    ImageView imageLoadingSparkUp;
    ImageView imageLoadingSparkDown;
    private final int DURATION_MILLIS = 300;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageLoadingHandLeft = findViewById(R.id.loading_animation_hand_left);

        imageLoadingHandRight = findViewById(R.id.loading_animation_hand_right);

        imageLoadingSparkUp = findViewById(R.id.loading_animation_spark_up);

        imageLoadingSparkDown = findViewById(R.id.loading_animation_spark_down);

        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(DURATION_MILLIS);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        imageLoadingSparkUp.startAnimation(animation);
        imageLoadingSparkDown.startAnimation(animation);

    }
}
