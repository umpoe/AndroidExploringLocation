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

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.immedia.Global.MyLog;
import za.co.immedia.R;
import za.co.immedia.ViewImageActivity;

/**
 * Created by Umpoe on 12/08/2017.
 */

public class PhotosListAdapter extends BaseAdapter {
    private Activity activity;
    private JSONArray jsonArrayPlaces=new JSONArray();
    MyLog MyLog=new MyLog();
    String Longitude="";
    String Latitude="";

    public PhotosListAdapter(Activity a, JSONArray j, String Longitude, String Latitude){
        this.activity=a;
        this.jsonArrayPlaces=j;
        this.Longitude=Longitude;
        this.Latitude=Latitude;
        MyLog.e("Entity Response : Adapter :", "");

    }
    public PhotosListAdapter(){

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
            v = li.inflate(R.layout.adapter_photoslist_layout, null);

        }
        else
        {

            v = convertView;

        }
//        v.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 200));

        ImageView ivIcon = (ImageView)v.findViewById(R.id.ivIcon);
        TextView tvTitle = (TextView)v.findViewById(R.id.tvTitle);


        try {
            JSONObject jsonObject=this.jsonArrayPlaces.getJSONObject(position);

            final String photoId = jsonObject.getString("id");
            String photoFarm = jsonObject.getString("farm");
            String photoServerId = jsonObject.getString("server");
            final String photoSecret = jsonObject.getString("secret");
            final String photoTitle = jsonObject.getString("title");
            String photoOwner = jsonObject.getString("owner");
            String photoSize = "_n";

            final String urlimage="http://farm"+photoFarm+".staticflickr.com/"+photoServerId+"/"+photoId+"_"+photoSecret+photoSize+".jpg";
//            Picasso.with(activity).load(urlimage).into(ivIcon);.transform(new RoundedCornersTransform(this)).into(imageView)
            Picasso.with(activity).load(urlimage).resize(300, 300).centerCrop().into(ivIcon);
            tvTitle.setText(photoTitle);
            /*try{
                JSONObject objectLocation = (JSONObject) new JSONTokener(jsonObject.getString("location")).nextValue();
                tvLocationAddress.setText(objectLocation.getString("address"));
                Longitude=jsonObject.getJSONObject("location").getString("lng");
                Latitude=jsonObject.getJSONObject("location").getString("lat");}catch(Exception a){  a.printStackTrace();}


           if (!Longitude.isEmpty()&&!Latitude.isEmpty()){
                v.setBackgroundColor(activity.getResources().getColor(R.color.PlacesWhite));
            }else{
                v.setBackgroundColor(activity.getResources().getColor(R.color.PlacesGray));
            }*/

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(activity, "Clicked this: "+clickLongitude+" "+clickLatitude, Toast.LENGTH_SHORT).show();
                    if(urlimage.length()<0){
//                        MyLog.MyDialogue(activity,"Please note that this location has no gps data associated with it at the moment. please keep checking for changes.", "Alert");
                    }else{
                        Intent intent =new Intent(activity, ViewImageActivity.class);
                        intent.putExtra("urlImage", urlimage);
                        intent.putExtra("photoId", photoId);
                        intent.putExtra("photoSecret", photoSecret);
                        intent.putExtra("photoTitle", photoTitle);
                        intent.putExtra("Longitude", Longitude);
                        intent.putExtra("Latitude", Latitude);
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
