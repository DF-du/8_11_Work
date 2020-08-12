package com.dlf.two.model;

import android.net.Uri;
import android.util.Log;

import com.dlf.two.ApiService;
import com.dlf.two.contract.IContract;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IModel implements IContract.Model {

    @Override
    public void getData(final IContract.CallBack callBack, String imgPath) {


        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), "ming");



        File file = new File(imgPath);
        if(!file.exists()){
            Log.i("Tag", "文件不存在");
            return;
        }
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody.Part file1 = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .build();

        Call<ResponseBody> data = retrofit.create(ApiService.class).getData(requestBody, file1);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    callBack.loadUpUISunnecc(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.loadUpUIFailed(t.getMessage());
            }
        });


    }
}
