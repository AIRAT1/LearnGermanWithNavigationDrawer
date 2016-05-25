package de.android.learngermanwithnavigationdrawer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestActivity extends ParentActivity {
    public static final String THE_WORD = "theWord";
    public static final String FIVE_ANSWERS = "fiveAnswers";
    public static final String SCORE = "score";
    private int score = 0;
    private TextView scoreTextView;
    private String theWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        firstInit();
        super.readWords();
        pickRandomWords();
    }

    void firstInit() {
        super.firstInit();
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        scoreTextView.setText("Счёт: " + score);
    }

    void pickRandomWords() {
        List<String> fiveQuestions = new ArrayList<>();
        Collections.shuffle(allQuestionList);
        for (int i = 0; i < 5; i++) {
            fiveQuestions.add(allQuestionList.get(i));
        }
        theWord = fiveQuestions.get(0);
        fiveAnswers.clear();
        for (String word : fiveQuestions) {
            fiveAnswers.add(dictionary.get(word));
        }
        Collections.shuffle(fiveAnswers);

        setTextViewAndArrayAdapterValues();
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

    private void setTextViewAndArrayAdapterValues() {
        questionTextView.setText(theWord);
        scoreTextView.setText("Счёт: " + score);

        if (myArrayAdapter == null) {
            myArrayAdapter = new MyArrayAdapter(this, fiveAnswers);
        }else {
            myArrayAdapter.notifyDataSetChanged();
        }
        listView.setAdapter(myArrayAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString(THE_WORD, theWord);
        bundle.putStringArrayList(FIVE_ANSWERS, fiveAnswers);
        bundle.putInt(SCORE, score);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        if (bundle.containsKey(THE_WORD) && bundle.containsKey(FIVE_ANSWERS) && bundle.containsKey(SCORE)) {
            theWord = bundle.getString(THE_WORD, "");
            fiveAnswers = bundle.getStringArrayList(FIVE_ANSWERS);
            score = bundle.getInt(SCORE);
//            adapter = null;
            myArrayAdapter = null;
            setTextViewAndArrayAdapterValues();
        }
    }
}
