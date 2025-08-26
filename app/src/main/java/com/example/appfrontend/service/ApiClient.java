package com.example.appfrontend.service;

import android.content.Context;

import androidx.room.Room;

import com.example.appfrontend.database.AppDatabase;
import com.example.appfrontend.model.TokenEntity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://10.0.2.2:8080";
    private static Retrofit retrofit = null;

    // ✅ Interceptor interno che legge il token da Room
    private static class TokenInterceptor implements Interceptor {
        private final Context context;

        public TokenInterceptor(Context context) {
            this.context = context;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            // Recupera il token da Room
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "app_db")
                    .allowMainThreadQueries() // ⚠️ solo per test (meglio usare AsyncTask/Coroutine in futuro)
                    .build();

            TokenEntity tokenEntity = db.tokenDao().getToken();
            String accessToken = tokenEntity != null ? tokenEntity.getAccessToken() : null;

            // Costruisci la richiesta con eventuale Authorization header
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder();

            if (accessToken != null) {
                builder.header("Authorization", "Bearer " + accessToken);
            }

            return chain.proceed(builder.build());
        }
    }

    // ✅ Usa context per creare l’ApiService con l’interceptor
    public static ApiService getApiService(Context context) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new TokenInterceptor(context)) // <-- qui aggiungiamo JWT
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}

