package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wakeupngoc.kotlinboilerplate.BuildConfig;
import com.wakeupngoc.kotlinboilerplate.constants.NetworkConstants;
import com.wakeupngoc.kotlinboilerplate.di.qualifiers.CacheSize;
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;
import com.wakeupngoc.kotlinboilerplate.persistence.remote.service.AuthService;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@AppScope
@Module
public class NetworkModule {

    @Provides@AppScope@CacheSize
    static int provideCacheSize() {
        return 10 * 1024 * 1024;
    }

    @NonNull @Provides @AppScope
    static Gson gson() {
        return new GsonBuilder().create();
    }

    @NonNull @Provides @AppScope
    static Cache provideDefaultHttpCache(@NonNull Application app, @CacheSize int cacheSize) {
        return new Cache(app.getCacheDir(), cacheSize);
    }

    @NonNull @Provides @AppScope
    static OkHttpClient okHttpClient(Cache cache) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .cache(cache);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addNetworkInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }

    @NonNull @Provides @AppScope
    static Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(NetworkConstants.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
