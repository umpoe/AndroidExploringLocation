package za.co.immedia.Methods;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Display;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import za.co.immedia.Global.MyLog;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;


public class Methods {


    MyLog MyLog=new MyLog();




    public void writePlacesJSONObject(Context context,JSONObject jsonObject) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("places", MODE_PRIVATE)));

            String eol = System.getProperty("line.separator");
            writer.write(jsonObject + eol);

            writer.close();

        } catch (FileNotFoundException e) {
            MyLog.StackTrace(e);
        } catch (IOException e) {
            MyLog.StackTrace(e);
        }

    }


    public JSONObject readLoginDetailsJSONObject(Context context) {
        JSONObject jsonObject = new JSONObject();
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new InputStreamReader(context.openFileInput("places" )));
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    jsonObject = (JSONObject) new JSONTokener(line).nextValue();

                } catch (JSONException e) {
                    MyLog.StackTrace(e);
                    jsonObject = null;
                    return jsonObject;
                }

            }


            reader.close();
        } catch (FileNotFoundException e) {
            MyLog.StackTrace(e);
            jsonObject = null;
            return jsonObject;
        } catch (IOException e) {
            MyLog.StackTrace(e);
            jsonObject = null;
            return jsonObject;
        }
        return jsonObject;
    }


}