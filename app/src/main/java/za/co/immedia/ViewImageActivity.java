package za.co.immedia;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import za.co.immedia.Adapters.PhotosListAdapter;
import za.co.immedia.Classes.ReturnColor;
import za.co.immedia.Global.MyLog;
import za.co.immedia.Methods.ApiConnector;
import za.co.immedia.Methods.SharedPreferences;

public class ViewImageActivity extends AppCompatActivity {
    za.co.immedia.Methods.SharedPreferences SharedPreferences=new SharedPreferences();
    za.co.immedia.Classes.ReturnColor ReturnColor=new ReturnColor();
    String colorPrimary="";
    MyLog MyLog=new MyLog();
    PhotoView photoView;
    String urlImage="";
    String photoId="";
    String photoSecret="";
    String photoTitle="";
    String photoDateTaken="";
    TextView tvTitle, tvUserName, tvDescription;
    FloatingActionButton floatingActionButtonViewMap;

    String Longitude="";
    String Latitude="";
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        photoView = (PhotoView) findViewById(R.id.photo_view);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        floatingActionButtonViewMap = (FloatingActionButton) findViewById(R.id.floatingActionButtonViewMap);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);



        urlImage = getIntent().getStringExtra("urlImage");
        photoId = getIntent().getStringExtra("photoId");
        photoSecret = getIntent().getStringExtra("photoSecret");
        photoTitle = getIntent().getStringExtra("photoTitle");
        Longitude = getIntent().getStringExtra("Longitude");
        Latitude= getIntent().getStringExtra("Latitude");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(photoTitle);

        colorPrimary=SharedPreferences.readStringPrefs(ViewImageActivity.this, "InitialisedColor", "key");
        toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(ViewImageActivity.this,colorPrimary));

        if(urlImage!=null &&urlImage.length()>3 ){
            Picasso.with(ViewImageActivity.this).load(urlImage).into(photoView);
        }

        if(photoId!=null&&photoId.length()!=0&&photoSecret.length()!=0){
            new GetPhotosDetailsArray().execute(new ApiConnector());
        }

        floatingActionButtonViewMap.setVisibility(View.GONE);

        floatingActionButtonViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent =new Intent(ViewImageActivity.this, MapsActivity.class);
                intent.putExtra("Longitude", Longitude);
                intent.putExtra("Latitude", Latitude);
                intent.putExtra("urlImage", urlImage);
                startActivity(intent);*/

                /*FragmentActivity TargetFragment=new MapsActivity();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content,TargetFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
         super.onSupportNavigateUp();
         finish();
         return true;
    }

    private class GetPhotosDetailsArray extends AsyncTask<ApiConnector, Long, JSONObject> {

        @Override
        protected JSONObject doInBackground(ApiConnector... params) {
            //executed in background


            return params[0].GetPhotosDetailsArray(ViewImageActivity.this, photoId, photoSecret);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            //executed in main thread
//            ProgressBarLoad.setVisibility(View.GONE);
            if(jsonObject==null){
                MyLog.e("Entity Response : jsonObject null :", "");
            }else{
                try {
                    MyLog.e("Entity Response : jsonObject : "+jsonObject, "");
                    JSONObject jsonObjectPhoto =jsonObject.getJSONObject("photo");
                    JSONObject jsonObjectDates = (JSONObject) new JSONTokener(jsonObjectPhoto.getString("dates")).nextValue();
                    JSONObject jsonObjectDescription = (JSONObject) new JSONTokener(jsonObjectPhoto.getString("description")).nextValue();
                    JSONObject jsonObjectLocation = (JSONObject) new JSONTokener(jsonObjectPhoto.getString("location")).nextValue();
                    JSONObject jsonObjectowner=(JSONObject) new JSONTokener(jsonObjectPhoto.getString("owner")).nextValue();

                    Longitude = jsonObjectLocation.getString("longitude");
                    Latitude= jsonObjectLocation.getString("latitude");
//                    floatingActionButtonViewMap.setVisibility(View.VISIBLE);



                    MyLog.e("jsonObjectPhoto : ", ""+jsonObjectPhoto);
                    MyLog.e("jsonObjectDates : ", ""+jsonObjectDates);
                    MyLog.e("jsonObjectDescription : ", ""+jsonObjectDescription);

                    tvDescription.setText(jsonObjectDescription.getString("_content"));
                    tvUserName.setText(jsonObjectowner.getString("username"));


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date d = sdf.parse(jsonObjectDates.getString("taken"));
                        sdf.applyPattern("dd/MM/yyyy hh:mm");
                        photoDateTaken=sdf.format(d);
                        tvTitle.setText(photoDateTaken);
                    } catch (ParseException ex) {
                        MyLog.StackTrace(ex);
                    }

                } catch (Exception e) {
                    MyLog.StackTrace(e);
                }

            }

        }
    }

}
