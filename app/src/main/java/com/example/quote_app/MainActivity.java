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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.example.quote_app.lib.Utils.showInfoDialog;


public class MainActivity extends AppCompatActivity {
    private final QuoteController controller = new QuoteController();

    private TextView quoteText;
    private TextView authorText;

    private String currQuote;
    private String currAuthor;

    private final String savedQuotesFileName = "SAVED_QUOTES";
    private final String savedQuotesSetName = "SAVED_QUOTES_SET";
    Set<String> quotesSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupFAB();
        setupFields();
        restoreFromPreferences_TextSize();

        SharedPreferences savedPrefs = getSharedPreferences(savedQuotesFileName, MODE_PRIVATE);
        quotesSet = savedPrefs.getStringSet(savedQuotesSetName, new HashSet<>());
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
        currAuthor = controller.getAuthor();
        currQuote = controller.getQuote();

        authorText.setText(currAuthor);
        quoteText.setText(currQuote);
        controller.regenerateQuote();
    }

    @Override
    protected void onStop() {
        saveQuotesToSharedPref();
        super.onStop();
    }

    private void saveQuotesToSharedPref() {
        SharedPreferences preferences = getSharedPreferences(savedQuotesFileName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putStringSet(savedQuotesSetName, quotesSet);
        editor.apply();
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

        switch (id) {
            case R.id.action_settings:
                showSettings();
                return true;
            case R.id.action_about:
                showAbout();
                return true;
            case R.id.like_quote:
                quotesSet.add(currQuote + "- " + currAuthor);
                return true;
            case R.id.activity_saved_quotes:
                Intent intent = new Intent(getApplicationContext(), SavedQuotesActivity.class);
                intent.putExtra("QUOTES", new ArrayList<>(quotesSet));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

