/* package com.example.appfrontend;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
} */
/*
package com.example.appfrontend;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appfrontend.model.PeriodTDO;
import com.example.appfrontend.service.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Lista per memorizzare i periodi caricati
    private final List<PeriodTDO> periods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // XML UI (anche vuoto per ora)

        // Avvio caricamento dati in background
        new Thread(() -> {
            ConfigLoader.loadData("http://10.0.2.2:8080/api/periods", periods, PeriodTDO.class);

            // Mostra un messaggio quando il caricamento è completo (sul thread principale)
            runOnUiThread(() -> {
                Toast.makeText(this, "Caricati " + periods.size() + " periodi", Toast.LENGTH_LONG).show();
            });
        }).start();
    }
} */
package com.example.appfrontend;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfrontend.adapter.PeriodAdapter;
import com.example.appfrontend.model.PeriodTDO;
import com.example.appfrontend.service.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<PeriodTDO> periods = new ArrayList<>();
    private PeriodAdapter adapter;
    private RecyclerView recyclerView;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewPeriods);
        adapter = new PeriodAdapter(periods);
        recyclerView.setAdapter(adapter);

        new Thread(() -> {
            ConfigLoader.loadData("http://10.0.2.2:8080/api/periods", periods, PeriodTDO.class);

            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Caricati " + periods.size() + " periodi", Toast.LENGTH_LONG).show();
            });
        }).start();
    } */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Collegamento al layout XML

        // 1. Collega la RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPeriods);

        // 2. Prepara la lista e l'adapter
        PeriodAdapter adapter = new PeriodAdapter(periods);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 3. Carica i dati in background
        new Thread(() -> {
            ConfigLoader.loadData("http://127.0.0.1:8080/api/periods", periods, PeriodTDO.class);

            // 4. Una volta terminato, aggiorna la UI sul thread principale
            runOnUiThread(() -> {
                System.out.println("MAIN: dati caricati: " + periods.size());
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Caricati " + periods.size() + " periodi", Toast.LENGTH_LONG).show();
            });
        }).start();
    }

}

