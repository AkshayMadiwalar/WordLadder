package com.example.akshay.wordladder;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.akshay.wordladder.SolverActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Akshay on 17-12-2017.
 */

public class WordSelectionActivity extends AppCompatActivity {

    private PathDictionary dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new PathDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean onStart(View view) {
        TextView startWordView = (TextView) findViewById(R.id.startWord);
        TextView endWordView = (TextView) findViewById(R.id.endWord);
        String[] words = dictionary.findPath(
                startWordView.getText().toString().toLowerCase(),
                endWordView.getText().toString().toLowerCase());
        if (words != null) {
            // TODO: Launch new activity here
            Intent intent = new Intent(this, SolverActivity.class);
            intent.putExtra("path",words);
            startActivity(intent);
        } else {
            Log.i("Word ladder", "Word combination is not possible");
            Toast toast = Toast.makeText(this, "Couldn't find path between the two given words",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_word_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
