package de.android.learngermanwithnavigationdrawer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.android.learngermanwithnavigationdrawer.R;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText newWord, newTranslate;
    private TextInputLayout inputLayoutWord, inputLayoutTranslate;
    private Button btnOk;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddWordActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        init();
    }

    private void init() {
        inputLayoutWord = (TextInputLayout)findViewById(R.id.inputLayoutWord);
        inputLayoutTranslate = (TextInputLayout)findViewById(R.id.inputLayoutTranslate);
        newWord = (EditText)findViewById(R.id.newWord);
        newTranslate = (EditText)findViewById(R.id.newTranslate);
        btnOk = (Button)findViewById(R.id.btnOk);
        newWord.addTextChangedListener(new MyTextWatcher(newWord));
        newTranslate.addTextChangedListener(new MyTextWatcher(newTranslate));
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        submitForm();
    }

    private void submitForm() {
        if (!validateWord()) {
            return;
        }
        if (!validateTranslation()) {
            return;
        }
        Toast.makeText(AddWordActivity.this, "All right!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateWord() {
        if (newWord.getText().toString().trim().isEmpty()) {
            inputLayoutWord.setError(getString(R.string.errorAddNewWord));
            requestFocus(newWord);
            return false;
        }else {
            inputLayoutWord.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateTranslation() {
        if (newTranslate.getText().toString().trim().isEmpty()) {
            inputLayoutTranslate.setError(getString(R.string.errorAddNewWord));
            requestFocus(newTranslate);
            return false;
        }else {
            inputLayoutTranslate.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher {
        private View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.newWord:
                    validateWord();
                    break;
                case R.id.newTranslate:
                    validateTranslation();
                    break;
                default:
                    break;
            }
        }
    }
}
