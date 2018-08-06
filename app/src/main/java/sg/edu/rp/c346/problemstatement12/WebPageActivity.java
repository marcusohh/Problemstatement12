package sg.edu.rp.c346.problemstatement12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageActivity extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_page);

        wvPage = findViewById(R.id.webviewpage);
        wvPage.setWebViewClient(new WebViewClient());

        Intent intentReceived = getIntent();

        String url = intentReceived.getStringExtra("link");
        wvPage.loadUrl(url);

    }
}
