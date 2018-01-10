package za.co.immedia.Methods;


import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import za.co.immedia.Global.MyLog;


public class ApiConnector {
    MyLog MyLog=new MyLog();
    Methods methods=new Methods();



    public JSONArray GetPlacesArray(Context context, String Longitude, String Latitude) {
/*
        Latitude="-29.858680";
        Longitude="31.021840";*/
        String stringurl = "https://api.foursquare.com/v2/venues/search?v=20161016&ll="+Latitude+"%2C%20"+Longitude+"&intent=checkin&client_id=QQZM0HOEHMD5ZAGFGE2D3LPXFQELK5QOJQU10BANHL41TPFE&client_secret=4Z4GJCW5J3SBG4OMKPI511OMLR2Q4B4IBHQIUWW42BQ2IRXI " ;
//        String stringurl = "https://api.foursquare.com/v2/venues/search?v=20161016&ll="+Latitude+"%2C%20"+Longitude+"&query=coffee&intent=checkin&client_id=QQZM0HOEHMD5ZAGFGE2D3LPXFQELK5QOJQU10BANHL41TPFE&client_secret=4Z4GJCW5J3SBG4OMKPI511OMLR2Q4B4IBHQIUWW42BQ2IRXI " ;
//        String stringurl = "https://api.foursquare.com/v2/venues/search?oauth_token=DCTPRLS5UDNPXUK0QLP0DFDL0Q0XMEXPHPMIZN5FSNKWLC3F&v=20131016&ll="+Latitude+"%2C"+Longitude+"&intent=checkin " ;
//        String stringurl = "/RegisterNewUser.php?fn=" + Longitude + "&usn=" + Latitude ;
        MyLog.d("url", stringurl);
        JSONArray jsonArray=null;
        JSONArray jsonObjectFinal=null;
        try {
            URL url = new URL(stringurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // read the response
            MyLog.e("Response Code : ", ""+conn.getResponseCode());
            InputStream in = new BufferedInputStream(conn.getInputStream());
//            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            String response = readFullyAsString(in,"UTF-8" );
            System.out.println(response);
            MyLog.e("Response response : ", ""+response);

            try {

                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                //MyLog.e("Entity Response : object :", ""+object);

               /* JSONObject jsonObject= (JSONObject) new JSONTokener(object.getString("response")).nextValue();
                jsonArray=jsonObject.getJSONArray("venues");*/


//                MyLog.e("Entity Response : object.getJSONObject :", ""+object.getJSONObject("response"));
//                jsonArray = new JSONArray(response);
                //jsonObjectFinal=jsonArray.getJSONObject(0);
                //jsonObjectFinal=object.getJSONObject("response").getJSONArray("venues").getJSONObject(0);
                JSONObject jsonObject =object.getJSONObject("response");
                JSONArray jsonArray1=jsonObject.getJSONArray("venues");
                MyLog.e("Entity Response : ", "here");
//                MyLog.e("Entity Response : jsonArray1 :", ""+jsonArray1);
                jsonObjectFinal=jsonArray1;
                //methods.writePlacesJSONObject(context, jsonObjectFinal);
                MyLog.e("Entity Response : jsonObjectFinal :", ""+jsonObjectFinal);
            } catch (JSONException e) {
                MyLog.StackTrace(e);
            }

        } catch (ProtocolException e) {
            MyLog.StackTrace(e);
        } catch (MalformedURLException e) {
            MyLog.StackTrace(e);
        } catch (IOException e) {
            MyLog.StackTrace(e);
        }




        return jsonObjectFinal;
    }

    public JSONObject GetPhotosDetailsArray(Context context, String photoId, String Secret) {

        String stringurl = "https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=4ca7bdd041f6899062925efb51e1fffe&photo_id="+photoId+"&secret="+Secret+"&format=json&nojsoncallback=1" ;
//        String stringurl = "https://api.foursquare.com/v2/venues/search?v=20161016&ll="+Latitude+"%2C%20"+Longitude+"&query=coffee&intent=checkin&client_id=QQZM0HOEHMD5ZAGFGE2D3LPXFQELK5QOJQU10BANHL41TPFE&client_secret=4Z4GJCW5J3SBG4OMKPI511OMLR2Q4B4IBHQIUWW42BQ2IRXI " ;
//        String stringurl = "https://api.foursquare.com/v2/venues/search?oauth_token=DCTPRLS5UDNPXUK0QLP0DFDL0Q0XMEXPHPMIZN5FSNKWLC3F&v=20131016&ll="+Latitude+"%2C"+Longitude+"&intent=checkin " ;
//        String stringurl = "/RegisterNewUser.php?fn=" + Longitude + "&usn=" + Latitude ;
        MyLog.d("url", stringurl);
        JSONArray jsonArray=null;
        JSONObject jsonObjectFinal=null;
        try {
            URL url = new URL(stringurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // read the response
            System.out.println("Response Code: " + conn.getResponseCode());
            MyLog.e("Response Code :", ""+conn.getResponseCode());
            InputStream in = new BufferedInputStream(conn.getInputStream());
//            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            String response = readFullyAsString(in,"UTF-8" );
            System.out.println(response);
            MyLog.e("Response response :", ""+response);

            try {

                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                jsonObjectFinal=object;
                MyLog.e("Entity Response : jsonObjectFinal :", ""+jsonObjectFinal);
            } catch (JSONException e) {
                MyLog.StackTrace(e);
            }

        } catch (ProtocolException e) {
            MyLog.StackTrace(e);
        } catch (MalformedURLException e) {
            MyLog.StackTrace(e);
        } catch (IOException e) {
            MyLog.StackTrace(e);
        }




        return jsonObjectFinal;
    }


    public JSONObject GetPhotosArray(Context context, String Longitude, String Latitude, int pageNumber) {
/*
        Latitude="-29.858680";
        Longitude="31.021840";*/
        String stringurl = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=4ca7bdd041f6899062925efb51e1fffe&lat="+Latitude+"&lon="+Longitude+"&per_page=10&page="+pageNumber+"&format=json&nojsoncallback=1" ;
//        String stringurl = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=4ca7bdd041f6899062925efb51e1fffe&lat="+Latitude+"&lon="+Longitude+"&per_page=10&&format=json&nojsoncallback=1";
//        String stringurl = "https://api.foursquare.com/v2/venues/search?v=20161016&ll="+Latitude+"%2C%20"+Longitude+"&query=coffee&intent=checkin&client_id=QQZM0HOEHMD5ZAGFGE2D3LPXFQELK5QOJQU10BANHL41TPFE&client_secret=4Z4GJCW5J3SBG4OMKPI511OMLR2Q4B4IBHQIUWW42BQ2IRXI " ;
//        String stringurl = "https://api.foursquare.com/v2/venues/search?oauth_token=DCTPRLS5UDNPXUK0QLP0DFDL0Q0XMEXPHPMIZN5FSNKWLC3F&v=20131016&ll="+Latitude+"%2C"+Longitude+"&intent=checkin " ;
//        String stringurl = "/RegisterNewUser.php?fn=" + Longitude + "&usn=" + Latitude ;
        MyLog.d("url", stringurl);
        JSONArray jsonArray=null;
        JSONObject jsonObjectFinal=null;
        try {
            URL url = new URL(stringurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // read the response
            MyLog.e("Response Code :", ""+ conn.getResponseCode());
            InputStream in = new BufferedInputStream(conn.getInputStream());
//            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            String response = readFullyAsString(in,"UTF-8" );
            MyLog.e("Response response :", ""+ response);
            try {
                //jsonArray = new JSONArray(response);
                //MyLog.e("Entity Response : jsonObjectFinal :", ""+jsonArray);
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                MyLog.e("Entity Response : object :", ""+object);
                jsonObjectFinal=object;
/*

               JSONObject jsonObject= (JSONObject) new JSONTokener(object.getString("response")).nextValue();
                jsonArray=jsonObject.getJSONArray("venues");
*/


//                MyLog.e("Entity Response : object.getJSONObject :", ""+object.getJSONObject("response"));
//                jsonArray = new JSONArray(response);
                //jsonObjectFinal=jsonArray.getJSONObject(0);
                //jsonObjectFinal=object.getJSONObject("response").getJSONArray("venues").getJSONObject(0);
               /* JSONObject jsonObject =object.getJSONObject("response");
                JSONArray jsonArray1=jsonObject.getJSONArray("venues");
                MyLog.e("Entity Response : ", "here");
//                MyLog.e("Entity Response : jsonArray1 :", ""+jsonArray1);
                jsonObjectFinal=jsonArray1;*/
                //methods.writePlacesJSONObject(context, jsonObjectFinal);*/

            } catch (JSONException e) {
                MyLog.StackTrace(e);
            }

        } catch (ProtocolException e) {
            MyLog.StackTrace(e);
        } catch (MalformedURLException e) {
            MyLog.StackTrace(e);
        } catch (IOException e) {
            MyLog.StackTrace(e);
        }




        return jsonObjectFinal;
//        return jsonArray;
    }




    public String readFullyAsString(InputStream inputStream, String encoding)
            throws IOException {
        return readFully(inputStream).toString(encoding);
    }

    public byte[] readFullyAsBytes(InputStream inputStream)
            throws IOException {
        return readFully(inputStream).toByteArray();
    }

    private ByteArrayOutputStream readFully(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos;
    }



    public JSONArray CheckDuplicateValues(JSONArray jsonArray){
        JSONArray finalJarray=new JSONArray();
        JSONArray jsonArrayTemp=jsonArray;

        for(int x=0; x<jsonArray.length(); x++){
            try {
                JSONObject jsonObject=jsonArray.getJSONObject(x);
                int checkIfExists=0;
                for(int z=0; z<jsonArray.length(); z++){
                    JSONObject jsonObjectTemp=jsonArray.getJSONObject(z);
                    if(jsonObject.getString("name").equalsIgnoreCase(jsonObjectTemp.getString("name"))){
                        checkIfExists++;
                    }
                }

                if(checkIfExists==1){
                    finalJarray.put(jsonObject);
                    MyLog.d("CheckDuplicateValues", "CheckDuplicateValues "+jsonObject);
                }
                checkIfExists=0;
                MyLog.d("CheckDuplicateValuesX", "x "+x);

            } catch (Exception e) {
                MyLog.StackTrace(e);
            }

        }



        return  finalJarray;
    }

}



