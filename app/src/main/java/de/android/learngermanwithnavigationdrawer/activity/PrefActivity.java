package de.android.learngermanwithnavigationdrawer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;

import de.android.learngermanwithnavigationdrawer.R;

public class PrefActivity extends PreferenceActivity {
    private EditTextPreference editTextPreference;
    private SwitchPreference switchPreference;
    private CheckBoxPreference animals, most_200_words, my_words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

        firstInit();
    }

    private void firstInit() {
        editTextPreference = (EditTextPreference)findPreference("editTextPreference");
        switchPreference = (SwitchPreference)findPreference("switchPreference");
        animals = (CheckBoxPreference)findPreference("animals");
        most_200_words = (CheckBoxPreference)findPreference("most_200_words");
        my_words = (CheckBoxPreference)findPreference("my_words");
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PrefActivity.class);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPreferencesToEnable();
        if (isNothingChecked()) {
            animals.setChecked(true);
        }
    }

    private boolean isNothingChecked() {
        CheckBoxPreference[] preferences = new CheckBoxPreference[] {
                animals, most_200_words, my_words};
        for (CheckBoxPreference cbp : preferences) {
            if (cbp.isChecked()) {
                return false;
            }
        }
        return true;
    }

    private void checkPreferencesToEnable() {

    }
}
