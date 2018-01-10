package za.co.immedia;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

/*import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import za.co.immedia.Global.MyLog;*/

public class MapsActivity extends FragmentActivity  {
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
/*
    MyLog MyLog =new MyLog();
    private GoogleMap mMap;
    private Double Longitude=0.0;
    private Double Latitude=0.0;
    private String urlImage="";
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

/*

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Longitude = Double.valueOf(getIntent().getStringExtra("Longitude"));
        Latitude= Double.valueOf(getIntent().getStringExtra("Latitude"));
        urlImage= getIntent().getStringExtra("urlImage");
*/


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Latitude, Longitude);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom + 17f));
//        GroundOverlayOptions newarkMap = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromBitmap(bitmap)).anchor(0, 1).position(new LatLng(-33.924869, 18.424055), 8600f, 6500f);
//        mMap.addGroundOverlay(newarkMap);
//        Picasso.with(this).load("https://www.zimeye.net/wp-content/uploads/2017/05/mat-north-schools-218x150.jpg")
        Picasso.with(this).load(urlImage)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        // loaded bitmap is here (bitmap)
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Latitude, Longitude))
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));;
                        MyLog.e("Entity Response : Picasso :", "from "+from);

      *//*                  // Add a marker in Sydney and move the camera
                        LatLng sydney = new LatLng(-33.924869, 18.424055);
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                        GroundOverlayOptions newarkMap = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromBitmap(bitmap)).anchor(0, 1).position(new LatLng(-33.924869, 18.424055), 8600f, 6500f);
                        mMap.addGroundOverlay(newarkMap);*//*
//
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        MyLog.e("Entity Response : errorDrawable :", "errorDrawable "+urlImage);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        MyLog.e("Entity Response : onPostExecute :", "");
                    }
                });
    }*/
}
