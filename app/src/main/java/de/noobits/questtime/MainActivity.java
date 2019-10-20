package de.noobits.questtime;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.wear.widget.WearableRecyclerView;

import de.noobits.questtime.Database.QuestContract.Quest;
import de.noobits.questtime.Database.QuestDbHelper;
import de.noobits.questtime.AddQuestActivity;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private WearableRecyclerView mQuestListView;
    private ImageButton mAddQuestButton;
    private QuestDbHelper dbHelper;

    public static final int ADD_QUEST_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text);
        mQuestListView = findViewById(R.id.quest_list);
        mAddQuestButton = findViewById(R.id.add_quest_button);

        dbHelper = new QuestDbHelper(this);

        // Enables Always-on
        setAmbientEnabled();

        loadQuestsFromDb();

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        mAddQuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddQuestButtonClicked();
            }
        });
    }

    private void onAddQuestButtonClicked() {
        Intent intent = new Intent(this, AddQuestActivity.class);
        startActivityForResult(intent, ADD_QUEST_REQUEST);
    }

    private void loadQuestsFromDb() {
       SQLiteDatabase db = dbHelper.getReadableDatabase();

       String[] projection = {
               BaseColumns._ID,
        Quest.COLUMN_NAME_TITLE,
       };

        String sortOrder = Quest.COLUMN_DATE + " DESC";

        Cursor cursor = db.query(
                Quest.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        //TODO: load quests into listview here

        cursor.close();
    }

    private void addNewQuest(String quest){
        dbHelper.createNewQuest(quest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            try {
                addNewQuest(data.getExtras().getString("quest"));
            }catch (NullPointerException e){
                System.out.println("MainActivity: " + e);
                throw(e);
            }
        }
    }
}
