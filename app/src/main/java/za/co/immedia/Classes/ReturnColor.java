package za.co.immedia.Classes;

import android.content.Context;

import za.co.immedia.R;

/**
 * Created by Umpoe on 12/10/2017.
 */

public class ReturnColor {

    int colorPrimary;
    int colorPrimaryDark;
    int colorAccent;

   public int ReturnColorAccent(Context context, String value){
       if(value.equalsIgnoreCase("1")){
           colorAccent= context.getResources().getColor(R.color.colorAccent1);
       }else if(value.equalsIgnoreCase("2")){
           colorAccent= context.getResources().getColor(R.color.colorAccent2);
       }else{
           colorAccent= context.getResources().getColor(R.color.colorAccent);
       }
     return colorAccent;
   }
   public int ReturnPrimaryDark(Context context, String value){
       if(value.equalsIgnoreCase("1")){
           colorPrimaryDark= context.getResources().getColor(R.color.colorPrimaryDark1);
       }else if(value.equalsIgnoreCase("2")){
           colorPrimaryDark= context.getResources().getColor(R.color.colorPrimaryDark2);
       }else{
           colorPrimaryDark= context.getResources().getColor(R.color.colorPrimaryDark);
       }
     return colorPrimaryDark;
   }
   public int ReturnPrimary(Context context, String value){
       if(value.equalsIgnoreCase("1")){
           colorPrimary= context.getResources().getColor(R.color.colorPrimary1);
       }else if(value.equalsIgnoreCase("2")){
           colorPrimary= context.getResources().getColor(R.color.colorPrimary2);
       }else{
           colorPrimary= context.getResources().getColor(R.color.colorPrimary);
       }
     return colorPrimary;
   }


}
