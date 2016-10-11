package com.han.jin.pokmoncatcher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JiN on 14/09/16.
 */
public class PokemonsDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_POKEMONS = "create table Pokemons ("
            + "id char(3) primary key NOT NULL, "
            + "image int NOT NULL, "
            + "name text NOT NULL, "
            + "weight real, "
            + "height real, "
            + "type1 text, "
            + "type2 text, "
            + "category text, "
            + "description text, "
            + "evolution int);";

    public static final String CREATE_POKEMON_LOCATION = "create table PokemonLocation ("
            + "id integer primary key autoincrement, "
            + "latitude double NOT NULL, "
            + "longitude double NOT NULL, "
            + "pokemon_id char(3) NOT NULL, "
            + "FOREIGN KEY(pokemon_id) REFERENCES Pokemons(id));";

    public static final String CREATE_POKESTOP_LOCATION = "create table PokestopLocation ("
            + "id integer primary key autoincrement, "
            + "latitude double NOT NULL, "
            + "longitude double NOT NULL, "
            + "time_remain time NOT NULL);";

    private Context pContext;

    public PokemonsDatabaseHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        pContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists Pokemons");
        db.execSQL(CREATE_POKEMONS);
        db.execSQL(CREATE_POKEMON_LOCATION);
        db.execSQL(CREATE_POKESTOP_LOCATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
