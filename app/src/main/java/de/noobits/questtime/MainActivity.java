package de.noobits.questtime;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import java.util.List;

import de.noobits.questtime.dummy.Quest;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private List<Quest> questList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

        //TODO: load quests from database here
    }
}