package com.han.jin.pokmoncatcher;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JiN on 14/09/16.
 */
public class PokemonDetailActivity extends AppCompatActivity {

    private PokemonsDatabaseHelper dbHelper;
    SQLiteDatabase db;

    private String id;
    private int image;
    private String name;
    private String weight;
    private String height;
    private String type1;
    private String type2;
    private String category;
    private String description;
    private int evolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(" Pokémon Detail");
        getSupportActionBar().setIcon(R.mipmap.logo_tail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String itemOnClicked = intent.getStringExtra("clickedItem");

        dbHelper = new PokemonsDatabaseHelper(this, "Pokemons.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Pokemons", null, "id = '" + itemOnClicked + "'",
                null, null, null, null);
        if (cursor.moveToFirst()) {
            id = cursor.getString(cursor.getColumnIndex("id"));
            image = cursor.getInt(cursor.getColumnIndex("image"));
            name = cursor.getString(cursor.getColumnIndex("name"));
            weight = Float.toString(cursor.getFloat(cursor.getColumnIndex("weight")));
            height = Float.toString(cursor.getFloat(cursor.getColumnIndex("height")));
            type1 = cursor.getString(cursor.getColumnIndex("type1"));
            type2 = cursor.getString(cursor.getColumnIndex("type2"));
            category = cursor.getString(cursor.getColumnIndex("category"));
            description = cursor.getString(cursor.getColumnIndex("description"));
            evolution = cursor.getInt(cursor.getColumnIndex("evolution"));
        }
        cursor.close();

        ImageView pokemon_image = (ImageView) findViewById(R.id.pokemon_image);
        pokemon_image.setImageDrawable(getResources().getDrawable(image));

        TextView pokemon_id = (TextView) findViewById(R.id.pokemon_id);
        pokemon_id.setText(id + " ");

        TextView pokemon_name = (TextView) findViewById(R.id.pokemon_name);
        pokemon_name.setText(name);

        TextView pokemon_weight = (TextView) findViewById(R.id.pokemon_weight);
        pokemon_weight.setText("Weight: " + weight + "kg");

        TextView pokemon_height = (TextView) findViewById(R.id.pokemon_height);
        pokemon_height.setText("Height: " + height + "m");

        TextView pokemon_type1 = (TextView) findViewById(R.id.pokemon_type1);
        pokemon_type1.setText(type1);

        if (type2 != null) {
            TextView pokemon_type2 = (TextView) findViewById(R.id.pokemon_type2);
            pokemon_type2.setText(" | " + type2);
        }

        TextView pokemon_category = (TextView) findViewById(R.id.pokemon_category);
        pokemon_category.setText(category);

        TextView pokemon_description = (TextView) findViewById(R.id.pokemon_description);
        pokemon_description.setText(description);

        TextView pokemon_evolution_text = (TextView) findViewById(R.id.pokemon_evolution_text);
        pokemon_evolution_text.setText("Evolution");

        if (evolution != 0) {
            ImageView pokemon_evolution_image = (ImageView) findViewById(R.id.pokemon_evolution_image);
            pokemon_evolution_image.setImageDrawable(getResources().getDrawable(evolution));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_help:
                Intent help_intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(help_intent);
                break;
            case R.id.menu_about:
                Intent about_intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(about_intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
