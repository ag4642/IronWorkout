package com.example.ashu4642.ironplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Anirudh on 1/23/2015.
 * Why are the studio tools so irritating
 */

public class ExerciseDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "exercisedb";
    public static final String DATABASE_TABLE = "exercise";

    // Field names -- use the KEY_XXX constants here and in
    // client code, so it's all consistent and checked at compile-time.

    public static final String KEY_ROWID = "_id";  // Android requires exactly this key name
    public static final int INDEX_ROWID = 0;
    public static final String KEY_TITLE = "title"; //name of exercise "Bicep Curls"
    public static final int INDEX_TITLE = 1;
    public static final String KEY_GROUP = "group"; //Muscle group it works out "Arms"
    public static final int INDEX_GROUP = 2;
    public static final String KEY_REPS = "reps"; //Repititions for each set as a string "10,10,10"
    public static final int INDEX_STATE = 3;
    public static final String KEY_WEIGHT = "weight"; //25 pounds
    public static final int INDEX_WEIGHT = 4;
    public static final String[] KEYS_ALL =
            {KEY_ROWID, KEY_TITLE, KEY_GROUP, KEY_STATE};
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private ExerciseDBHelper mHelper;

    public ExerciseDatabase(Context context) {
        mContext = context;
    }

    public void open() throws SQLException {
        mHelper = new ExerciseDBHelper(mContext);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void close() {
        mHelper.close();
        mHelper = null;
        mDatabase = null;
    }

    public long createRow(String id, String title, String group, String reps, String weight) {
        ContentValues values = new ContentValues(6);
        values.put(KEY_ID, id);
        values.put(KEY_TITLE,title);
        values.put(KEY_GROUP,group);
        values.put(KEY_REPS, reps);
        values.put (KEY_WEIGHT,weight);
        return mDatabase.insert(DATABASE_TABLE, null, values);
    }

    public boolean updateRow(long rowId, ContentValues values) {
        return (mDatabase.update(DATABASE_TABLE, values,
                ExerciseDatabase.KEY_ROWID + "=" + rowId, null) > 0);
    }

    public boolean deleteRow(long rowId) {
        return (mDatabase.delete(DATABASE_TABLE, ExerciseDatabase.KEY_ROWID
        +"="+rowId,null) > 0);
    }

    public Cursor queryAll() {
        return mDatabase.query(DATABASE_TABLE, KEYS_ALL, null, null, null, null,
                KEY_GROUP + " ASC, " + KEY_TITLE + " ASC,");
    }

    public Cursor query(long rowId) throws SQLException {
        Cursor cursor = mDatabase.query(true, DATABASE_TABLE, KEYS_ALL,
        KEY_ROWID+"="+rowId, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }
    public String[][] getSQLMatrix()
    {
        Cursor cursor = this.queryAll();
        String[][] results = new String[cursor.getCount()][cursor.getColumns()];
        for(int i = 0;i<cursor.getCount();i++)
        {
            for (int j = 0; j < cursor.getColumns(); j++) {
                if (cursor.moveToFront()) {
                    results[i][j] = cursor.getString(j);
                }
                cursor.moveToNext();
            }
        }

    }
    public int numRows()
    {
        return this.queryAll().getCount();
    }
    public int numColumns()
    {
        return this.queryAll().getColumns();
    }
    public ContentValues createContentValues(String title, String group, int state) {
        ContentValues values = new ContentValues();
        values.put(ExerciseDatabase.KEY_TITLE, title);
        values.put(ExerciseDatabase.KEY_GROUP, group);
        values.put(ExerciseDatabase.KEY_STATE, state);
        return values;
    }

    private static class ExerciseDBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE =
                "create table " + DATABASE_TABLE + " (" +
                ExerciseDatabase.KEY_ROWID  + " integer primary key autoincrement, " +
                ExerciseDatabase.KEY_TITLE  + " text not null, " +
                ExerciseDatabase.KEY_GROUP  + " text not null, " +
                ExerciseDatabase.KEY_REPS   + " text not null, " +
                ExerciseDatabase.KEY_WEIGHT + " text not null, "
                ");";

        public ExerciseDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            //
        }
    }
}
