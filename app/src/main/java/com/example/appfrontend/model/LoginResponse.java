/* package com.example.appfrontend.model;

public class LoginResponse {
} */
/*
package com.example.appfrontend.model;

public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;

    // Vuoto per Retrofit/Gson
    public LoginResponse() {}

    // Getter e Setter
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
} */

package com.example.appfrontend.model;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}


