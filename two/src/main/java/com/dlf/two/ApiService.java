package com.dlf.two;


import com.dlf.two.bean.ImgBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    String baseUrl="http://yun918.cn/";
    String baseListUrl="https://gank.io/";

    @Multipart
    @POST("study/public/file_upload.php")
    Call<ResponseBody> getData(@Part("key") RequestBody requestBody, @Part MultipartBody.Part part);

    @GET("api/data/%E7%A6%8F%E5%88%A9/20/31")
    Observable<ImgBean> getInfoData();
}
