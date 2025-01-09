package project.an.readnewsapp.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ReadNews.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "bookmark";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_IMG = "image_path";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_DATE = "pub_date";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("+
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_TITLE + " TEXT, "+
                    COLUMN_LINK + " TEXT, "+
                    COLUMN_IMG + " TEXT, "+
                    COLUMN_CONTENT + " TEXT, "+
                    COLUMN_DATE + " TEXT) ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String title, String link, String img, String content, String pubDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_LINK, link);
        values.put(COLUMN_IMG, img);
        values.put(COLUMN_DATE, pubDate);
        values.put(COLUMN_CONTENT, content);
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
