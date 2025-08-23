/* package com.example.appfrontend.service;

public class ApiService {
} */
//package com.example.infoapp.service;
package com.example.appfrontend.service;
import com.example.appfrontend.model.RegisterResponse;
import com.example.appfrontend.model.RegisterRequest;
//import com.example.appfrontend.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Endpoint per registrazione
    @POST("/auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);
}


