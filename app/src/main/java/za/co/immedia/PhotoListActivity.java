package za.co.immedia;

import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import za.co.immedia.Adapters.PhotosListAdapter;
import za.co.immedia.Classes.ReturnColor;
import za.co.immedia.Global.MyLog;
import za.co.immedia.Methods.ApiConnector;
import za.co.immedia.Methods.SharedPreferences;

public class PhotoListActivity extends AppCompatActivity {
    za.co.immedia.Methods.SharedPreferences SharedPreferences=new SharedPreferences();
    za.co.immedia.Classes.ReturnColor ReturnColor=new ReturnColor();
    String colorPrimary="";
    MyLog MyLog=new MyLog();
     String Longitude="";
     String Latitude="";
     String name="";
     int numberOfPages=2;
     int pageNumber=1;
    FloatingActionButton floatingActionButtonNext;
    FloatingActionButton floatingActionButtonPrevious;
    GridView GridViewPhotos;
    PhotosListAdapter PhotosListAdapter=new PhotosListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        colorPrimary=SharedPreferences.readStringPrefs(PhotoListActivity.this, "InitialisedColor", "key");
        toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(PhotoListActivity.this,colorPrimary));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Longitude = getIntent().getStringExtra("clickLongitude");
        Latitude= getIntent().getStringExtra("clickLatitude");
        name = getIntent().getStringExtra("name");

        floatingActionButtonNext=(FloatingActionButton)findViewById(R.id.floatingActionButtonNext);
        floatingActionButtonPrevious=(FloatingActionButton)findViewById(R.id.floatingActionButtonPrevious);
        GridViewPhotos=(GridView)findViewById(R.id.GridViewPhotos);


        getSupportActionBar().setTitle(name);

        floatingActionButtonNext.setBackgroundTintList(ColorStateList.valueOf(ReturnColor.ReturnColorAccent(PhotoListActivity.this,colorPrimary)));
        floatingActionButtonPrevious.setBackgroundTintList(ColorStateList.valueOf(ReturnColor.ReturnColorAccent(PhotoListActivity.this,colorPrimary)));


        new GetPhotosArray().execute(new ApiConnector());


        floatingActionButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pageNumber!=numberOfPages){
                    ++pageNumber;
                    new GetPhotosArray().execute(new ApiConnector());
                    floatingActionButtonPrevious.setVisibility(View.VISIBLE);
                }

            }
        });

        if(pageNumber==1){
            floatingActionButtonPrevious.setVisibility(View.GONE);
        }
        floatingActionButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --pageNumber;
                if(pageNumber==1){
                    floatingActionButtonPrevious.setVisibility(View.GONE);
                }
                new GetPhotosArray().execute(new ApiConnector());
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
         super.onSupportNavigateUp();
        finish();
        return true;
    }

    private class GetPhotosArray extends AsyncTask<ApiConnector, Long, JSONObject> {

        @Override
        protected JSONObject doInBackground(ApiConnector... params) {
            //executed in background

            return params[0].GetPhotosArray(PhotoListActivity.this, Longitude, Latitude, pageNumber);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            //executed in main thread
//            ProgressBarLoad.setVisibility(View.GONE);
            if(jsonObject==null){
                MyLog.e("Entity Response : jsonArray null :", "");
            }else{
                try {
                    //jsonArray.getJSONObject(0);
//                    JSONArray jsonArray= jsonObject.getJSONObject("response").getJSONArray("venues");

                    jsonObject.getString("photos");
                    JSONObject jsonObjectPhotos= (JSONObject) new JSONTokener(jsonObject.getString("photos")).nextValue();

                    jsonObjectPhotos.getString("page");
                    jsonObjectPhotos.getString("pages");
                    jsonObjectPhotos.getString("perpage");
                    jsonObjectPhotos.getString("total");
                    numberOfPages= Integer.parseInt(jsonObjectPhotos.getString("pages"));

                    JSONArray jsonArray=new JSONArray(jsonObjectPhotos.getString("photo"));
                    jsonArray.getJSONObject(0);
                    MyLog.e("Entity Response : onPostExecute : jsonArray", ""+jsonArray);
/*                    id : "38921470881"
                    owner : "71036877@N00"
                    secret : "24ba27bc60"
                    server : "4633"
                    farm : 5
                    title : "Barcode"
                    ispublic : 1
                    isfriend : 0
                    isfamily : 0*/

        /*            for(int z=0; z<jsonArray.length(); z++){
                        JSONObject jsonObjectTemp=jsonArray.getJSONObject(z);
                        String photoId = jsonObject.getString("id");
                        String photoFarm = jsonObject.getString("farm");
                        String photoServerId = jsonObject.getString("server");
                        String photoSecret = jsonObject.getString("secret");
                        String photoTitle = jsonObject.getString("title");
                        String photoOwner = jsonObject.getString("owner");
                        String photoSize = "_n";

                        String urlimage="http://farm"+photoFarm+".staticflickr.com/"+photoServerId+"/"+photoId+"_"+photoSecret+photoSize+".jpg";
                        MyLog.e("Entity Response : onPostExecute : urlimage", ""+urlimage);
                        //String urlimage="http://farm5.staticflickr.com/4633/38921470881_24ba27bc60_n.jpg";

                    }*/
                    PhotosListAdapter =new PhotosListAdapter(PhotoListActivity.this, jsonArray, Longitude, Latitude);
                    GridViewPhotos.setAdapter(PhotosListAdapter);


                    //TextViewLatLong.setText(""+jsonObject);
/*


                    MainPlacesListAdapter=new MainPlacesListAdapter(MainActivity.this,jsonObject);
                    ListViewPlaces.setAdapter(MainPlacesListAdapter);
*/


                } catch (Exception e) {
                    MyLog.StackTrace(e);
                }

            }

        }
    }


}
