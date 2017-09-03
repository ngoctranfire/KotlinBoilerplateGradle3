package com.wakeupngoc.kotlinboilerplate.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wakeupngoc.kotlinboilerplate.di.qualifiers.CacheSize;
import com.wakeupngoc.kotlinboilerplate.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@AppScope
@Module
public class NetworkModule {

    @Provides @AppScope @CacheSize
    static int provideCacheSize() {
        return 10 * 1024 * 1024;
    }

    @NonNull @Provides @AppScope
    static Gson gson() {
        return new GsonBuilder().create();
    }

    @NonNull @Provides @AppScope
    Cache provideDefaultHttpCache(@NonNull Context context, @CacheSize int cacheSize) {
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @NonNull @Provides @AppScope
    static OkHttpClient okHttpClient(@CacheSize int cacheSize) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor()
                .addInterceptor()
                .cache(cacheSize)
                .build();

        return httpClient;
    }
}
