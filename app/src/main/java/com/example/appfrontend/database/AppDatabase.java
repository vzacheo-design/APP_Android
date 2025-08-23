/* package com.example.appfrontend.database;

public class AppDatabase {
} */

package com.example.appfrontend.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appfrontend.dao.TokenDao;
import com.example.appfrontend.model.TokenEntity;

@Database(entities = {TokenEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TokenDao tokenDao();
}

