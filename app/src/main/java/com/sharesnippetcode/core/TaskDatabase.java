/**
 * Created by phucthai on 3/15/15.
 */
package com.sharesnippetcode.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class TaskDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TaskDatabase";

    public TaskDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE Items ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Notes TEXT, " +
                "Done INTEGER)";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Items");
        this.onCreate(db);
    }

    /**
     * CRUD operations
     */

    private static final String TABLE_ITEMS = "items";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_NOTES = "Notes";
    private static final String KEY_DONE = "Done";

    private static final String[] COLUMNS = {KEY_ID, KEY_DONE, KEY_NAME, KEY_NOTES};

    public void addTask(Task task) {
        Log.d("addTask", task.toString());
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_NOTES, task.getNotes());
        values.put(KEY_DONE, task.getDone());

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public int updateTask(Task task) {
        Log.d("updateTask", task.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_NOTES, task.getNotes());
        values.put(KEY_DONE, task.getDone());

        int i = db.update(TABLE_ITEMS,
                values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});

        db.close();

        return i;
    }

    public Task getTask(int id) {
        Log.d("getTask", String.valueOf(id));
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ITEMS,
                COLUMNS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        Task task = null;
        if (cursor != null) {
            cursor.moveToFirst();

            task = new Task();
            task.setId(cursor.getInt(0));
            task.setDone(cursor.getString(1));
            task.setName(cursor.getString(2));
            task.setNotes(cursor.getString(3));
        }

        return task;
    }

    public List<Task> getAllTasks() {

        List<Task> tasks = new LinkedList<>();

        String query = "SELECT * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Task task = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    task = new Task();
                    task.setId(cursor.getInt(0));
                    task.setDone(cursor.getString(1));
                    task.setName(cursor.getString(2));
                    task.setNotes(cursor.getString(3));

                    tasks.add(task);
                } while (cursor.moveToNext());
            }
        }

        Log.d("getAllTasks", tasks.toString());
        return tasks;
    }

    public void deleteTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_ITEMS,
                KEY_ID + " = ?",
                new String[] {String.valueOf(task.getId())});
        db.close();

        Log.d("deleteTask", task.toString());
    }
}
