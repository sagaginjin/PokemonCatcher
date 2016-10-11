package com.han.jin.pokmoncatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(" About");
        getSupportActionBar().setIcon(R.mipmap.logo_tail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView updateContent = (TextView) findViewById(R.id.update_content);
        updateContent.setText("Bug fixeds. \nAdd new features in the app. \nAdd more Pokémons in " +
                "the library.");
    }
}
