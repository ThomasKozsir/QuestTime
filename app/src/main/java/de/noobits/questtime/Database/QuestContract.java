package de.noobits.questtime.Database;

import android.provider.BaseColumns;

import java.util.List;

public final class QuestContract {

    private QuestContract(){}

    public static class Quest implements BaseColumns {
        public static final String TABLE_NAME = "Quests";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_DATE = "created";
    }
}
