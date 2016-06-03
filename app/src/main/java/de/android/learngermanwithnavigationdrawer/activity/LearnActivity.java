package de.android.learngermanwithnavigationdrawer.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;

import de.android.learngermanwithnavigationdrawer.R;
import de.android.learngermanwithnavigationdrawer.adapter.MyArrayAdapter;

public class LearnActivity extends ParentActivity {
    public static final int REQUEST_CODE = 12345;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        super.firstInit();
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setVisibility(View.VISIBLE);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.add_a_new_Word, Snackbar.LENGTH_LONG)
                        .setAction(R.string.yes, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(AddWordActivity.newIntent(LearnActivity.this));
                            }
                        }).show();
            }
        });
    }

    @Override
    void pickRandomWords() {
        if (myArrayAdapter == null) {
            myArrayAdapter = new MyArrayAdapter(this, allQuestionList);
        }
        listView.setAdapter(myArrayAdapter);
        listView.setSelector(R.drawable.list_selector);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                questionTextView.setText(dictionary.get(allQuestionList.get(position)));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        fabAdd.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fabAdd.setVisibility(View.VISIBLE);
        super.readWords();
        pickRandomWords();
    }
}
