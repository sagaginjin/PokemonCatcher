package com.han.jin.pokmoncatcher;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.han.jin.pokmoncatcher.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private UiSettings uiSettings;
    private LocationManager locationManager;
    private String provider;
    private Location location;
    private PokemonsDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private ContentValues values;
    private LatLng ll;
    private String itemOnClicked;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        dbHelper = new PokemonsDatabaseHelper(this, "Pokemons.db", null, 1);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        Intent intent = getIntent();
        bundle = intent.getParcelableExtra("bundle");
        itemOnClicked = intent.getStringExtra("clickedItem");
        if (itemOnClicked != null) {
            ll = bundle.getParcelable("latLng");
        }

        ImageButton mylocation_button = (ImageButton) findViewById(R.id.map_mylocation);
        mylocation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserLocation();
            }
        });

        ImageButton library_button = (ImageButton) findViewById(R.id.map_library);
        library_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LibraryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton back_button = (ImageButton) findViewById(R.id.map_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MapStateManager mgr = new MapStateManager(this);
        mgr.saveMapState(mMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        mMap.getUiSettings().setMapToolbarEnabled(false);

        MapStateManager mgr = new MapStateManager(this);

        CameraPosition position = mgr.getCameraPosition();
        if (position != null) {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
            mMap.moveCamera(update);
        }

        setUpMap();
    }

    public void setUpMap() {

        double pLatitude;
        double pLongitude;
        String id;

        Cursor cursor = db.query("PokemonLocation", new String[]{"latitude", "longitude", "pokemon_id"},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                pLatitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                pLongitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                id = cursor.getString(cursor.getColumnIndex("pokemon_id"));
                showPokemonLocation(new LatLng(pLatitude, pLongitude), id);
            } while (cursor.moveToNext());
        }
        cursor.close();

        if (mMap != null) {
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    ll = latLng;
                    choosePokemon(latLng);
                }
            });
        } else {
            Toast.makeText(getBaseContext(), "map is null", Toast.LENGTH_SHORT).show();
        }
        if (ll != null && itemOnClicked != null) {
            addPokemonLocation(ll, itemOnClicked);
        }
    }

    public void getUserLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            return;
        }
        location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            double dLatitude = location.getLatitude();
            double dLongitude = location.getLongitude();

            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(dLatitude, dLongitude))
                    .title("I'm here!")
                    .anchor(0.15f, 1.0f)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.logo_tail)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 16));
        } else {
            Toast.makeText(this, "Unable to fetch the current location", Toast.LENGTH_SHORT).show();
        }

        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
    }

    public void choosePokemon(LatLng latLng) {
        Intent intent = new Intent(this, AddPokemonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("latLng", latLng);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    public void addPokemonLocation(LatLng latLng, String id) {
        int newImage = 0;
        String newName = null;

        Cursor cursor = db.query("Pokemons", new String[]{"image", "name"},
                "id = '" + id + "'", null, null, null, null);
        if (cursor.moveToFirst()) {
            newImage = cursor.getInt(cursor.getColumnIndex("image"));
            newName = cursor.getString(cursor.getColumnIndex("name"));
        }
        cursor.close();

        Bitmap smallMarker = reSize(newImage);

        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .anchor(.5f, .5f)
                .position(latLng)
                .title(newName));
        storePokemonLocation(latLng, itemOnClicked);
    }

    public void storePokemonLocation(LatLng latLng, String id) {
        values.put("latitude", latLng.latitude);
        values.put("longitude", latLng.longitude);
        values.put("pokemon_id", id);
        db.insert("PokemonLocation", null, values);
        values.clear();
    }

    public void showPokemonLocation(LatLng latLng, String id) {
        int image = 0;
        String name = null;
        Cursor cursor = db.query("Pokemons", new String[]{"name", "image"},
                "id = '" + id + "'", null, null, null, null);
        if (cursor.moveToFirst()) {
            image = cursor.getInt(cursor.getColumnIndex("image"));
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        cursor.close();

        Bitmap smallMarker = reSize(image);

        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .anchor(.5f, .5f)
                .title(name)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
    }

    public Bitmap reSize(int image) {
        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(image);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        return smallMarker;
    }
}
