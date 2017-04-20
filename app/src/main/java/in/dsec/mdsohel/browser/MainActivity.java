package in.dsec.mdsohel.browser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    WebView browser;
    EditText editurl;
    Button go, back, forward, clear, reload;
    ProgressBar progressbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the webview
        browser = (WebView)findViewById(R.id.webview);

        //Initializing the EditTExt where we will write our url
        editurl = (EditText)findViewById(R.id.edit);

        //Initializing the buttons(go, back, forward, clear, reload)
        go = (Button)findViewById(R.id.btn_go);
        back = (Button)findViewById(R.id.btn_backward);
        forward = (Button)findViewById(R.id.btn_forward);
        clear = (Button)findViewById(R.id.btn_clear);
        reload = (Button)findViewById(R.id.btn_reload);

        //initialing PROGRESSBAR
        progressbar = (ProgressBar)findViewById(R.id.progess);


        //Enabling javaScript for our browser
        WebSettings setting = browser.getSettings();
        setting.setJavaScriptEnabled(true);


        //declaring new web client as myclient.
        browser.setWebViewClient(new myclient());


        browser.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressbar.setProgress(newProgress);

                //if page is fully loaded
                if(newProgress == 100){
                    progressbar.setVisibility(view.GONE);
                }else{
                    //if page still loading
                    progressbar.setVisibility(view.VISIBLE);
                }
            }
        });


        //when click on the GO button
        go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //getting the url dynamically from EditText
                String url = editurl.getText().toString();

                //can search without (http://www.)
                if(!url.startsWith("http://www."))
                    url = "http://www." + url;

                //Loading the url in to the browser
                browser.loadUrl(url);


                //hide the keyboard when we press GO button
                InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(editurl.getWindowToken(), 0);

            }

        });

        //for the forward button to go forward
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(browser.canGoForward()) {
                    browser.goForward();
                    browser.getUrl();
                }
            }
        });

        //for the back button to go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(browser.canGoBack()) {
                    browser.goBack();
                    browser.getUrl();
                }
            }
        });

        //for the reload button to reload the page
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.clearHistory();
            }
        });

        //for the clear button to clear the history
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.reload();
            }
        });

    }
}
