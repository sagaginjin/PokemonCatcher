package com.han.jin.pokmoncatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by JiN on 12/09/16.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView logo = (ImageView) findViewById(R.id.logoImage);
        final Animation rotate = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation fade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        logo.startAnimation(rotate);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(fade);
                finish();
                Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
