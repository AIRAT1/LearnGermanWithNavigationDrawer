package de.android.learngermanwithnavigationdrawer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class LearnActivity extends ParentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        super.firstInit();
        super.readWords();
        pickRandomWords();
    }

    @Override
    void pickRandomWords() {
        if (myArrayAdapter == null) {
            myArrayAdapter = new MyArrayAdapter(this, allQuestionList);
        }else {
            myArrayAdapter.notifyDataSetChanged();
        }
        listView.setAdapter(myArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                questionTextView.setText(dictionary.get(allQuestionList.get(position)));
            }
        });
    }

}
