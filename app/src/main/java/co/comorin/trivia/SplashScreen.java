package co.comorin.trivia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import java.util.Calendar;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LinearLayout background = findViewById(R.id.background);
        final CardView container = findViewById(R.id.container);



        // Shutter animation
        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0, getResources().getDisplayMetrics().heightPixels + 500);

        animation.setFillAfter(true);
        animation.setDuration(1000);
        background.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //fade animation
                container.animate().setDuration(500).alpha(1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        container.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(SplashScreen.this, NameAnswerActivity.class));
                            }
                        }, 1000);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
