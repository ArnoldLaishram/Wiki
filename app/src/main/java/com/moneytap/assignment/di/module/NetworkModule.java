package com.moneytap.assignment.di.module;

import com.moneytap.assignment.Config;
import com.moneytap.assignment.WikiService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final int API_CONNECTION_TIME = 10;

    @Provides
    @Singleton
    public WikiService provideApi(OkHttpClient client, Converter.Factory converterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(client)
                .addConverterFactory(converterFactory)
                .build();

        return retrofit.create(WikiService.class);
    }

    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HashMap hashMap = new HashMap<String, String>();

        List<Interceptor> interceptors = getInterceptors(hashMap);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(API_CONNECTION_TIME, TimeUnit.SECONDS);
        builder.readTimeout(API_CONNECTION_TIME, TimeUnit.SECONDS);
        builder.writeTimeout(API_CONNECTION_TIME, TimeUnit.SECONDS);

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }


    public List<Interceptor> getInterceptors(final HashMap<String, String> additionalHeader) {
        List<Interceptor> interceptors = new ArrayList<>();
        // add header interceptor
        interceptors.add(getHeaderInterceptor(additionalHeader));

        return interceptors;
    }

    private Interceptor getHeaderInterceptor(final HashMap<String, String> additionalHeader) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                if (additionalHeader.size() != 0) {
                    for (Map.Entry<String, String> hdr : additionalHeader.entrySet()) {
                        builder.addHeader(hdr.getKey(), hdr.getValue());
                    }
                }
                Map<String, String> headers = getHeadersAfterAnnotatedSkip(chain.request().headers());
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    builder.addHeader(header.getKey(), header.getValue());
                }

                return chain.proceed(builder.build());
            }
        };
    }

    private Map<String, String> getHeadersAfterAnnotatedSkip(Headers annotatedHeaders) {
        Map<String, String> configHeaders = new HashMap<>(Config.API_HEADERS);

        for (String headerName : annotatedHeaders.names()) {
            configHeaders.remove(headerName);
        }

        return configHeaders;
    }

}
