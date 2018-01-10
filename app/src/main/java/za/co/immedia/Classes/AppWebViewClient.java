package za.co.immedia.Classes;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class AppWebViewClient extends WebViewClient {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
       // setProgressBar(true);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //Page load finished
        super.onPageFinished(view, url);
        //setProgressBar(false);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        // TODO Auto-generated method stub
        super.onReceivedError(view, errorCode, description, failingUrl);
    }
}
