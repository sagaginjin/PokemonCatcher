package com.han.jin.pokmoncatcher;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JiN on 13/09/16.
 */
public class LibraryActivity extends AppCompatActivity {

    private PokemonsDatabaseHelper dbHelper;
    SQLiteDatabase db;
    private GridView pokemonGrid;
    private SimpleAdapter gridAdapter;
    private List<Map<String, Object>> gridData = new ArrayList<>();
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(" Library");
        getSupportActionBar().setIcon(R.mipmap.logo_tail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new PokemonsDatabaseHelper(this, "Pokemons.db", null, 1);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Pokemons", new String[]{"image", "name"},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("image", cursor.getInt(cursor.getColumnIndex("image")));
                item.put("name", cursor.getString(cursor.getColumnIndex("name")));
                gridData.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();

        pokemonGrid = (GridView) findViewById(R.id.pokemon_grid);

        gridAdapter = new SimpleAdapter(this, gridData, R.layout.grid_item,
                new String[]{"image", "name"}, new int[]{R.id.grid_image, R.id.grid_text});

        pokemonGrid.setNumColumns(3);
        pokemonGrid.setAdapter(gridAdapter);

        pokemonGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), PokemonDetailActivity.class);
                if (i + 1 < 10) {
                    id = "00" + Integer.toString(i + 1);
                } else if (i + 1 >= 10 && i + 1 < 100) {
                    id = "0" + Integer.toString(i + 1);
                } else {
                    id = Integer.toString(i + 1);
                }
                intent.putExtra("clickedItem", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
