package com.example.ashu4642.ironplanner;

/*public class ExerciseDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tododb";
    public static final String DATABASE_TABLE = "todo";

    // Field names -- use the KEY_XXX constants here and in
    // client code, so it's all consistent and checked at compile-time.

    public static final String KEY_ROWID = "_id";  // Android requires exactly this key name
    public static final int INDEX_ROWID = 0;
    public static final String KEY_TITLE = "title";
    public static final int INDEX_TITLE = 1;
    public static final String KEY_BODY = "body";
    public static final int INDEX_BODY = 2;
    public static final String KEY_STATE = "state";
    public static final int INDEX_STATE = 3;

    public static final String[] KEYS_ALL =
            { ExerciseDatabase.KEY_ROWID, ExerciseDatabase.KEY_TITLE, ExerciseDatabase.KEY_BODY, ExerciseDatabase.KEY_STATE };


    private Context mContext;
    private SQLiteDatabase mDatabase;
    private TodoDBHelper mHelper;

    *//** Construct DB for this activity context. *//*
    public ExerciseDatabase(Context context) {
        mContext = context;
    }

    *//** Opens up a connection to the database. Do this before any operations. *//*
    public void open() throws SQLException {
        mHelper = new TodoDBHelper(mContext);
        mDatabase = mHelper.getWritableDatabase();
    }

    *//** Closes the database connection. Operations are not valid after this. *//*
    public void close() {
        mHelper.close();
        mHelper = null;
        mDatabase = null;
    }


    *//**
     Creates and inserts a new row using the given values.
     Returns the rowid of the new row, or -1 on error.
     todo: values should not include a rowid I assume.
     *//*
    public long createRow(ContentValues values) {
        return mDatabase.insert(DATABASE_TABLE, null, values);
    }

    *//**
     Updates the given rowid with the given values.
     Returns true if there was a change (i.e. the rowid was valid).
     *//*
    public boolean updateRow(long rowId, ContentValues values) {
        return mDatabase.update(DATABASE_TABLE, values,
                ExerciseDatabase.KEY_ROWID + "=" + rowId, null) > 0;
    }

    *//**
     Deletes the given rowid.
     Returns true if any rows were deleted (i.e. the id was valid).
     *//*
    public boolean deleteRow(long rowId) {
        return mDatabase.delete(DATABASE_TABLE,
                ExerciseDatabase.KEY_ROWID + "=" + rowId, null) > 0;
    }


    *//** Returns a cursor for all the rows. Caller should close or manage the cursor. *//*
    public Cursor queryAll() {
        return mDatabase.query(DATABASE_TABLE,
                KEYS_ALL,  // i.e. return all 4 columns
                null, null, null, null,
                ExerciseDatabase.KEY_TITLE + " ASC"  // order-by, "DESC" for descending
        );

        // Could pass for third arg to filter in effect:
        // TodoDatabaseHelper.KEY_STATE + "=0"

        // query() is general purpose, here we show the most common usage.
    }

    *//** Returns a cursor for the given row id. Caller should close or manage the cursor. *//*
    public Cursor query(long rowId) throws SQLException {
        Cursor cursor = mDatabase.query(true, DATABASE_TABLE,
                KEYS_ALL,
                KEY_ROWID + "=" + rowId,  // select the one row we care about
                null, null, null, null, null);

        // cursor starts before first -- move it to the row itself.
        cursor.moveToFirst();
        return cursor;
    }

    *//** Creates a ContentValues hash for our data. Pass in to create/update. *//*
    public ContentValues createContentValues(String title, String body, int state) {
        ContentValues values = new ContentValues();
        values.put(ExerciseDatabase.KEY_TITLE, title);
        values.put(ExerciseDatabase.KEY_BODY, body);
        values.put(ExerciseDatabase.KEY_STATE, state);
        return values;
    }

    // Helper for database open, create, upgrade.
    // Here written as a private inner class to TodoDB.
    private static class TodoDBHelper extends SQLiteOpenHelper {
        // SQL text to create table (basically just string or integer)
        private static final String DATABASE_CREATE =
                "create table " + DATABASE_TABLE + " (" +
                        ExerciseDatabase.KEY_ROWID + " integer primary key autoincrement, " +
                        ExerciseDatabase.KEY_TITLE + " text not null, " +
                        ExerciseDatabase.KEY_BODY + " text not null," +
                        ExerciseDatabase.KEY_STATE + " integer " +
                        ");";

        // SQLITE does not have a complex type system, so although "done" is a boolean
        // to the app, here we store it as an integer with (0 = false)


        public TodoDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        *//** Creates the initial (empty) database. *//*
        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }


        */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/** Called at version upgrade time, in case we want to change/migrate
         the database structure. Here we just do nothing. *//*
        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            // we do nothing for this case
        }
    }
}*/


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
            {KEY_ROWID, KEY_TITLE, KEY_GROUP, KEY_REPS,KEY_WEIGHT};
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

    public long createRow(ContentValues values) {
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
    public int getRows()
    {
        return this.queryAll().getCount();
    }
    public int getColumns()
    {
        return this.queryAll().getColumnCount();
    }
    public String[][] getSQLMatrix()
    {
        Cursor cursor = this.queryAll();
        String[][] results = new String[cursor.getCount()][cursor.getColumnCount()];
        for(int i=0;i<results.length;i++)
        {
            if(cursor.moveToFirst()) {
                for (int j = 0; j < results[0].length; j++) {
                    results[i][j] = cursor.getString(j);
                }
            }
            cursor.moveToNext();
        }
        return results;
    }

    public ContentValues createContentValues(String title, String group, String reps, String weight) {
        ContentValues values = new ContentValues();
        values.put(ExerciseDatabase.KEY_TITLE, title);
        values.put(ExerciseDatabase.KEY_GROUP, group);
        values.put(ExerciseDatabase.KEY_REPS, reps);
        values.put(ExerciseDatabase.KEY_WEIGHT,weight);
        return values;
    }

    private static class ExerciseDBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE =
                "create table " + DATABASE_TABLE + " (" +
                ExerciseDatabase.KEY_ROWID  + " integer primary key autoincrement, " +
                ExerciseDatabase.KEY_TITLE  + " text not null, " +
                ExerciseDatabase.KEY_GROUP  + " text not null, " +
                ExerciseDatabase.KEY_REPS   + " text not null, " +
                ExerciseDatabase.KEY_WEIGHT + " text not null, " +
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
