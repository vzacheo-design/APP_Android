/* package com.example.appfrontend.service;

public class ConfigLoader {
} */
package com.example.appfrontend.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Utility class for loading JSON data from a remote API
 * and populating a provided list.
 */
public class ConfigLoader {

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Carica dati JSON da un URL e popola una lista fornita con oggetti del tipo specificato.
     *
     * @param url        URL dell’API REST
     * @param targetList Lista da popolare con gli oggetti ottenuti
     * @param typeClass  Classe del tipo da deserializzare (es. PeriodTDO.class)
     * @param <T>        Tipo generico
     */
    public static <T> void loadData(String url, List<T> targetList, Class<T> typeClass) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                System.err.println("Errore HTTP: " + response.code());
                return;
            }

            String json = response.body().string();

            Gson gson = new Gson();
            Type listType = TypeToken.getParameterized(List.class, typeClass).getType();
            List<T> resultList = gson.fromJson(json, listType);

            // Pulisce e riempie la lista fornita
            targetList.clear();
            targetList.addAll(resultList);

            System.out.println("LD: Dati caricati da: " + url + " → " + targetList.size() + " elementi");

        } catch (IOException e) {
            System.err.println("Errore durante la chiamata HTTP a " + url + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}

