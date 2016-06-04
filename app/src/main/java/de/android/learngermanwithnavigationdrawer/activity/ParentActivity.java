package de.android.learngermanwithnavigationdrawer.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import de.android.learngermanwithnavigationdrawer.R;
import de.android.learngermanwithnavigationdrawer.adapter.MyArrayAdapter;

public abstract class ParentActivity extends AppCompatActivity {
    protected Map<String, String> dictionary;
    protected ArrayList<String> allQuestionList;
    protected ArrayList<String> fiveAnswers;
    protected MyArrayAdapter myArrayAdapter;
    protected TextView questionTextView;
    protected ListView listView;
    protected SharedPreferences sp;
    protected boolean isAnimalSelected, isMost200WordsSelected, isMyWordsSelected;
    protected static boolean isOnlyMyWordsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void firstInit() {
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        listView = (ListView)findViewById(R.id.listView);
        dictionary = new HashMap<>();
        allQuestionList = new ArrayList<>();
        fiveAnswers = new ArrayList<>();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }
    public void readWords() {
        isOnlyMyWordsSelected = false;
        allQuestionList.clear();
        isAnimalSelected = sp.getBoolean("animals", false);
        isMost200WordsSelected = sp.getBoolean("most_200_words", false);
        isMyWordsSelected = sp.getBoolean("my_words", false);
        if (isAnimalSelected == false && isMost200WordsSelected == false && isMyWordsSelected == false) {
            isAnimalSelected = true;
        }

        if (isAnimalSelected) {
            Scanner animalsScanner = new Scanner(getResources().openRawResource(R.raw.list));
            readWordHelper(animalsScanner);
            animalsScanner.close();
        }

        if (isMost200WordsSelected) {
            Scanner most_200_wordsScanner = new Scanner(getResources().openRawResource(R.raw.most_200_german_words));
            readWordHelper(most_200_wordsScanner);
            most_200_wordsScanner.close();
        }

        if (isMyWordsSelected) {
            try {
                if (!isAnimalSelected && !isMost200WordsSelected) {
                    isOnlyMyWordsSelected = true;
                }
                Scanner scanner2 = new Scanner(openFileInput("added_words.txt"));
                readWordHelper(scanner2);
                scanner2.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void readWordHelper(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("/");
            if (parts.length >= 2) {
                String question = parts[0];
                String answer = parts[1];
                allQuestionList.add(question);
                dictionary.put(question, answer);
            }
        }
    }
    abstract void pickRandomWords();
}
