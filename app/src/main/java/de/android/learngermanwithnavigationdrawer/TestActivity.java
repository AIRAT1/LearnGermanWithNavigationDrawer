package de.android.learngermanwithnavigationdrawer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TestActivity extends ParentActivity {
    private int score = 0;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        firstInit();
        readWords();
        pickRandomWords();
    }

    void firstInit() {
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        scoreTextView.setText("Счёт: " + score);
        listView = (ListView)findViewById(R.id.listView);
        dictionary = new HashMap<>();
        allQuestionList = new ArrayList<>();
        fiveAnswers = new ArrayList<>();
    }

    void readWords() {
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

    void pickRandomWords() {
        List<String> fiveQuestions = new ArrayList<>();
        Collections.shuffle(allQuestionList);
        for (int i = 0; i < 5; i++) {
            fiveQuestions.add(allQuestionList.get(i));
        }
        final String theWord = fiveQuestions.get(0);
        questionTextView.setText(theWord);
        fiveAnswers.clear();
        for (String word : fiveQuestions) {
            fiveAnswers.add(dictionary.get(word));
        }
        Collections.shuffle(fiveAnswers);
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fiveAnswers);
        }else {
            adapter.notifyDataSetChanged();
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String usersAnswer = fiveAnswers.get(position);
                String rightAnswer = dictionary.get(theWord);
                if (usersAnswer.equals(rightAnswer)) {
                    score++;
                    scoreTextView.setText("Счёт: " + score);
                    if (score == 10) {
                        Toast.makeText(TestActivity.this, "Победа!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    score--;
                    if (score < -4) {
                        Toast.makeText(TestActivity.this, "Позор!", Toast.LENGTH_SHORT).show();
                    }
                    scoreTextView.setText("Счёт: " + score);
                }
                pickRandomWords();
            }
        });
    }

}
