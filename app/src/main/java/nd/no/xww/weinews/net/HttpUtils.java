package nd.no.xww.weinews.net;

import java.util.concurrent.TimeUnit;

import nd.no.xww.weinews.API;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xww
 * @desciption : 网络请求 OkHttp + Retrofit
 * @date 2020/1/7
 * @time 16:32
 */
public class HttpUtils {

    public static class RetrofitHolder {
        static final Retrofit instance = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                // 开启 json 转换为实体类
                .addConverterFactory(GsonConverterFactory.create())
                // 开启 RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpHolder.instance)
                .build();
    }

    public static class OkHttpHolder {
        // 开启请求日志
        static final OkHttpClient instance = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static Retrofit getInstance() {
        return RetrofitHolder.instance;
    }
}
