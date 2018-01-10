package za.co.immedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import za.co.immedia.Methods.SharedPreferences;


public class WelcomeActivity extends AppCompatActivity {
    Button button, buttonRegister;
    SharedPreferences SharedPreferences=new SharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        button=(Button)findViewById(R.id.button);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);

/*        if(SharedPreferences.readStringPrefs(WelcomeActivity.this, "welcome", "lockkey").length()>0){
            Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
//            Intent intent=new Intent(WelcomeActivity.this, MapsActivity.class);
            startActivity(intent);
            finish();
        }else{

        }*/
       
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                SharedPreferences.writeStringPrefs(WelcomeActivity.this, "welcome","Initialised" ,"lockkey");
                SharedPreferences.writeStringPrefs(WelcomeActivity.this, "InitialisedColor","" ,"key");
//                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                SharedPreferences.writeStringPrefs(WelcomeActivity.this, "welcome","Initialised" ,"lockkey");
                SharedPreferences.writeStringPrefs(WelcomeActivity.this, "InitialisedColor","" ,"key");
//                finish();
            }
        });

    }

/*    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("out","hahahahahaha");
    }*/
}
