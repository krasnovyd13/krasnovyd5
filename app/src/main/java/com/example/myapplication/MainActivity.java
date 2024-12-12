package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editTextFilter;
    private ArrayAdapter<String> adapter;
    private List<String> currencyList = new ArrayList<>();
    private DataLoader dataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewCurrencies);
        editTextFilter = findViewById(R.id.editTextFilter);

        dataLoader = new DataLoader(this);
        dataLoader.fetchCurrencyRates("https://api.exchangeratesapi.io/latest", new DataLoader.Callback() {
            @Override
            public void onDataLoaded(List<String> rates) {
                currencyList = rates;
                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, currencyList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        // Filter functionality
        editTextFilter.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }
}
