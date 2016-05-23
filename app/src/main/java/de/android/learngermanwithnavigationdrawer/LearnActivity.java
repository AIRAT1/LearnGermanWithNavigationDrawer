package de.android.learngermanwithnavigationdrawer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LearnActivity extends ParentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        firstInit();
        readWords();
        pickRandomWords();
    }

    @Override
    void firstInit() {
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        listView = (ListView)findViewById(R.id.listView);
        dictionary = new HashMap<>();
        allQuestionList = new ArrayList<>();
        fiveAnswers = new ArrayList<>();
    }

    @Override
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

    @Override
    void pickRandomWords() {
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allQuestionList);
        }else {
            adapter.notifyDataSetChanged();
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                questionTextView.setText(dictionary.get(allQuestionList.get(position)));
            }
        });
    }

}
