/* package com.example.appfrontend.database;

public class DatabaseClient {
}*/

package com.example.appfrontend.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private static DatabaseClient instance;
    private final AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyAppDB")
                .allowMainThreadQueries() // ⚠️ solo per test, meglio async
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}

