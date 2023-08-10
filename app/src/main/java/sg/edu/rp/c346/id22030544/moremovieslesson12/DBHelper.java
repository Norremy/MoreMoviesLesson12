package sg.edu.rp.c346.id22030544.moremovieslesson12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import sg.edu.rp.c346.id22030544.moremovieslesson12.Movie;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 2;
    private static final String DATABASE_NAME = "movies.db";

    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE+ " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " TEXT)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");
        for (int i = 0; i< 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, "Data number " + i);
            db.insert(TABLE_MOVIE, null, values);
        }
        Log.i("info", "dummy records inserted");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
//        // Create table(s) again
        db.execSQL("ALTER TABLE " + TABLE_MOVIE + " ADD COLUMN  module_name TEXT ");

//        onCreate(db);
    }

    public void insertMovie(String title, String genre, String rating, Integer  year) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_GENRE, genre);
        // Insert the row into the TABLE_TASK
        values.put(COLUMN_YEAR, year);

        values.put(COLUMN_RATING, rating );

        db.insert(TABLE_MOVIE, null, values);
        // Close the database connection
        db.close();
    }


    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(id, title,genre, rating,year);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }
    public ArrayList<Movie> getMoviesfiltered() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String selection = "rating=? ";
        String[] selectionArgs = {"PG13"};
        Cursor cursor = db.query(TABLE_MOVIE, columns, selection, selectionArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(id,title,genre,rating,year);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

    public int updateMovie(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getRating());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIE, values, condition, args);

        db.close();
        return result;
    }
    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }
    public ArrayList<Movie> getAllMovies(String keyword) {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String condition = COLUMN_TITLE + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_MOVIE, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(id,title,genre, rating,year);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }


}
