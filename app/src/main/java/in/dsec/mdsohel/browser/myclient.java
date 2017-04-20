package in.dsec.mdsohel.browser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * our new web client object
 */

class myclient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
