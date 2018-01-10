package za.co.immedia.Methods;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import za.co.immedia.Classes.AppWebViewClient;
import za.co.immedia.Classes.ReturnColor;
import za.co.immedia.Global.MyLog;
import za.co.immedia.R;


public class WebViewWebsiteActivity extends AppCompatActivity {
    za.co.immedia.Methods.SharedPreferences SharedPreferences=new SharedPreferences();
    za.co.immedia.Classes.ReturnColor ReturnColor=new ReturnColor();
    String colorPrimary="";
    WebView WebViewReadNotes;
    private WebSettings websettings;
    private String stringURL="";
    MyLog Mylog=new MyLog();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_website);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Website");

        colorPrimary= SharedPreferences.readStringPrefs(WebViewWebsiteActivity.this, "InitialisedColor", "key");
        toolbar.setBackgroundColor(ReturnColor.ReturnPrimary(WebViewWebsiteActivity.this,colorPrimary));

        WebViewReadNotes=(WebView)findViewById(R.id.WebViewReadNotes);

        websettings = WebViewReadNotes.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setAllowFileAccess(true);
        websettings.setBuiltInZoomControls(true);
        websettings.setSupportZoom(true);
        websettings.getBuiltInZoomControls();
        WebViewReadNotes.setWebChromeClient(new WebChromeClient());

        WebViewReadNotes.getSettings().setDomStorageEnabled(true);
        WebViewReadNotes.getSettings().setAppCacheMaxSize(1024*1024*8);
        WebViewReadNotes.getSettings().setAppCachePath(getBaseContext().getFilesDir().getPath()+ getPackageName() +"/cache");
        WebViewReadNotes.getSettings().setAllowFileAccess(true);
        WebViewReadNotes.getSettings().setAppCacheEnabled(true);
        WebViewReadNotes.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        stringURL="http://www.immedia.co.za/";
        Mylog.e("adminhelp","stringURL "+stringURL);

        WebViewReadNotes.setWebViewClient(new AppWebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
                Mylog.e("errorWebv", ""+view.getSettings().getCacheMode()+" WebSettings.LOAD_DEFAULT "+WebSettings.LOAD_DEFAULT+" failingUrl "+failingUrl+" stringURL "+stringURL+" errorCode "+errorCode);
                if (view.getSettings().getCacheMode() == WebSettings.LOAD_DEFAULT && stringURL.equals(failingUrl))
                {
                    WebViewReadNotes.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                    WebViewReadNotes.loadUrl(stringURL);
                    return;
                }else if (stringURL.equals(failingUrl)){
                    // cache failed as well, load a local resource as last resort
                    WebViewReadNotes.loadUrl("file:///android_asset/statichtml/HtmlError/LoadingError.html");
                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        WebViewReadNotes.loadUrl(stringURL);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



}
