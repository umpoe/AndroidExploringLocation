package za.co.immedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import za.co.immedia.Classes.ReturnColor;
import za.co.immedia.Methods.SharedPreferences;

public class ImplementationActivity extends AppCompatActivity {
    za.co.immedia.Methods.SharedPreferences SharedPreferences=new SharedPreferences();
    za.co.immedia.Classes.ReturnColor ReturnColor=new ReturnColor();
    String colorPrimary="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implementation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("U. Loyal Ncube");
        colorPrimary= SharedPreferences.readStringPrefs(ImplementationActivity.this, "InitialisedColor", "key");
        toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(ImplementationActivity.this,colorPrimary));
    }

    @Override
    public boolean onSupportNavigateUp() {
         super.onSupportNavigateUp();
         finish();
        return true;
    }
}
