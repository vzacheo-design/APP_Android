/* package com.example.appfrontend.model;

public class TokenEntity {
} */

package com.example.appfrontend.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tokens")
public class TokenEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String accessToken;
    private String refreshToken;

    public TokenEntity(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
}

