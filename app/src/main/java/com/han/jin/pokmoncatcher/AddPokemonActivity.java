package com.han.jin.pokmoncatcher;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPokemonActivity extends AppCompatActivity {

    private PokemonsDatabaseHelper dbHelper;
    private GridView pokemonList;
    private SimpleAdapter listAdapter;
    private List<Map<String, Object>> listData = new ArrayList<>();
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon);

        dbHelper = new PokemonsDatabaseHelper(this, "Pokemons.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Pokemons", new String[]{"image", "name"},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("image", cursor.getInt(cursor.getColumnIndex("image")));
                item.put("name", cursor.getString(cursor.getColumnIndex("name")));
                listData.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();

        pokemonList = (GridView) findViewById(R.id.pokemon_list);

        listAdapter = new SimpleAdapter(this, listData, R.layout.list_item,
                new String[]{"image", "name"}, new int[]{R.id.list_image, R.id.list_text});

        pokemonList.setNumColumns(4);
        pokemonList.setAdapter(listAdapter);

        pokemonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                Bundle bundle = getIntent().getParcelableExtra("bundle");
                if (i + 1 < 10) {
                    id = "00" + Integer.toString(i + 1);
                } else if (i + 1 >= 10 && i + 1 < 100) {
                    id = "0" + Integer.toString(i + 1);
                } else {
                    id = Integer.toString(i + 1);
                }
                intent.putExtra("clickedItem", id);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }
}
