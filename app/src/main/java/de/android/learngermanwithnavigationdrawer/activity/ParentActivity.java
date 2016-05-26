package de.android.learngermanwithnavigationdrawer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import de.android.learngermanwithnavigationdrawer.adapter.MyArrayAdapter;
import de.android.learngermanwithnavigationdrawer.R;

public abstract class ParentActivity extends AppCompatActivity {
    protected Map<String, String> dictionary;
    protected ArrayList<String> allQuestionList;
    protected ArrayList<String> fiveAnswers;
    protected MyArrayAdapter myArrayAdapter;
    protected TextView questionTextView;
    protected ListView listView;

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
    }
    public void readWords() {
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.list));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length >= 2) {
                String question = parts[0];
                StringBuilder answerBuilder = new StringBuilder();
                for (int i = 1; i < parts.length; i++) {
                    answerBuilder.append(parts[i]).append(" ");
                }
                String answer = answerBuilder.toString().trim();
                allQuestionList.add(question);
                dictionary.put(question, answer);
            }
        }
    }
    abstract void pickRandomWords();
}
