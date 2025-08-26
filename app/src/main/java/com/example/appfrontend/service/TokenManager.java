/* package com.example.appfrontend.service;

public class TokenManager {
} */

package com.example.appfrontend.service;

public class TokenManager {

    private static TokenManager instance;
    private String accessToken;
    private String refreshToken;

    private TokenManager() {}

    public static synchronized TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void saveTokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

