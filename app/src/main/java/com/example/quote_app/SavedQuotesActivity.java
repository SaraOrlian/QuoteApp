package com.example.quote_app;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SavedQuotesActivity extends AppCompatActivity {
    ArrayList<String> quotesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        quotesArray = getIntent().getStringArrayListExtra("QUOTES");
        setupQuotes();
    }

    private void setupQuotes() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, quotesArray);

        ListView listView = (ListView) findViewById(R.id.quotes_list);
        listView.setAdapter(adapter);
    }
}