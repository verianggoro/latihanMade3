package id.co.pembelajar.moviecatalog.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;

import id.co.pembelajar.moviecatalog.model.TvShowsModels;
import id.co.pembelajar.moviecatalog.utils.RestClientUtils;
import okhttp3.Call;

public class TvShowsViewModel extends ViewModel {
    private static final String TAG = TvShowsViewModel.class.getSimpleName();
    private static final String API_KEY = "7cdd326560d9adb5e24ae3add1c7d335";
    private MutableLiveData<List<TvShowsModels.Result>> listData = new MutableLiveData<>();

    public void sendReqData(String language){
        HashMap<String, String> params = new HashMap<>();
        params.put("api_key", API_KEY);
        params.put("language", language);
        RestClientUtils.get("tv", params, new RestClientUtils.HttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Failed Fetch Data From Server");
            }

            @Override
            public void onResponse(Call call, int statusCode, String responseStr) {
                Log.i(TAG, responseStr);
                if (statusCode == HttpURLConnection.HTTP_OK){
                    Gson gson = new Gson();
                    List<TvShowsModels.Result> list;
                    try{
                        TvShowsModels models = gson.fromJson(responseStr, TvShowsModels.class);
                        list = models.getResults();
                        listData.postValue(list);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public LiveData<List<TvShowsModels.Result>> getResultData(){
        return listData;
    }

}
