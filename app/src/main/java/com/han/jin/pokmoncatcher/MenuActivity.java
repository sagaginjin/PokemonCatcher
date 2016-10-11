package com.han.jin.pokmoncatcher;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by JiN on 12/09/16.
 */
public class MenuActivity extends AppCompatActivity {

    private PokemonsDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        getSupportActionBar().setTitle(" Menu");
        getSupportActionBar().setIcon(R.mipmap.logo_tail);

        dbHelper = new PokemonsDatabaseHelper(this, "Pokemons.db", null, 1);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        if (checkDB()) {
            insertData();
        }

        Button map_button = (Button) findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        Button library_button = (Button) findViewById(R.id.library_button);
        library_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LibraryActivity.class);
                startActivity(intent);
            }
        });

        Button help_button = (Button) findViewById(R.id.help_button);
        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        Button about_button = (Button) findViewById(R.id.about_button);
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
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

    public boolean checkDB() {
        boolean isDBEmpty;
        String count = "SELECT count(*) FROM Pokemons";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            isDBEmpty = false;
        } else {
            isDBEmpty = true;
        }
        return isDBEmpty;
    }

    public void insertData() {
        values.put("id", "001");
        values.put("image", R.mipmap.p001);
        values.put("name", "Bulbasaur");
        values.put("weight", 6.9);
        values.put("height", 0.7);
        values.put("type1", "Grass");
        values.put("type2", "Poison");
        values.put("category", "Seed Pokémon");
        values.put("description", "Bulbasaur can be seen napping in bright sunlight. " +
                "There is a seed on its back. By soaking up the sun's rays, " +
                "the seed grows progressively larger.");
        values.put("evolution", R.mipmap.p002);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "002");
        values.put("image", R.mipmap.p002);
        values.put("name", "Ivysaur");
        values.put("weight", 13.0);
        values.put("height", 1.0);
        values.put("type1", "Grass");
        values.put("type2", "Poison");
        values.put("category", "Seed Pokémon");
        values.put("description", "There is a bud on this Pokémon's back. " +
                "To support its weight, Ivysaur's legs and trunk grow thick and strong. " +
                "If it starts spending more time lying in the sunlight, " +
                "it's a sign that the bud will bloom into a large flower soon.");
        values.put("evolution", R.mipmap.p003);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "003");
        values.put("image", R.mipmap.p003);
        values.put("name", "Venasaur");
        values.put("weight", 100.0);
        values.put("height", 2.0);
        values.put("type1", "Grass");
        values.put("type2", "Poison");
        values.put("category", "Seed Pokémon");
        values.put("description", "There is a large flower on Venusaur's back. " +
                "The flower is said to take on vivid colors if it gets plenty of " +
                "nutrition and sunlight. The flower's aroma soothes the emotions of people.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "004");
        values.put("image", R.mipmap.p004);
        values.put("name", "Charmander");
        values.put("weight", 8.5);
        values.put("height", 0.6);
        values.put("type1", "Fire");
        values.put("category", "Lizard Pokémon");
        values.put("description", "The flame that burns at the tip of its tail is " +
                "an indication of its emotions. The flame wavers when Charmander is " +
                "enjoying itself. If the Pokémon becomes enraged, the flame burns fiercely.");
        values.put("evolution", R.mipmap.p005);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "005");
        values.put("image", R.mipmap.p005);
        values.put("name", "Charmeleon");
        values.put("weight", 19.0);
        values.put("height", 1.1);
        values.put("type1", "Fire");
        values.put("category", "Flame Pokémon");
        values.put("description", "Charmeleon mercilessly destroys its foes using its sharp " +
                "claws. If it encounters a strong foe, it turns aggressive. In this excited " +
                "state, the flame at the tip of its tail flares with a bluish white color.");
        values.put("evolution", R.mipmap.p006);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "006");
        values.put("image", R.mipmap.p006);
        values.put("name", "Charizard");
        values.put("weight", 90.5);
        values.put("height", 1.7);
        values.put("type1", "Fire");
        values.put("type1", "Flying");
        values.put("category", "Flame Pokémon");
        values.put("description", "Charizard flies around the sky in search of powerful " +
                "opponents. It breathes fire of such great heat that it melts anything. " +
                "However, it never turns its fiery breath on any opponent weaker than itself.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "007");
        values.put("image", R.mipmap.p007);
        values.put("name", "Squirtle");
        values.put("weight", 9.0);
        values.put("height", 0.5);
        values.put("type1", "Water");
        values.put("category", "Tiny Turtle Pokèmon");
        values.put("description", "Squirtle's shell is not merely used for protection. " +
                "The shell's rounded shape and the grooves on its surface help minimize " +
                "resistance in water, enabling this Pokémon to swim at high speeds.");
        values.put("evolution", R.mipmap.p008);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "008");
        values.put("image", R.mipmap.p008);
        values.put("name", "Wartortle");
        values.put("weight", 22.5);
        values.put("height", 1.0);
        values.put("type1", "Water");
        values.put("category", "Turtle Pokémon");
        values.put("description", "Its tail is large and covered with a rich, thick fur. " +
                "The tail becomes increasingly deeper in color as Wartortle ages. The " +
                "scratches on its shell are evidence of this Pokémon's toughness as a battler.");
        values.put("evolution", R.mipmap.p009);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "009");
        values.put("image", R.mipmap.p009);
        values.put("name", "Blastoise");
        values.put("weight", 88.5);
        values.put("height", 1.6);
        values.put("type1", "Water");
        values.put("category", "Shellfish Pokèmon");
        values.put("description", "Blastoise has water spouts that protrude from its shell. " +
                "The water spouts are very accurate. They can shoot bullets of water with " +
                "enough accuracy to strike empty cans from a distance of over 160 feet.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "010");
        values.put("image", R.mipmap.p010);
        values.put("name", "Caterpie");
        values.put("weight", 2.9);
        values.put("height", 0.3);
        values.put("type1", "Bug");
        values.put("category", "Worm Pokémon");
        values.put("description", "Caterpie has a voracious appetite. It can devour leaves " +
                "bigger than its body right before your eyes. From its antenna, this Pokémon " +
                "releases a terrifically strong odor.");
        values.put("evolution", R.mipmap.p011);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "011");
        values.put("image", R.mipmap.p011);
        values.put("name", "Metapod");
        values.put("weight", 9.9);
        values.put("height", 0.7);
        values.put("type1", "Bug");
        values.put("category", "Cocoon Pokémon");
        values.put("description", "The shell covering this Pokémon’s body is as hard as an " +
                "iron slab. Metapod does not move very much. It stays still because it is " +
                "preparing its soft innards for evolution inside the hard shell.");
        values.put("evolution", R.mipmap.p012);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "012");
        values.put("image", R.mipmap.p012);
        values.put("name", "Butterfree");
        values.put("weight", 32.1);
        values.put("height", 1.1);
        values.put("type1", "Bug");
        values.put("type2", "Flying");
        values.put("category", "Cocoon Pokémon");
        values.put("description", "Butterfree has a superior ability to search for delicious " +
                "honey from flowers. It can even search out, extract, and carry honey from " +
                "flowers that are blooming over six miles from its nest.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "013");
        values.put("image", R.mipmap.p013);
        values.put("name", "Weedle");
        values.put("weight", 3.2);
        values.put("height", 1.3);
        values.put("type1", "Bug");
        values.put("type2", "Poison");
        values.put("category", "Hairy Bug Pokémon");
        values.put("description", "Weedle has an extremely acute sense of smell. It is capable " +
                "of distinguishing its favorite kinds of leaves from those it dislikes just by " +
                "sniffing with its big red proboscis (nose)");
        values.put("evolution", R.mipmap.p014);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "014");
        values.put("image", R.mipmap.p014);
        values.put("name", "Kakuna");
        values.put("weight", 10.0);
        values.put("height", 0.6);
        values.put("type1", "Bug");
        values.put("type2", "Poison");
        values.put("category", "Cocoon Pokémon");
        values.put("description", "Kakuna remains virtually immobile as it clings to a tree. " +
                "However, on the inside, it is extremely busy as it prepares for its coming " +
                "evolution. This is evident from how hot the shell becomes to the touch.");
        values.put("evolution", R.mipmap.p015);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "015");
        values.put("image", R.mipmap.p015);
        values.put("name", "Butterfree");
        values.put("weight", 29.5);
        values.put("height", 1.0);
        values.put("type1", "Bug");
        values.put("type2", "Poison");
        values.put("category", "Poison Bee Pokémon");
        values.put("description", "Beedrill is extremely territorial. No one should ever " +
                "approach its nest—this is for their own safety. If angered, they will attack " +
                "in a furious swarm.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "016");
        values.put("image", R.mipmap.p016);
        values.put("name", "Pidgey");
        values.put("weight", 1.8);
        values.put("height", 0.3);
        values.put("type1", "Normal");
        values.put("type2", "Flying");
        values.put("category", "Tiny Bird Pokémon");
        values.put("description", "Pidgey has an extremely sharp sense of direction. It is " +
                "capable of unerringly returning home to its nest, however far it may be " +
                "removed from its familiar surroundings.");
        values.put("evolution", R.mipmap.p017);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "017");
        values.put("image", R.mipmap.p017);
        values.put("name", "Pidgeotto");
        values.put("weight", 30.0);
        values.put("height", 1.1);
        values.put("type1", "Normal");
        values.put("type2", "Flying");
        values.put("category", "Bird Pokémon");
        values.put("description", "Pidgeotto claims a large area as its own territory. This " +
                "Pokémon flies around, patrolling its living space. If its territory is " +
                "violated, it shows no mercy in thoroughly punishing the foe with its sharp claws.");
        values.put("evolution", R.mipmap.p018);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "018");
        values.put("image", R.mipmap.p018);
        values.put("name", "Pidgeot");
        values.put("weight", 39.5);
        values.put("height", 1.5);
        values.put("type1", "Normal");
        values.put("type2", "Flying");
        values.put("category", "Bird Pokémon");
        values.put("description", "This Pokémon has a dazzling plumage of beautifully glossy " +
                "feathers. Many Trainers are captivated by the striking beauty of the feathers " +
                "on its head, compelling them to choose Pidgeot as their Pokémon.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "019");
        values.put("image", R.mipmap.p019);
        values.put("name", "Rattata");
        values.put("weight", 3.5);
        values.put("height", 0.3);
        values.put("type1", "Normal");
        values.put("category", "Mouse Pokémon");
        values.put("description", "Rattata is cautious in the extreme. Even while it is asleep, " +
                "it constantly listens by moving its ears around. It is not picky about where it " +
                "lives—it will make its nest anywhere.");
        values.put("evolution", R.mipmap.p020);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "020");
        values.put("image", R.mipmap.p020);
        values.put("name", "Raticate");
        values.put("weight", 18.5);
        values.put("height", 0.7);
        values.put("type1", "Normal");
        values.put("category", "Mouse Pokémon");
        values.put("description", "Raticate’s sturdy fangs grow steadily. To keep them ground " +
                "down, it gnaws on rocks and logs. It may even chew on the walls of houses.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "021");
        values.put("image", R.mipmap.p021);
        values.put("name", "Spearow");
        values.put("weight", 2.0);
        values.put("height", 0.3);
        values.put("type1", "Normal");
        values.put("type2", "Flying ");
        values.put("category", "Tiny Bird Pokémon");
        values.put("description", "Spearow has a very loud cry that can be heard over half a " +
                "mile away. If its high, keening cry is heard echoing all around, it is a sign " +
                "that they are warning each other of danger.");
        values.put("evolution", R.mipmap.p022);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "022");
        values.put("image", R.mipmap.p022);
        values.put("name", "Fearow");
        values.put("weight", 38.0);
        values.put("height", 1.2);
        values.put("type1", "Normal");
        values.put("type2", "Flying ");
        values.put("category", "Beak Pokémon");
        values.put("description", "Fearow is recognized by its long neck and elongated beak. " +
                "They are conveniently shaped for catching prey in soil or water. It deftly " +
                "moves its long and skinny beak to pluck prey.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "023");
        values.put("image", R.mipmap.p023);
        values.put("name", "Ekans");
        values.put("weight", 6.9);
        values.put("height", 2.0);
        values.put("type1", "Poison");
        values.put("category", "Snake Pokémon");
        values.put("description", "Ekans curls itself up in a spiral while it rests. Assuming " +
                "this position allows it to quickly respond to a threat from any direction with " +
                "a glare from its upraised head.");
        values.put("evolution", R.mipmap.p024);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "024");
        values.put("image", R.mipmap.p024);
        values.put("name", "Arbok");
        values.put("weight", 65.0);
        values.put("height", 3.5);
        values.put("type1", "Poison");
        values.put("category", "Cobra Pokémon");
        values.put("description", "This Pokémon is terrifically strong in order to constrict " +
                "things with its body. It can even flatten steel oil drums. Once Arbok wraps " +
                "its body around its foe, escaping its crunching embrace is impossible.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "025");
        values.put("image", R.mipmap.p025);
        values.put("name", "Pikachu");
        values.put("weight", 6.0);
        values.put("height", 0.4);
        values.put("type1", "Electric");
        values.put("category", "Mouse Pokémon");
        values.put("description", "Whenever Pikachu comes across something new, it blasts it " +
                "with a jolt of electricity. If you come across a blackened berry, it's evidence " +
                "that this Pokémon mistook the intensity of its charge.");
        values.put("evolution", R.mipmap.p026);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "026");
        values.put("image", R.mipmap.p026);
        values.put("name", "Raichu");
        values.put("weight", 30.0);
        values.put("height", 0.8);
        values.put("type1", "Electric");
        values.put("category", "Mouse Pokémon");
        values.put("description", "If the electrical sacs become excessively charged, Raichu " +
                "plants its tail in the ground and discharges. Scorched patches of ground will " +
                "be found near this Pokémon's nest.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "027");
        values.put("image", R.mipmap.p027);
        values.put("name", "Sandshrew");
        values.put("weight", 12.0);
        values.put("height", 0.6);
        values.put("type1", "Ground");
        values.put("category", "Mouse Pokémon");
        values.put("description", "Sandshrew's body is configured to absorb water without waste, " +
                "enabling it to survive in an arid desert. This Pokémon curls up to protect " +
                "itself from its enemies.");
        values.put("evolution", R.mipmap.p028);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "028");
        values.put("image", R.mipmap.p028);
        values.put("name", "Sandslash");
        values.put("weight", 29.5);
        values.put("height", 1.0);
        values.put("type1", "Ground");
        values.put("category", "Mouse Pokémon");
        values.put("description", "Sandslash's body is covered by tough spikes, which are " +
                "hardened sections of its hide. Once a year, the old spikes fall out, to be " +
                "replaced with new spikes that grow out from beneath the old ones.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "029");
        values.put("image", R.mipmap.p029);
        values.put("name", "Nidoran♀");
        values.put("weight", 7.0);
        values.put("height", 0.4);
        values.put("type1", "Poison");
        values.put("category", "Poison Pin Pokémon");
        values.put("description", "Nidoran♀ has barbs that secrete a powerful poison. They are " +
                "thought to have developed as protection for this small-bodied Pokémon. When " +
                "enraged, it releases a horrible toxin from its horn.");
        values.put("evolution", R.mipmap.p030);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "030");
        values.put("image", R.mipmap.p030);
        values.put("name", "Nidorina");
        values.put("weight", 20.0);
        values.put("height", 0.8);
        values.put("type1", "Poison");
        values.put("category", "Poison Pin Pokémon");
        values.put("description", "When Nidorina are with their friends or family, they keep " +
                "their barbs tucked away to prevent hurting each other. This Pokémon appears " +
                "to become nervous if separated from the others.");
        values.put("evolution", R.mipmap.p031);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "031");
        values.put("image", R.mipmap.p031);
        values.put("name", "Nidoqueen");
        values.put("weight", 60.0);
        values.put("height", 1.3);
        values.put("type1", "Poison");
        values.put("type2", "Ground ");
        values.put("category", "Drill Pokémon");
        values.put("description", "Nidoqueen's body is encased in extremely hard scales. " +
                "It is adept at sending foes flying with harsh tackles. This Pokémon is at its " +
                "strongest when it is defending its young.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "032");
        values.put("image", R.mipmap.p032);
        values.put("name", "Nidoran♂");
        values.put("weight", 9.0);
        values.put("height", 0.5);
        values.put("type1", "Poison");
        values.put("category", "Poison Pin Pokémon");
        values.put("description", "Nidoran♂ has developed muscles for moving its ears. Thanks " +
                "to them, the ears can be freely moved in any direction. Even the slightest " +
                "sound does not escape this Pokémon's notice.");
        values.put("evolution", R.mipmap.p033);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "033");
        values.put("image", R.mipmap.p033);
        values.put("name", "Nidorino");
        values.put("weight", 19.5);
        values.put("height", 0.9);
        values.put("type1", "Poison");
        values.put("category", "Poison Pin Pokémon");
        values.put("description", "Nidorino has a horn that is harder than a diamond. If it " +
                "senses a hostile presence, all the barbs on its back bristle up at once, and " +
                "it challenges the foe with all its might.");
        values.put("evolution", R.mipmap.p034);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "034");
        values.put("image", R.mipmap.p034);
        values.put("name", "Nidoking");
        values.put("weight", 62.0);
        values.put("height", 1.4);
        values.put("type1", "Poison");
        values.put("type2", "Ground ");
        values.put("category", "Drill Pokémon");
        values.put("description", "Nidoking's thick tail packs enormously destructive power. " +
                "With one swing, it can topple a metal transmission tower. Once this Pokémon " +
                "goes on a rampage, there is no stopping it.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "035");
        values.put("image", R.mipmap.p035);
        values.put("name", "Clefairy");
        values.put("weight", 7.5);
        values.put("height", 0.6);
        values.put("type1", "Fairy");
        values.put("category", "Fairy Pokémon");
        values.put("description", "On every night of a full moon, groups of this Pokémon come " +
                "out to play. When dawn arrives, the tired Clefairy return to their quiet " +
                "mountain retreats and go to sleep nestled up against each other.");
        values.put("evolution", R.mipmap.p036);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "036");
        values.put("image", R.mipmap.p036);
        values.put("name", "Clefable");
        values.put("weight", 40.0);
        values.put("height", 1.3);
        values.put("type1", "Fairy");
        values.put("category", "Fairy Pokémon");
        values.put("description", "Clefable moves by skipping lightly as if it were flying " +
                "using its wings. Its bouncy step even lets it walk on water. It is known to " +
                "take strolls on lakes on quiet, moonlit nights.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "037");
        values.put("image", R.mipmap.p037);
        values.put("name", "Vulpix");
        values.put("weight", 1.0);
        values.put("height", 0.6);
        values.put("type1", "Fire");
        values.put("category", "Fox Pokémon");
        values.put("description", "At the time of its birth, Vulpix has one white tail. " +
                "The tail separates into six if this Pokémon receives plenty of love from " +
                "its trainer. The six tails become magnificently curled.");
        values.put("evolution", R.mipmap.p038);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "038");
        values.put("image", R.mipmap.p038);
        values.put("name", "Ninetales");
        values.put("weight", 19.9);
        values.put("height", 1.1);
        values.put("type1", "Fire");
        values.put("category", "Fox Pokémon");
        values.put("description", "Ninetales casts a sinister light from its bright red eyes " +
                "to gain total control over its foe's mind. This Pokémon is said to live for " +
                "one thousand years.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "039");
        values.put("image", R.mipmap.p039);
        values.put("name", "Jigglypuff");
        values.put("weight", 5.5);
        values.put("height", 0.5);
        values.put("type1", "Normal");
        values.put("type2", "Fairy");
        values.put("category", "Balloon Pokémon");
        values.put("description", "Jigglypuff's vocal cords can freely adjust the wavelength of " +
                "its voice. This Pokémon uses this ability to sing at precisely the right " +
                "wavelength to make its foes most drowsy.");
        values.put("evolution", R.mipmap.p040);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "040");
        values.put("image", R.mipmap.p040);
        values.put("name", "Wigglytuff");
        values.put("weight", 12.0);
        values.put("height", 1.0);
        values.put("type1", "Normal");
        values.put("type2", "Fairy");
        values.put("category", "Balloon Pokémon");
        values.put("description", "Wigglytuff has large, saucerlike eyes. The surfaces of its " +
                "eyes are always covered with a thin layer of tears. If any dust gets in this " +
                "Pokémon's eyes, it is quickly washed away.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "041");
        values.put("image", R.mipmap.p041);
        values.put("name", "Zubat");
        values.put("weight", 7.5);
        values.put("height", 0.8);
        values.put("type1", "Poison");
        values.put("type2", "Flying");
        values.put("category", "Bat Pokémon");
        values.put("description", "Zubat remains quietly unmoving in a dark spot during the " +
                "bright daylight hours. It does so because prolonged exposure to the sun causes " +
                "its body to become slightly burned.");
        values.put("evolution", R.mipmap.p042);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "042");
        values.put("image", R.mipmap.p042);
        values.put("name", "Golbat");
        values.put("weight", 55.0);
        values.put("height", 1.6);
        values.put("type1", "Poison");
        values.put("type2", "Flying");
        values.put("category", "Bat Pokémon");
        values.put("description", "Golbat loves to drink the blood of living things. It is " +
                "particularly active in the pitch black of night. This Pokémon flits around " +
                "in the night skies, seeking fresh blood.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "043");
        values.put("image", R.mipmap.p043);
        values.put("name", "Oddish");
        values.put("weight", 5.4);
        values.put("height", 0.5);
        values.put("type1", "Grass");
        values.put("type2", "Poison");
        values.put("category", "Weed Pokémon");
        values.put("description", "During the daytime, Oddish buries itself in soil to " +
                "absorb nutrients from the ground using its entire body. The more fertile " +
                "the soil, the glossier its leaves become.");
        values.put("evolution", R.mipmap.p044);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "044");
        values.put("image", R.mipmap.p044);
        values.put("name", "Gloom");
        values.put("weight", 8.6);
        values.put("height", 0.8);
        values.put("type1", "Grass");
        values.put("type2", "Poison");
        values.put("category", "Weed Pokémon");
        values.put("description", "Gloom releases a foul fragrance from the pistil of its " +
                "flower. When faced with danger, the stench worsens. If this Pokémon is " +
                "feeling calm and secure, it does not release its usual stinky aroma.");
        values.put("evolution", R.mipmap.p045);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "045");
        values.put("image", R.mipmap.p045);
        values.put("name", "Vileplume");
        values.put("weight", 18.6);
        values.put("height", 1.2);
        values.put("type1", "Grass");
        values.put("type2", "Poison");
        values.put("category", "Flower Pokémon");
        values.put("description", "Vileplume's toxic pollen triggers atrocious allergy " +
                "attacks. That's why it is advisable never to approach any attractive flowers " +
                "in a jungle, however pretty they may be.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "046");
        values.put("image", R.mipmap.p046);
        values.put("name", "Gloom");
        values.put("weight", 5.4);
        values.put("height", 0.3);
        values.put("type1", "Bug");
        values.put("type2", "Grass");
        values.put("category", "Mushroom Pokémon");
        values.put("description", "Paras has parasitic mushrooms growing on its back called " +
                "tochukaso. They grow large by drawing nutrients from this Bug Pokémon host. " +
                "They are highly valued as a medicine for extending life.");
        values.put("evolution", R.mipmap.p047);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "047");
        values.put("image", R.mipmap.p047);
        values.put("name", "Parasect");
        values.put("weight", 29.5);
        values.put("height", 1.0);
        values.put("type1", "Bug");
        values.put("type2", "Grass");
        values.put("category", "Mushroom Pokémon");
        values.put("description", "Parasect is known to infest large trees en masse and drain " +
                "nutrients from the lower trunk and roots. When an infested tree dies, they " +
                "move onto another tree all at once.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "048");
        values.put("image", R.mipmap.p048);
        values.put("name", "Venonat");
        values.put("weight", 30.0);
        values.put("height", 1.0);
        values.put("type1", "Bug");
        values.put("type2", "Poison");
        values.put("category", "Insect Pokémon");
        values.put("description", "Venonat is said to have evolved with a coat of thin, stiff " +
                "hair that covers its entire body for protection. It possesses large eyes that " +
                "never fail to spot even miniscule prey.");
        values.put("evolution", R.mipmap.p049);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "049");
        values.put("image", R.mipmap.p049);
        values.put("name", "Venomoth");
        values.put("weight", 12.5);
        values.put("height", 1.5);
        values.put("type1", "Bug");
        values.put("type2", "Poison");
        values.put("category", "Poison Moth  Pokémon");
        values.put("description", "Venomoth is nocturnal—it is a Pokémon that only becomes " +
                "active at night. Its favorite prey are small insects that gather around " +
                "streetlights, attracted by the light in the darkness.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "050");
        values.put("image", R.mipmap.p050);
        values.put("name", "Diglett");
        values.put("weight", 0.8);
        values.put("height", 0.2);
        values.put("type1", "Ground");
        values.put("category", "Mole Pokémon");
        values.put("description", "Diglett are raised in most farms. The reason is " +
                "simple—wherever this Pokémon burrows, the soil is left perfectly tilled for " +
                "planting crops. This soil is made ideal for growing delicious vegetables.");
        values.put("evolution", R.mipmap.p051);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "051");
        values.put("image", R.mipmap.p051);
        values.put("name", "Dugtrio");
        values.put("weight", 33.3);
        values.put("height", 0.71);
        values.put("type1", "Ground");
        values.put("category", "Mole Pokémon");
        values.put("description", "Dugtrio are actually triplets that emerged from one body. " +
                "As a result, each triplet thinks exactly like the other two triplets. They work " +
                "cooperatively to burrow endlessly.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "052");
        values.put("image", R.mipmap.p052);
        values.put("name", "Meowth");
        values.put("weight", 4.2);
        values.put("height", 0.4);
        values.put("type1", "Normal");
        values.put("category", "Scratch Cat  Pokémon");
        values.put("description", "Meowth withdraws its sharp claws into its paws to slinkily " +
                "sneak about without making any incriminating footsteps. For some reason, this " +
                "Pokémon loves shiny coins that glitter with light.");
        values.put("evolution", R.mipmap.p053);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "053");
        values.put("image", R.mipmap.p053);
        values.put("name", "Persian");
        values.put("weight", 32.0);
        values.put("height", 1.0);
        values.put("type1", "Normal");
        values.put("category", "Classy Cat Pokémon");
        values.put("description", "Persian has six bold whiskers that give it a look of toughness. " +
                "The whiskers sense air movements to determine what is in the Pokémon's surrounding " +
                "vicinity. It becomes docile if grabbed by the whiskers.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "054");
        values.put("image", R.mipmap.p054);
        values.put("name", "Psyduck");
        values.put("weight", 19.6);
        values.put("height", 0.8);
        values.put("type1", "Water");
        values.put("category", "Duck  Pokémon");
        values.put("description", "Psyduck uses a mysterious power. When it does so, this " +
                "Pokémon generates brain waves that are supposedly only seen in sleepers. This " +
                "discovery spurred controversy among scholars.");
        values.put("evolution", R.mipmap.p055);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "055");
        values.put("image", R.mipmap.p055);
        values.put("name", "Golduck");
        values.put("weight", 76.6);
        values.put("height", 1.7);
        values.put("type1", "Water");
        values.put("category", "Duck  Pokémon");
        values.put("description", "The webbed flippers on its forelegs and hind legs and the " +
                "streamlined body of Golduck give it frightening speed. The Pokémon is definitely " +
                "much faster than even the most athletic swimmer.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "056");
        values.put("image", R.mipmap.p056);
        values.put("name", "Mankey");
        values.put("weight", 28.0);
        values.put("height", 0.5);
        values.put("type1", "Fighting");
        values.put("category", "Pig Monkey Pokémon");
        values.put("description", "When Mankey starts shaking and its nasal breathing turns " +
                "rough, it's a sure sign that it is becoming angry. However, because it goes " +
                "into a towering rage almost instantly, it is impossible for anyone to flee its wrath.");
        values.put("evolution", R.mipmap.p057);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "057");
        values.put("image", R.mipmap.p057);
        values.put("name", "Primeape");
        values.put("weight", 32.0);
        values.put("height", 1.0);
        values.put("type1", "Fighting");
        values.put("category", "Pig Monkey Pokémon");
        values.put("description", "When Primeape becomes furious, its blood circulation is " +
                "boosted. In turn, its muscles are made even stronger. However, it also becomes " +
                "much less intelligent at the same time.");
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "058");
        values.put("image", R.mipmap.p058);
        values.put("name", "Growlithe");
        values.put("weight", 19.0);
        values.put("height", 0.7);
        values.put("type1", "Fire");
        values.put("category", "Puppy Pokémon");
        values.put("description", "Growlithe has a superb sense of smell. Once it smells " +
                "anything, this Pokémon won't forget the scent, no matter what. It uses its " +
                "advanced olfactory sense to determine the emotions of other living things.");
        values.put("evolution", R.mipmap.p059);
        db.insert("Pokemons", null, values);
        values.clear();
        values.put("id", "059");
        values.put("image", R.mipmap.p059);
        values.put("name", "Arcanine");
        values.put("weight", 155.0);
        values.put("height", 1.9);
        values.put("type1", "Fire");
        values.put("category", "Legendary Pokémon");
        values.put("description", "Arcanine is known for its high speed. It is said to be capable " +
                "of running over 6,200 miles in a single day and night. The fire that blazes wildly " +
                "within this Pokémon's body is its source of power.");
        db.insert("Pokemons", null, values);
        values.clear();
    }
}
