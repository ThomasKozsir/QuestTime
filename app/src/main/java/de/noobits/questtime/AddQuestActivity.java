package de.noobits.questtime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class AddQuestActivity extends WearableActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);

        mEditText = findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

        openKeyboard();
    }

    private void openKeyboard() {
        //open keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }catch (NullPointerException e){
            System.out.println("AddQuestActivity.openKeyboard:");
            throw(e);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = this.getIntent();
        intent.putExtra("quest", mEditText.getText());
        onActivityResult(MainActivity.ADD_QUEST_REQUEST, 1, intent);
        //closeKeyboard();
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }catch(NullPointerException e){
            System.out.println("AddQuestActivity.closeKeyboard:");
            throw(e);
        }
    }
}
