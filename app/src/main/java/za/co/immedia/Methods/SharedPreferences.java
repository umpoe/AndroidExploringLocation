package za.co.immedia.Methods;

import android.content.Context;

import za.co.immedia.Global.MyLog;


public class SharedPreferences {
    static MyLog MyLog= new MyLog();

    int MODE_PRIVATE=0;
   static int MODE_PRIVATE_STATIC=0;


    public static void writeStringPrefs(Context context, String key,String string, String arrayStringKey){
        android.content.SharedPreferences settings = context.getSharedPreferences(key, MODE_PRIVATE_STATIC);
        android.content.SharedPreferences.Editor editor = settings.edit();
        editor.putString(arrayStringKey, string);

        editor.apply();
    }
    public static String readStringPrefs(Context context, String key, String arrayStringKey){
        String string="";
        android.content.SharedPreferences settings = context.getSharedPreferences(key, MODE_PRIVATE_STATIC);
        try {
            //arrayList=new ArrayList<String>(settings.getString(arrayStringKey, ""));
            string=settings.getString(arrayStringKey, "");
        } catch (Exception e) {
            MyLog.StackTrace(e);
            string="";
        }
        return string;
    }



}
