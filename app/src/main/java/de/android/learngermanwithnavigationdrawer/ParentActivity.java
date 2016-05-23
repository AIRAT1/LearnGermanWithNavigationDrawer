package de.android.learngermanwithnavigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public abstract class ParentActivity extends AppCompatActivity {
    protected Map<String, String> dictionary;
    protected List<String> allQuestionList;
    protected List<String> fiveAnswers;
    protected ArrayAdapter<String> adapter;
    protected TextView questionTextView;
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    abstract void firstInit();
    abstract void readWords();
    abstract void pickRandomWords();
}
