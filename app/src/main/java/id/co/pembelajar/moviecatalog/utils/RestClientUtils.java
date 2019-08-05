package id.co.pembelajar.moviecatalog.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestClientUtils {
    private static String TAG = RestClientUtils.class.getName();
    public static final String BASE_URL = "https://api.themoviedb.org/3/discover/";

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build();

    public static Call get(String url, HashMap<String, String> params, HttpCallback responseHandler) {
        return get(url, params, true, responseHandler);
    }

    public static Call get(String url, HashMap<String, String> params, boolean includeHeader, HttpCallback responseHandler) {
        if( params == null ) {
            params = new HashMap<String, String> ();
        }

        Uri.Builder builtUri = Uri.parse( getAbsoluteUrl(url) ).buildUpon();

        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());

            builtUri.appendQueryParameter( String.valueOf(pair.getKey()), String.valueOf(pair.getValue()));
        }
        Uri uri = builtUri.build();

        Request.Builder builder = new Request.Builder()
                .url(uri.toString());

        if(includeHeader)
            builder = addHeaders(builder);

        Request request = builder.build();

        Log.i(TAG, "url get:" + getAbsoluteUrl(url) +  ", params:" + params.toString());

        Call call = client.newCall(request);
        call.enqueue(initCallback(responseHandler));

        return  call;
    }

    public static Call get(Context ctx, String url, HashMap<String, String> params, HttpCallback responseHandler) {
        if( params == null ) {
            params = new HashMap<String, String> ();
        }

        Uri.Builder builtUri = Uri.parse( getAbsoluteUrl(url) ).buildUpon();

        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());

            builtUri.appendQueryParameter( String.valueOf(pair.getKey()), String.valueOf(pair.getValue()));
        }
        Uri uri = builtUri.build();

        Request.Builder builder = new Request.Builder()
                .url(uri.toString());
        builder = addHeaders(builder);
        Request request = builder.build();

        Log.i(TAG, "url get:" + getAbsoluteUrl(url) +  ", params:" + params.toString());

        Call call = client.newCall(request);
        call.enqueue(initCallback(responseHandler));

        return call;
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        String baseUrl = BASE_URL;
        if( relativeUrl.contains("/")) {
            int resourcePath = baseUrl.lastIndexOf("/", baseUrl.length()-2);
            baseUrl = baseUrl.substring(0, resourcePath) + "/";
            //Log.i(RestClientUtils.class.getName(), "baseUrl:" + baseUrl );
        }
        return BASE_URL/*baseUrl*/ + relativeUrl;
    }

    private static Request.Builder addHeaders(Request.Builder reqBuilder){
        return reqBuilder;
    }

    private static Callback initCallback(final HttpCallback callback){
        final Handler mainHandler = new Handler(Looper.getMainLooper());

        return new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try { callback.onFailure(call, e); }
                        catch (Exception e){ e.printStackTrace(); }
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final int statusCode = response.code();
                String responseBodyStr = "";
                try { responseBodyStr =  response.body().string(); }
                catch (Exception e){}
                if(responseBodyStr.length() == 0) responseBodyStr = response.toString();

                final String responseStr = responseBodyStr;

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try { callback.onResponse(call, statusCode, responseStr); }
                        catch (Exception e){ e.printStackTrace(); }
                    }
                });
            }
        };
    }

    public static interface HttpCallback {
        public void onFailure(Call call, IOException e);
        public void onResponse(Call call, int statusCode, String responseStr);
    }

}
