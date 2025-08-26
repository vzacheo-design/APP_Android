/* package com.example.appfrontend.database;

public class PeriodDao {
} */

package com.example.appfrontend.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PeriodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PeriodEntity> periods);

    @Query("SELECT * FROM periods")
    List<PeriodEntity> getAllPeriods();

    @Query("DELETE FROM periods")
    void clearAll();
}

