package com.han.jin.pokmoncatcher;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by JiN on 30/09/16.
 */

public class MapStateManager {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ZOOM = "zoom";
    private static final String BEARING = "bearing";
    private static final String TILT = "tilt";

    private static final String PREFS_NAME = "mapCameraState";

    private SharedPreferences mapStatePrefs;

    public MapStateManager(Context context) {
        mapStatePrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveMapState(GoogleMap map) {
        SharedPreferences.Editor editor = mapStatePrefs.edit();
        CameraPosition position = map.getCameraPosition();

        editor.putFloat(LATITUDE, (float) position.target.latitude);
        editor.putFloat(LONGITUDE, (float) position.target.longitude);
        editor.putFloat(ZOOM, position.zoom);
        editor.putFloat(BEARING, position.bearing);
        editor.putFloat(TILT, position.tilt);

        editor.commit();
    }

    public CameraPosition getCameraPosition() {
        double latitude = mapStatePrefs.getFloat(LATITUDE, 0);
        if (latitude == 0) {
            return null;
        }
        double longitude = mapStatePrefs.getFloat(LONGITUDE, 0);
        LatLng target = new LatLng(latitude, longitude);

        float zoom = mapStatePrefs.getFloat(ZOOM, 0);
        float bearing = mapStatePrefs.getFloat(BEARING, 0);
        float tilt = mapStatePrefs.getFloat(TILT, 0);

        CameraPosition position = new CameraPosition(target, zoom, bearing, tilt);
        return position;
    }

}
