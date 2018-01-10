package za.co.immedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import za.co.immedia.Classes.ReturnColor;
import za.co.immedia.Methods.SharedPreferences;

public class ChangeColorActivity extends AppCompatActivity {
    SharedPreferences SharedPreferences=new SharedPreferences();
    ReturnColor ReturnColor=new ReturnColor();
    String colorPrimary="";
    Button buttonColor1,buttonColor2,buttonColor3;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        colorPrimary=SharedPreferences.readStringPrefs(ChangeColorActivity.this, "InitialisedColor", "key");
        toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(ChangeColorActivity.this,colorPrimary));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Select App Color");

        buttonColor1=(Button)findViewById(R.id.buttonColor1);
        buttonColor2=(Button)findViewById(R.id.buttonColor2);
        buttonColor3=(Button)findViewById(R.id.buttonColor3);

        buttonColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.writeStringPrefs(ChangeColorActivity.this, "InitialisedColor","" ,"key");
                Toast.makeText(ChangeColorActivity.this, "Color 1 selected", Toast.LENGTH_SHORT).show();
                colorPrimary=SharedPreferences.readStringPrefs(ChangeColorActivity.this, "InitialisedColor", "key");
                toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(ChangeColorActivity.this,colorPrimary));
            }
        });
        buttonColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.writeStringPrefs(ChangeColorActivity.this, "InitialisedColor","1" ,"key");
                Toast.makeText(ChangeColorActivity.this, "Color 2 selected", Toast.LENGTH_SHORT).show();
                colorPrimary=SharedPreferences.readStringPrefs(ChangeColorActivity.this, "InitialisedColor", "key");
                toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(ChangeColorActivity.this,colorPrimary));
            }
        });
        buttonColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.writeStringPrefs(ChangeColorActivity.this, "InitialisedColor","2" ,"key");
                Toast.makeText(ChangeColorActivity.this, "Color 3 selected", Toast.LENGTH_SHORT).show();
                colorPrimary=SharedPreferences.readStringPrefs(ChangeColorActivity.this, "InitialisedColor", "key");
                toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(ChangeColorActivity.this,colorPrimary));
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
         super.onSupportNavigateUp();
         finish();
        return true;
    }
}
