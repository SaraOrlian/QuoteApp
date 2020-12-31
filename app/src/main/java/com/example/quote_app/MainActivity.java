package com.example.quote_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.example.quote_app.lib.Utils.showInfoDialog;


public class MainActivity extends AppCompatActivity {

    private TextView quoteText;
    private TextView authorText;
    private final QuoteController controller = new QuoteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupFAB();
        setupFields();
        restoreFromPreferences_TextSize();
    }

    private void setupFields() {
        quoteText = findViewById(R.id.quote_text);
        authorText = findViewById(R.id.author_text);
    }

    private void setupFAB() {
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> handleFABClick());
    }

    private void handleFABClick() {
        authorText.setText(controller.getAuthor());
        quoteText.setText(controller.getQuote());
        controller.regenerateQuote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                showSettings();
                return true;
            case R.id.action_about:
                showAbout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAbout() {
        showInfoDialog(this, "About our Quotes", "This app was developed by Michal Berger and Sara Orlian. We hope you enjoy and " +
                "get inspired by each quote:)");
    }

    private void showSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1)
            restoreFromPreferences_TextSize();
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    private void restoreFromPreferences_TextSize() {
        SharedPreferences defaultSharedPreferences = getDefaultSharedPreferences(this);
        boolean largeText = defaultSharedPreferences.getBoolean(getString(R.string.text_size), true);
        quoteText.setTextSize(largeText ? 25 : 18);
    }
}

