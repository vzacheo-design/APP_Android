/*package com.example.appfrontend.dao;

public class TokenDao {
} */

package com.example.appfrontend.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appfrontend.model.TokenEntity;

@Dao
public interface TokenDao {

    @Insert
    void insert(TokenEntity token);

    @Query("SELECT * FROM tokens LIMIT 1")
    TokenEntity getToken();

    @Query("DELETE FROM tokens")
    void deleteAll();
}

