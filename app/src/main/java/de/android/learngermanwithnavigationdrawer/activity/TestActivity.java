package de.android.learngermanwithnavigationdrawer.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.android.learngermanwithnavigationdrawer.R;
import de.android.learngermanwithnavigationdrawer.adapter.MyArrayAdapter;

public class TestActivity extends ParentActivity {
    public static final String THE_WORD = "theWord";
    public static final String FIVE_ANSWERS = "fiveAnswers";
    public static final String SCORE = "score";
    public static final int WIN_SCORE = 10;
    public static final int LOSE_SCORE = 4;
    private int score = 0;
    private TextView scoreTextView;
    private String theWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        firstInit();
//        super.readWords();
//        pickRandomWords();
    }

    public void firstInit() {
        super.firstInit();
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        scoreTextView.setText(getString(R.string.score_text) + " " + score);
    }

    void pickRandomWords() {
        if (allQuestionList.size() < 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.alarm)
                    .setMessage(R.string.low_size_of_array)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(PrefActivity.newIntent(getApplicationContext()));
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            dialog.dismiss();
                        }
                    }).create().show();
            return;
        }
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
                    scoreTextView.setText(getString(R.string.score_text) + " " + score);
                    if (score == WIN_SCORE) {
                        Toast.makeText(TestActivity.this, R.string.win_text, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    score--;
                    if (score < - LOSE_SCORE) {
                        Toast.makeText(TestActivity.this, R.string.lose_text, Toast.LENGTH_SHORT).show();
                    }
                    scoreTextView.setText(getString(R.string.score_text) + " " + score);
                }
                pickRandomWords();
            }
        });
    }

    private void setTextViewAndArrayAdapterValues() {
        questionTextView.setText(theWord);
        scoreTextView.setText(getString(R.string.score_text) + " " + score);

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
            myArrayAdapter = null;
            setTextViewAndArrayAdapterValues();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.readWords();
        pickRandomWords();
    }
}
