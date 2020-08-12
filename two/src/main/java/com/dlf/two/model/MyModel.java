package com.dlf.two.model;

import com.dlf.two.ApiService;
import com.dlf.two.MyApp;
import com.dlf.two.bean.ImgBean;
import com.dlf.two.contract.MyContract;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel implements MyContract.Model {


    @Override
    public void getData(final MyContract.CallBack callBack) {

        OkHttpClient build = new OkHttpClient.Builder()
                .cache(new Cache(MyApp.context.getCacheDir(), 1024 * 1024 * 10))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(build)
                .baseUrl(ApiService.baseListUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Observable<ImgBean> infoData = retrofit.create(ApiService.class).getInfoData();
        infoData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImgBean imgBean) {
                        callBack.loadUpUISunnecc(imgBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.loadUpUIFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
