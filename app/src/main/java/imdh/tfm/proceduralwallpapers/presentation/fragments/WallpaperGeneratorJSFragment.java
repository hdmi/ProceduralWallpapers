package imdh.tfm.proceduralwallpapers.presentation.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.evernote.android.state.StateSaver;

import butterknife.BindView;
import butterknife.ButterKnife;
import imdh.tfm.proceduralwallpapers.R;

/**
 * Created by carlosab on 17/02/2018.
 */

public class WallpaperGeneratorJSFragment extends Fragment {

    @BindView(R.id.wallpaperJSImageView)
    ImageView wallpaperJSImageView;

    @BindView(R.id.somethingButton)
    ImageButton somethingButton;

    @BindView(R.id.webView)
    WebView webView;

    public static WallpaperGeneratorJSFragment newInstance() {
        return new WallpaperGeneratorJSFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wallpaper_generator_fragment, container, false);
        StateSaver.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this, v);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        //webView.addJavascriptInterface(new JavascriptAccessor(), "javascriptAccessor");
        webView.setWebViewClient(new WebViewClient() {});
        webView.loadUrl("file:///android_asset/wallpaper_container.html");

       somethingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueCallback<String> callback = new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String data) {
                        byte[] dataBase64 = Base64.decode(data.substring(22, data.length()), Base64.DEFAULT);
                        updateWallpaperJS(BitmapFactory.decodeByteArray(dataBase64, 0, dataBase64.length));
                    }
                };
                System.out.println("tid: "+Thread.currentThread().getId());
                webView.evaluateJavascript("location.reload();window.localStorage['wallpaper']", callback);
            }
        });
        System.out.println("tid: "+Thread.currentThread().getId());

        return v;
    }



    public void updateWallpaperJS(final Bitmap bm){
        if(bm == null){
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {


                System.out.println("tid: "+Thread.currentThread().getId());
                wallpaperJSImageView.setImageBitmap(bm);
            }
        });

    }

    private void webviewCleanup(){
        if(webView == null){
            return;
        }
        webView.evaluateJavascript("window.localStorage.clear()", null);
    }

/*
    private class JavascriptAccessor {
        String lmao = "";

        @JavascriptInterface
        public void getYerData(String data) {

            //byte[] dataBase64 = Base64.decode(data.substring(22, data.length()), Base64.DEFAULT);
            //updateWallpaperJS(BitmapFactory.decodeByteArray(dataBase64, 0, dataBase64.length));
            //String a = "";
            System.out.println("date: "+data);
            System.out.println("tid: "+Thread.currentThread().getId());

        }
    }*/

}
