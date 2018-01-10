package za.co.immedia.Global;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by Umpoe on 3/3/2017.
 */

public class MyLog {
    Boolean logBoolean=false;
    Boolean StackTraceBoolean=false;
    public void e(String StringTag, String StringContent){
     if(logBoolean){
         Log.e(StringTag, StringContent);
     }
    }

    public void d(String StringTag, String StringContent){
     if(logBoolean){
         Log.d(StringTag, StringContent);
     }
    }

    public void StackTrace(Exception e){
     if(StackTraceBoolean){
         e.printStackTrace();
     }
    }
    public void MyLog() {

    }
    public void MyDialogue(Context context, String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 2. Chain together various setter methods to set the dialog characteristics

        // Add the buttons

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });


        builder.setMessage(message).setTitle(title);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        if(!dialog.isShowing()){
            dialog.show();
        }
    }





}
