package za.co.immedia.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import za.co.immedia.Global.MyLog;
import za.co.immedia.PhotoListActivity;
import za.co.immedia.R;

/**
 * Created by Umpoe on 12/08/2017.
 */

public class MainPlacesListAdapter extends BaseAdapter {
    private Activity activity;
    private JSONArray jsonArrayPlaces=new JSONArray();
    MyLog MyLog=new MyLog();

    public MainPlacesListAdapter(Activity a, JSONArray j){
        this.activity=a;
        this.jsonArrayPlaces=j;
        MyLog.e("Entity Response : Adapter :", "");
    }
    public MainPlacesListAdapter(){

    }

    @Override
    public int getCount() {
        return this.jsonArrayPlaces.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v;
        RecyclerView.ViewHolder viewholder = null;
        if(convertView==null){
            LayoutInflater li = this.activity.getLayoutInflater();
            v = li.inflate(R.layout.adapter_mainplaceslist_layout, null);
        }
        else
        {
            v = convertView;
        }

        ImageView ivIcon = (ImageView)v.findViewById(R.id.ivIcon);
        TextView tvLocationName = (TextView)v.findViewById(R.id.tvLocationName);
        TextView tvLocationAddress = (TextView)v.findViewById(R.id.tvLocationAddress);
        TextView tvCategory = (TextView)v.findViewById(R.id.tvCategory);
        TextView tvPhone = (TextView)v.findViewById(R.id.tvPhone);


        String Longitude="";
        String Latitude="";





        try {
            JSONObject jsonObject=this.jsonArrayPlaces.getJSONObject(position);


            tvLocationName.setText(jsonObject.getString("name"));


            //JSONArray   jsonArray= jsonObject.getJSONObject("response").getJSONArray("venues");

            try{
                JSONObject objectLocation = (JSONObject) new JSONTokener(jsonObject.getString("location")).nextValue();
                tvLocationAddress.setText(objectLocation.getString("address"));


                Longitude=objectLocation.getString("lng");
                Latitude=objectLocation.getString("lat");}catch(Exception a){  MyLog.StackTrace(a);}

            try{    JSONArray jsonArray = new JSONArray(jsonObject.getString("categories"));
                tvCategory.setText(jsonArray.getJSONObject(0).getString("name"));}catch(JSONException b){  MyLog.StackTrace(b);}
            try{
                JSONObject objectLocation = (JSONObject) new JSONTokener(jsonObject.getString("contact")).nextValue();
                tvPhone.setText(objectLocation.getString("phone"));}catch(Exception c){  MyLog.StackTrace(c);}

            final  String clickLongitude=Longitude;
            final  String clickLatitude=Latitude;
            final  String Placename=tvLocationName.getText().toString();


            if (!Longitude.isEmpty()&&!Latitude.isEmpty()){
                v.setBackgroundColor(activity.getResources().getColor(R.color.PlacesWhite));
            }else{
                v.setBackgroundColor(activity.getResources().getColor(R.color.PlacesGray));
            }
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(activity, "Clicked this: "+clickLongitude+" "+clickLatitude, Toast.LENGTH_SHORT).show();
                    if(clickLongitude.length()<3||clickLatitude.length()<3){
                        MyLog.MyDialogue(activity,"Please note that this location has no gps data associated with it at the moment. please keep checking for changes.", "Alert");
                    }else{
                        Intent intent =new Intent(activity, PhotoListActivity.class);
                        intent.putExtra("clickLongitude", clickLongitude);
                        intent.putExtra("clickLatitude", clickLatitude);
                        intent.putExtra("name",Placename );
                        activity.startActivity(intent);
                    }
                }
            });

        } catch (Exception e) {
           MyLog.StackTrace(e);
            //tvLocationName.setText("non");
        }


        return v;
    }
}
