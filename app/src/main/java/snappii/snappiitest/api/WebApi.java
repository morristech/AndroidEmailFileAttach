package snappii.snappiitest.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import snappii.snappiitest.misc.Constants;

public class WebApi {
    private static WebApi instance = new WebApi();
    private WebApiInterface service;

    public WebApi() {
        initConnection();
    }

    public static WebApi getInstance() {
        return instance;
    }

    private void initConnection() {
        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggerInterceptor)
                .connectTimeout(Constants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(Constants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_END_POINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(WebApiInterface.class);
    }

    public WebApiInterface getService() {
        return service;
    }

}
