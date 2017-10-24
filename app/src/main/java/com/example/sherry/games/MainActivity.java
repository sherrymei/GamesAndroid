package com.example.sherry.games;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] games = new String[]{"HangMan","TicTacToe"};
      // TextView textView = (TextView) findViewById(R.id.textView);
        final ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, games );
        listView.setAdapter(adapter);


        AdapterView.OnItemClickListener clickHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent hangman = new Intent(MainActivity.this, HangMan.class);
                        startActivity(hangman);
                        break;
                    case 1:
                        Intent TicTacToe = new Intent(MainActivity.this, TicTacToe.class);
                        startActivity(TicTacToe);
                        break;
                }
            }
        };
        listView.setOnItemClickListener(clickHandler);
    }
}
