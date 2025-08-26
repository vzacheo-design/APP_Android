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
import com.example.appfrontend.database.DatabaseClient;
import com.example.appfrontend.model.LoginRequest;
import com.example.appfrontend.model.LoginResponse;
import com.example.appfrontend.model.TokenEntity;
import com.example.appfrontend.service.ApiClient;
import com.example.appfrontend.service.ApiService;
import com.example.appfrontend.service.TokenManager;

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

        apiService = ApiClient.getApiService(this);

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest request = new LoginRequest(username, password);
        Call<LoginResponse> call = apiService.login(request); // Assicurati di aver aggiunto il metodo in ApiService

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    // 🔹 Salva in variabili globali
                    TokenManager.getInstance().saveTokens(
                            loginResponse.getAccessToken(),
                            loginResponse.getRefreshToken()
                    );
                    String accessToken = loginResponse.getAccessToken();
                    String refreshToken = loginResponse.getRefreshToken();

                    // ✅ Salvo i token in Room
                    TokenEntity tokenEntity = new TokenEntity(accessToken, refreshToken);
                    DatabaseClient.getInstance(LoginActivity.this)
                            .getAppDatabase()
                            .tokenDao()
                            .deleteAll(); // rimuovo i vecchi token
                    DatabaseClient.getInstance(LoginActivity.this)
                            .getAppDatabase()
                            .tokenDao()
                            .insert(tokenEntity);

                    // ✅ Mostro anche un popup coi token
                    String msg = "Access Token:\n" + accessToken +
                            "\n\nRefresh Token:\n" + refreshToken;
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
