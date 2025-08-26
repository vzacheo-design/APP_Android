/* package com.example.appfrontend.service;

public class TokenInterceptor {
} */

package com.example.appfrontend.service;

import android.content.Context;

import androidx.room.Room;

//import com.example.appfrontend.db.AppDatabase;
import com.example.appfrontend.database.AppDatabase;
import com.example.appfrontend.model.TokenEntity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private Context context;

    public TokenInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Recupera il token da Room
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "app_db")
                .allowMainThreadQueries() // solo per test, meglio in Async
                .build();

        TokenEntity tokenEntity = db.tokenDao().getToken();
        String accessToken = tokenEntity != null ? tokenEntity.getAccessToken() : null;

        // Se esiste un access token lo aggiungiamo alla richiesta
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

        if (accessToken != null) {
            builder.header("Authorization", "Bearer " + accessToken);
        }

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}

