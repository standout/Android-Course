package com.example.hariskljajic.picassooptimizedadapter.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hariskljajic.picassooptimizedadapter.Data.Contracts.ContractGallery;
import com.example.hariskljajic.picassooptimizedadapter.Models.GalleryFrame;

/**
 * Created by Haris Kljajic on 2014-09-17.
 */
public class GalleryDbHelper extends SQLiteOpenHelper {

    // Constants
    private final static String DATABASE_NAME = "GalleryTable.db";
    private final static int DATABASE_VERSION = 4;
    private final static String COMMA_SEP = ",";
    private final static String TEXT_TYPE = " TEXT";

    // Projection
    String[] projection = {
            ContractGallery.Gallery._ID,
            ContractGallery.Gallery.COLUMN_NAME_URL,
            ContractGallery.Gallery.COLUMN_NAME_NAME,
            ContractGallery.Gallery.COLUMN_NAME_DESCRIPTION
    };

    // Variables
    SQLiteDatabase db;


    // Database creation SQL statement
    private static final String SQL_CREATE_GALLERY =
            "CREATE TABLE " + ContractGallery.Gallery.TABLE_NAME + " (" +
                    ContractGallery.Gallery._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    ContractGallery.Gallery.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ContractGallery.Gallery.COLUMN_NAME_URL + TEXT_TYPE + COMMA_SEP +
                    ContractGallery.Gallery.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + " )";

    // Database delete SQL statement
    private static final String SQL_DELETE_GALLERY =
            "DROP TABLE IF EXISTS " + ContractGallery.Gallery.TABLE_NAME;

    public GalleryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Create database method
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(SQL_CREATE_GALLERY);
            Log.d("GalleryDbHelper", "Table created version" + DATABASE_VERSION);
        }
        catch (Exception ex){
            Log.d("GalleryDbHelper", ex.getMessage());
        }
    }
    // Update database method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion != oldVersion){
            try{
                db.execSQL(SQL_DELETE_GALLERY);
                onCreate(db);
            }
            catch (Exception ex){
                Log.d("GalleryDbHelper", ex.getMessage());
            }

        }
    }

    // Get database data
    public Cursor get(){

        db = getReadableDatabase();

        Cursor cursor = db.query(
                ContractGallery.Gallery.TABLE_NAME,  // The table to query
                projection,              // The columns to return
                null,                    // The columns for the WHERE clause
                null,                    // The values for the WHERE clause
                null,                    // don't group the rows
                null,                    // don't filter by row groups
                null                     // The sort order
        );

        return cursor;
    }
    // Insert database data
    public long insert(GalleryFrame galleryFrame){

        db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContractGallery.Gallery.COLUMN_NAME_NAME, galleryFrame.getName());
        contentValues.put(ContractGallery.Gallery.COLUMN_NAME_URL, galleryFrame.getUrl());
        contentValues.put(ContractGallery.Gallery.COLUMN_NAME_DESCRIPTION, galleryFrame.getDescription());

        long id = db.insert(ContractGallery.Gallery.TABLE_NAME, null, contentValues);

        return id;
    }
}
