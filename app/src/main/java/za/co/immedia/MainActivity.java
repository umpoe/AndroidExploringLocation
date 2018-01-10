package za.co.immedia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import za.co.immedia.Adapters.MainPlacesListAdapter;
import za.co.immedia.Classes.MyClassLatLng;
import za.co.immedia.Classes.ReturnColor;
import za.co.immedia.Global.MyLog;
import za.co.immedia.Methods.ApiConnector;
import za.co.immedia.Methods.SharedPreferences;
import za.co.immedia.Methods.WebViewWebsiteActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    SharedPreferences SharedPreferences=new SharedPreferences();
    ReturnColor ReturnColor=new ReturnColor();
    String colorPrimary="";
    Toolbar toolbar;
    MyLog MyLog=new MyLog();
    MyClassLatLng myClassLatLng=new MyClassLatLng();
    TextView TextViewLatLong;



    final String gpsLocationProvider = LocationManager.GPS_PROVIDER;
    final String networkLocationProvider = LocationManager.NETWORK_PROVIDER;
    String wantPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private String TAG="minala";


    String Longitude="";
    String Latitude="";

    ListView ListViewPlaces;

    MainPlacesListAdapter MainPlacesListAdapter=new MainPlacesListAdapter();
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        colorPrimary=SharedPreferences.readStringPrefs(MainActivity.this, "InitialisedColor", "key");
        toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(MainActivity.this,colorPrimary));

        TextViewLatLong=(TextView)findViewById(R.id.TextViewLatLong);
        ListViewPlaces=(ListView)findViewById(R.id.ListViewPlaces);


        /*LocationRequest  mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(30000); //5 seconds
        mLocationRequest.setFastestInterval(30000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);*/

        TextViewLatLong.setVisibility(View.GONE);

        InitialiseLatLong();

        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setBackgroundColor(ReturnColor.ReturnPrimary(MainActivity.this,""));

        fab.setBackgroundTintList(ColorStateList.valueOf(ReturnColor.ReturnColorAccent(MainActivity.this,colorPrimary)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I hope my application will be taken into your favourable consideration.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                InitialiseLatLong();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void InitialiseLatLong(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkPermission(wantPermission)) {
            Location lastKnownLocationGps = locationManager.getLastKnownLocation(gpsLocationProvider);
            Location lastKnownLocationNetwork = locationManager.getLastKnownLocation(networkLocationProvider);

            if (lastKnownLocationGps == null) {
                MyLog.d(TAG, "NO GPS");

                if (lastKnownLocationNetwork == null) {
                    MyLog.d(TAG, "NO Network");
                    MyLog.d(TAG, "NO Location!");
                } else {
                    Latitude =""+lastKnownLocationNetwork.getLatitude();
                    Longitude =""+lastKnownLocationNetwork.getLongitude();
                    MyLog.d(TAG, "Network " + lastKnownLocationNetwork.toString());
                    MyLog.d(TAG, "Location (Network)" + lastKnownLocationNetwork.getLatitude() + ", " + lastKnownLocationNetwork.getLongitude());
                }
            } else {
                MyLog.d(TAG, "GPS " + lastKnownLocationGps.toString());

                if (lastKnownLocationNetwork == null) {
                    MyLog.d(TAG, "NO Network");
                    MyLog.d(TAG, "Location (GPS) " + lastKnownLocationGps.getLatitude() + ", " + lastKnownLocationGps.getLongitude());
                    MyLog.d(TAG, "Time (GPS) " + lastKnownLocationGps.getTime());
                    MyLog.d(TAG, "Accuracy (GPS) " + lastKnownLocationGps.getAccuracy());
                    Latitude =""+lastKnownLocationGps.getLatitude();
                    Longitude =""+lastKnownLocationGps.getLongitude();
                } else {
                    MyLog.d(TAG, "Network " + lastKnownLocationNetwork.toString());

                    //Both Location provider have last location decide location base on accuracy
                    if (lastKnownLocationGps.getAccuracy() <= lastKnownLocationNetwork.getAccuracy()) {
                        MyLog.d(TAG, "Location (GPS) " + lastKnownLocationGps.getLatitude() + ", " + lastKnownLocationGps.getLongitude());
                        Latitude =""+lastKnownLocationGps.getLatitude();
                        Longitude =""+lastKnownLocationGps.getLongitude();
                    } else {
                        MyLog.d(TAG, "Location (Network) " + lastKnownLocationNetwork.getLatitude() + ", " + lastKnownLocationNetwork.getLongitude());
                        Latitude =""+lastKnownLocationNetwork.getLatitude();
                        Longitude =""+lastKnownLocationNetwork.getLongitude();
                    }

                }
            }
        }

/*
        if(myClassLatLng.GetStatusLocation().contains(" Please enable location on your device")){
            TextViewLatLong.setText(myClassLatLng.GetStatusLocation());
        }else{
            String LatLong=""+Latitude+ Longitude;
            TextViewLatLong.setText(LatLong);
        }
*/


        if(Latitude.length()>3&&Longitude.length()>3){
            new GetPlacesArray().execute(new ApiConnector());
        }else{
            MyLog.MyDialogue(MainActivity.this, "There seems to be a problem. Please ensure you activate Location on your device, make sure you give the application permission to use your " +
                    "location and also make sure you ave access to internet services. Having done the above please click on the message button on the bottom right conner of the phone. Thank you.", "Hello");
        }

    }



    private boolean checkPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(MainActivity.this, permission);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        colorPrimary=SharedPreferences.readStringPrefs(MainActivity.this, "InitialisedColor", "key");
        toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(MainActivity.this,colorPrimary));
        fab.setBackgroundTintList(ColorStateList.valueOf(ReturnColor.ReturnColorAccent(MainActivity.this,colorPrimary)));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(MainActivity.this, ChangeColorActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            SharedPreferences.writeStringPrefs(MainActivity.this, "welcome","" ,"lockkey");
            SharedPreferences.writeStringPrefs(MainActivity.this, "InitialisedColor","" ,"key");
            Intent intent=new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_callme) {
             Intent intent = new Intent(Intent.ACTION_DIAL);
             intent.setData(Uri.parse("tel:0027603537171"));
             startActivity(intent);
         } else if (id == R.id.nav_Implemention) {
             Intent intent=new Intent(MainActivity.this, ImplementationActivity.class);
             startActivity(intent);

        } else if (id == R.id.nav_website) {
             Intent intent=new Intent(MainActivity.this, WebViewWebsiteActivity.class);
             startActivity(intent);

        } else if (id == R.id.nav_send) {
             Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
             txtIntent .setType("text/plain");
             txtIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "Thank you");
             txtIntent .putExtra(android.content.Intent.EXTRA_TEXT, "I have this lovely Immedia App please do check it out");
             startActivity(Intent.createChooser(txtIntent ,"Share"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private class GetPlacesArray extends AsyncTask<ApiConnector, Long, JSONArray> {

        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            //executed in background

            return params[0].GetPlacesArray(MainActivity.this, Longitude, Latitude);
        }

        @Override
        protected void onPostExecute(JSONArray jsonObject) {
            super.onPostExecute(jsonObject);
            //executed in main thread
//            ProgressBarLoad.setVisibility(View.GONE);
            if(jsonObject==null){
                MyLog.e("Entity Response : jsonArray null :", "");
            }else{
                try {
                    //jsonArray.getJSONObject(0);
//                    JSONArray jsonArray= jsonObject.getJSONObject("response").getJSONArray("venues");
                    jsonObject.getJSONObject(0);
                    //TextViewLatLong.setText(""+jsonObject);
                    MyLog.e("Entity Response : onPostExecute :", ""+jsonObject.length());
                    MainPlacesListAdapter=new MainPlacesListAdapter(MainActivity.this,jsonObject);
                    ListViewPlaces.setAdapter(MainPlacesListAdapter);


                } catch (Exception e) {
                    MyLog.StackTrace(e);
                }

            }

        }
    }







}

