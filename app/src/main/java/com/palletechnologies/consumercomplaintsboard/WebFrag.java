package com.palletechnologies.consumercomplaintsboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFrag extends Fragment {

    WebView webView;

    public WebFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web, container, false);

        Intent intent = new Intent();
        Bundle bundle = intent.getExtras();

        String url = bundle.getString("url");

        webView = (WebView) v.findViewById(R.id.webview);
        //webView = new WebView(getActivity());

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient());

        return v;
    }

}
