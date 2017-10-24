package com.example.sherry.games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class HangMan extends AppCompatActivity {

    TextView lettersTextView, guessesTextView, wordTextView;

    String word="";
    String blanks="";
    String numGuesses="";
    int totalGuesses;
    int guessesUsed = 0;
    int lettersRemaining;
    int victory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);
        lettersTextView = (TextView) findViewById(R.id.letters);
        guessesTextView = (TextView) findViewById(R.id.guesses);
        wordTextView = (TextView) findViewById(R.id.word);
        startHangman();
    }

    private String generateWord() {
        String[] words = {"handler", "against", "horizon", "chops", "junkyard", "amoeba", "academy",
                "roast", "countryside", "children", "strange", "best", "drumbeat", "amnesiac",
                "chant", "amphibian", "smuggler", "fetish"};
        Random r = new Random();
        int index = r.nextInt(words.length);
        return words[index];
    }

    private void startHangman() {
        word = generateWord();
        int wordLength = word.length();
        lettersRemaining = wordLength;
        String numLetters = "This word consists of " + wordLength + " letters.";
        lettersTextView.setText(numLetters);
        totalGuesses = wordLength + 5;
        numGuesses = "You have " + totalGuesses + " guesses.";
        guessesTextView.setText(numGuesses);
        blanks = "";
        while (wordLength>0){
            blanks = blanks + "_";
            wordLength--;
        }
        wordTextView.setText(blanks);
    }

    public void insertLetter(View view){
        Button b = (Button) view;
        String s = b.getText().toString();
        char c = s.charAt(0);
        guessesUsed++;
        if (victory==0) {
            if (guessesUsed <= totalGuesses) {
                if (word.contains(s)) {
                    char[] guessChars = blanks.toCharArray();
                    int index = word.indexOf(s);
                    while (index >= 0) {
                        lettersRemaining--;
                        guessChars[index] = c;
                        blanks = String.valueOf(guessChars);
                        wordTextView.setText(blanks);
                        index = word.indexOf(s, index + 1);
                    }
                }
                if (lettersRemaining == 0) {
                    victory = 1;
                    numGuesses = "VICTORY! You used " + guessesUsed + " guesses.\nThe word was:";
                }
                else
                    numGuesses = "You have used " + guessesUsed + " of " + totalGuesses + " guesses.";
                b.setVisibility(View.INVISIBLE);
            }
            else {
                guessesUsed = guessesUsed - 1;
                numGuesses = "DEFEAT! You used " + guessesUsed + " guesses.\nThe word was:";
                wordTextView.setText(word);
                b.setEnabled(false);
            }
        }
        b.setEnabled(false);
        guessesTextView.setText(numGuesses);
    }

    public void reset(View view){
        recreate();
    }
}