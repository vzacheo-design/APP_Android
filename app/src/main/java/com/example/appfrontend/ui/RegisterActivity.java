/*package com.example.appfrontend.ui;

public class RegisterActivity {
} */
/*
package com.example.appfrontend.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfrontend.R; // Import corretto per R

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Carica activity_register.xml
    }
} */

package com.example.appfrontend.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfrontend.R;
import com.example.appfrontend.model.RegisterRequest;
import com.example.appfrontend.model.RegisterResponse;
import com.example.appfrontend.service.ApiClient;
import com.example.appfrontend.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUsername, editEmail, editPassword;
    private Button btnRegister;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Collego i campi UI
        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Retrofit API
        apiService = ApiClient.getApiService();

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = editUsername.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Creo il RegisterRequest usando il costruttore a tre parametri
        RegisterRequest request = new RegisterRequest(username, email, password);

        Call<RegisterResponse> call = apiService.register(request);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Errore nella registrazione", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Errore: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
