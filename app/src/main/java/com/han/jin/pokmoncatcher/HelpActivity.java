package com.han.jin.pokmoncatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(" Help");
        getSupportActionBar().setIcon(R.mipmap.logo_tail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView helpTitle = (TextView) findViewById(R.id.help_title);
        helpTitle.setText("Help");
        TextView addHelpTitle = (TextView) findViewById(R.id.add_help_title);
        addHelpTitle.setText("How to add a Pokémon on the map?");
        TextView addHelpContent = (TextView) findViewById(R.id.add_help_content);
        addHelpContent.setText("Click on the map hold it for a second, and you will see the " +
                "list of the Pokémons, choose the one you want to add to the map, then you will " +
                "see the Pokémon you just added show on the map.");
        TextView libraryHelpTitle = (TextView) findViewById(R.id.library_help_title);
        libraryHelpTitle.setText("How to see a Pokémon detail?");
        TextView libraryHelpContent = (TextView) findViewById(R.id.library_help_content);
        libraryHelpContent.setText("Click the button at the right bottom of the map, or click " +
                "the library button in the menu screen go to the library, then you can see all " +
                "the Pokémons, click one you can see the Pokémon details on the screen.");
        TextView locationHelpTitle = (TextView) findViewById(R.id.location_help_title);
        locationHelpTitle.setText("How to find my location on the map?");
        TextView locationHelpContent = (TextView) findViewById(R.id.location_help_content);
        locationHelpContent.setText("Click the button at the right top of the map screen, the " +
                "map will show you location on the map.");
    }

}
