/*package com.example.appfrontend.ui;

public class LoginActivity {
} */
package com.example.appfrontend.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfrontend.R;
import com.example.appfrontend.model.LoginRequest;
import com.example.appfrontend.model.LoginResponse;
import com.example.appfrontend.service.ApiClient;
import com.example.appfrontend.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editUsername, editPassword;
    private Button btnLogin;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        apiService = ApiClient.getApiService();

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest request = new LoginRequest(username, password);
        Call<LoginResponse> call = apiService.login(request); // da aggiungere in ApiService

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    // Mostra un popup con i token
                    String msg = "Access Token:\n" + loginResponse.getAccessToken() +
                            "\n\nRefresh Token:\n" + loginResponse.getRefreshToken();
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login fallito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Errore: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
