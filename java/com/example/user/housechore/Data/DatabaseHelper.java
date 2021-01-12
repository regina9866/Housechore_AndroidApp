package com.example.user.housechore.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.user.housechore.Helpers.Constants;
import com.example.user.housechore.Model.Chore;
import com.example.user.housechore.Model.Contact;
//import com.example.user.housechore.Model.Bowl;
//import com.example.user.housechore.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentine on 4/15/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Context in which this database exists.
    private Context mContext;

    // Database version.
    public static final int DATABASE_VERSION = 3;

    // Database name.
    public static final String DATABASE_NAME = "ImageExample";

    // Table names.
    public static final String TABLE_CONTACTS = "family";
    public static final String TABLE_BOWLS="bowls";
    public static final String TABLE_CHORES="chores";

    private final static String TAG = DatabaseHelper.class.getSimpleName();
    private android.database.sqlite.SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public void onCreate() {
//        db.execSQL(CREATE_CONTACTS_TABLE);
//        db.execSQL(CREATE_BOWL_TABLE);
//        db.execSQL(CREATE_CHORE_TABLE);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_BOWL_TABLE);
        db.execSQL(CREATE_CHORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_BOWLS);
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_CHORES);
    }

    // Command to create a table of Recipes.
    private static final String CREATE_CONTACTS_TABLE = " CREATE TABLE IF NOT EXISTS  " +  TABLE_CONTACTS + " ( "
            + Constants.COLUMN_CONTACT_ID + "  INTEGER PRIMARY KEY , "
            + Constants.COLUMN_CONTACT_NAME + "  TEXT , "
            + Constants.COLUMN_PHONENUMBER +"  TEXT ) ";

    // Command to create a table of Chores.
    private static final String CREATE_CHORE_TABLE = "CREATE TABLE IF NOT EXISTS " +  TABLE_CHORES + " ("
            + Constants.COLUMN_CHORE_ID + " INTEGER PRIMARY KEY, "
            + Constants.COLUMN_CHORE_NAME + " TEXT, "
            + Constants.COLUMN_POINT +" INTEGER, "
            + Constants.COLUMN_HOWLONG + " TEXT, "
            + Constants.COLUMN_DUEDATE + " TEXT, "
            + Constants.COLUMN_NOTE + " TEXT, "
            + Constants.COLUMN_IMAGE_PATH + " TEXT) ";

    // Command to create a table of Bowls.
    private static final String CREATE_BOWL_TABLE ="CREATE TABLE " +  TABLE_BOWLS + " ("
            + Constants.COLUMN_BOWL_ID + " INTEGER PRIMARY KEY, "
            + Constants.COLUMN_BOWL_NAME + " TEXT, "
            + Constants.COLUMN_BOWL_TYPE +" TEXT, "
            + Constants.COLUMN_BOWL_ROWS +" INTEGER, "
            + Constants.COLUMN_BOWL_COLS +" INTEGER, "
            + Constants.COLUMN_BOWL_EDGE_LEFT_X + " TEXT, "
            + Constants.COLUMN_BOWL_EDGE_LEFT_Y + " TEXT, "
            + Constants.COLUMN_BOWL_EDGE_RIGHT_X + " TEXT, "
            + Constants.COLUMN_BOWL_EDGE_RIGHT_Y + " TEXT, "
            + Constants.COLUMN_BOWL_HEIGHT + " FLOAT, "
            + Constants.COLUMN_BOWL_WIDTH + " FLOAT, "
            + Constants.COLUMN_BOWL_VOLUME + " FLOAT, "
            + Constants.COLUMN_BOWL_CANNY_IMAGE_PATH + " TEXT) ";

    // Database lock to prevent conflicts.
    public static final Object[] databaseLock = new Object[0];
//

    public List<Contact> getAllContact() {
        //Initialize an empty list of Customers
        List<Contact> contactList = new ArrayList<Contact>();

        //Command to select all Customers
        String selectQuery = " SELECT *  FROM  " +  TABLE_CONTACTS;

        //lock database for reading
        synchronized (databaseLock) {
            //Get a readable database
            SQLiteDatabase database = getReadableDatabase();
            //Make sure database is not empty
            if (database != null ) {
                //Get a cursor for all Customers in the database
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        Contact contact = getContact(cursor);
                        contactList.add(contact);
                        cursor.moveToNext();
                    }
                }
                //Close the database connection
                database.close();
            }
            //Return the list of customers
            return contactList;
        }

    }
    public List<Chore> getAllChore() {
        //Initialize an empty list of Customers
        List<Chore> choreList = new ArrayList<Chore>();

        //Command to select all Customers
        String selectQuery = "SELECT * FROM " +  TABLE_CHORES+" ORDER BY "+Constants.COLUMN_CHORE_ID+" DESC";

        //lock database for reading
        synchronized (databaseLock) {
            //Get a readable database
            SQLiteDatabase database = getReadableDatabase();

            //Make sure database is not empty
            if (database != null) {

                //Get a cursor for all Customers in the database
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        Chore chore = getChore(cursor);
                        choreList.add(chore);
                        cursor.moveToNext();
                    }
                }
                //Close the database connection
                database.close();
            }
            //Return the list of customers
            return choreList;
        }

    }


//
//    public List<Bowl> getAllBowls() {
//        Log.d(TAG,"GET BOWL LIST START::");
//
//        //Initialize an empty list of Bowls
//        List<Bowl> bowlList = new ArrayList<Bowl>();
//
//        //Command to select all Bowls
//        String selectQuery = "SELECT * FROM " +  TABLE_BOWLS;
//
//        //lock database for reading
//        synchronized (databaseLock) {
//            //Get a readable database
//            SQLiteDatabase database = getReadableDatabase();
//
//            //Make sure database is not empty
//            if (database != null) {
//
//                //Get a cursor for all Customers in the database
//                Cursor cursor = database.rawQuery(selectQuery, null);
//                if (cursor.moveToFirst()) {
//                    while (!cursor.isAfterLast()) {
//                        Bowl bowl = getBowl(cursor);
//                        bowlList.add(bowl);
//                        cursor.moveToNext();
//                    }
//                }
//                //Close the database connection
//                database.close();
//            }
//            //Return the list of customers
//            Log.d(TAG,"GET BOWL LIST DONE::");
//            return bowlList;
//        }
//
//    }
//    public List<Contact> getAllFavoriteContact(){
//        Log.d(TAG,"GetAllFavoriteRecipe");
//        //Initialize an empty list of Customers
//        List<Contact> recipeList = new ArrayList<Contact>();
//
//        //Command to select all Customers
//        String selectQuery = "SELECT * FROM " +  TABLE_CONTACTS+ " WHERE " +Constants.COLUMN_STAR+" = '"+Constants.FAVORITE_RECIPE+"'";
//        Log.d(TAG,selectQuery);
//        //lock database for reading
//        synchronized (databaseLock) {
//            //Get a readable database
//            SQLiteDatabase database = getReadableDatabase();
//
//            //Make sure database is not empty
//            if (database != null) {
//
//                //Get a cursor for all Customers in the database
//                Cursor cursor = database.rawQuery(selectQuery, null);
//                if (cursor.moveToFirst()) {
//                    while (!cursor.isAfterLast()) {
//                        Recipe recipe = null;
//                        recipeList.add(recipe);
//                        cursor.moveToNext();
//                    }
//                }
//                //Close the database connection
//                database.close();
//            }
//            //Return the list of customers
//            return recipeList;
//        }
//    }

    public List<Chore> getAllFavoriteChore(){
        Log.d(TAG,"GetAllFavoriteChore");
        //Initialize an empty list of Customers

        List<Chore> choreList = new ArrayList<Chore>();

        //Command to select all Customers
        String selectQuery = "SELECT * FROM " +  TABLE_CHORES+ " WHERE " +Constants.COLUMN_STAR+" = '"+Constants.FAVORITE_RECIPE+"'";
        Log.d(TAG,selectQuery);
        //lock database for reading
        synchronized (databaseLock) {
            //Get a readable database
            SQLiteDatabase database = getReadableDatabase();

            //Make sure database is not empty
            if (database != null) {

                //Get a cursor for all Customers in the database
                Cursor cursor = database.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        Chore chore = getChore(cursor);
                        choreList.add(chore);
                        cursor.moveToNext();
                    }
                }
                //Close the database connection
                database.close();
            }
            //Return the list of customers
            return choreList;
        }
    }
    private static Contact getContact(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_CONTACT_ID)));
        contact.setName(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CONTACT_NAME)));
        contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PHONENUMBER)));
        return contact;
    }

    private static Chore getChore(Cursor cursor) {
        Chore chore = new Chore();
        chore.setId(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_RECIPE_ID)));
        chore.setChoreName(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_CHORE_NAME)));
        chore.setmPoint(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_POINT)));
        chore.setmHowlong(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_HOWLONG)));
        chore.setDueDate(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_DUEDATE)));
        chore.setmNote(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NOTE)));
        chore.setImagePath(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_IMAGE_PATH)));
        return chore;
    }
//
//    private static Bowl getBowl(Cursor cursor) {
//        Bowl bowl = new Bowl();
//
//        bowl.setId(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_BOWL_ID)));
//        bowl.setName(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BOWL_NAME)));
//        bowl.setType(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BOWL_TYPE)));
//
//        bowl.setRowsLength(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_BOWL_ROWS)));
//        bowl.setColsLength(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_BOWL_COLS)));
//
//        bowl.setEdgeLeftTop(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BOWL_EDGE_LEFT_X)));
//        bowl.setEdgeLeftDown(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BOWL_EDGE_LEFT_Y)));
//        bowl.setEdgeRightTop(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BOWL_EDGE_RIGHT_X)));
//        bowl.setEdgeRightDown(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BOWL_EDGE_RIGHT_Y)));
//
//        bowl.setHeight(cursor.getFloat(cursor.getColumnIndex(Constants.COLUMN_BOWL_HEIGHT)));
//        bowl.setWidth(cursor.getFloat(cursor.getColumnIndex(Constants.COLUMN_BOWL_WIDTH)));
//        bowl.setVolume(cursor.getFloat(cursor.getColumnIndex(Constants.COLUMN_BOWL_VOLUME)));
//
//        bowl.setCannyImagePath(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_BOWL_CANNY_IMAGE_PATH)));
//        Log.d(TAG,"GET BOWL : "+bowl.getId() +" :: " +bowl.getName());
//
//        return bowl;
//    }

    public Long addContact(Contact contact) {
        Long ret = null;

        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            SQLiteDatabase database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Prepare the Recipe information that will be saved to the database
                ContentValues values = new ContentValues();

                values.put(Constants.COLUMN_CONTACT_NAME, contact.getName());
                values.put(Constants.COLUMN_PHONENUMBER, contact.getPhoneNumber());

                //Attempt to insert the client information into the transaction table
                try {
                    ret = database.insert( TABLE_CONTACTS, null, values);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to add Family to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }
    public Long addChore(Chore chore) {
        Long ret = null;

        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            SQLiteDatabase database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Prepare the Recipe information that will be saved to the database
                ContentValues values = new ContentValues();

//                values.put(Constants.COLUMN_TITLE, chore.getTitle());
//                values.put(Constants.COLUMN_STAR, chore.getStar());
//                values.put(Constants.COLUMN_INGREDIENT, chore.getIngredient());
//                values.put(Constants.COLUMN_RECIPE_ORDER, chore.getRecipeOrder());
//                values.put(Constants.COLUMN_AMOUNT, chore.getAmount());
                values.put(Constants.COLUMN_CHORE_NAME,chore.getChoreName());
                values.put(Constants.COLUMN_POINT,chore.getmPoint());
                values.put(Constants.COLUMN_HOWLONG,chore.getmHowlong());
                values.put(Constants.COLUMN_DUEDATE,chore.getmDueDate());
                values.put(Constants.COLUMN_NOTE,chore.getmNote());
                values.put(Constants.COLUMN_IMAGE_PATH, chore.getImagePath());

                //Attempt to insert the client information into the transaction table
                try {
                    ret = database.insert( TABLE_CHORES, null, values);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to add Chore to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }
//    public Long addBowl(Bowl bowl) {
//        Long ret = null;
//
//        //Lock database for writing
//        synchronized (databaseLock) {
//            //Get a writable database
//            SQLiteDatabase database = getWritableDatabase();
//
//            //Ensure the database exists
//            if (database != null) {
//                //Prepare the Recipe information that will be saved to the database
//                ContentValues values = new ContentValues();
//
//                values.put(Constants.COLUMN_BOWL_NAME, bowl.getName());
//                values.put(Constants.COLUMN_BOWL_TYPE, bowl.getType());
//
//                values.put(Constants.COLUMN_BOWL_ROWS, bowl.getRowsLength());
//                values.put(Constants.COLUMN_BOWL_COLS, bowl.getColsLength());
//
//                values.put(Constants.COLUMN_BOWL_EDGE_LEFT_X, bowl.getEdgeLeftTop().toString());
//                values.put(Constants.COLUMN_BOWL_EDGE_LEFT_Y, bowl.getEdgeLeftDown().toString());
//                values.put(Constants.COLUMN_BOWL_EDGE_RIGHT_X, bowl.getEdgeRightTop().toString());
//                values.put(Constants.COLUMN_BOWL_EDGE_RIGHT_Y, bowl.getEdgeRightDown().toString());
//
//                values.put(Constants.COLUMN_BOWL_HEIGHT, bowl.getHeight());
//                values.put(Constants.COLUMN_BOWL_WIDTH, bowl.getWidth());
//                values.put(Constants.COLUMN_BOWL_VOLUME, bowl.getVolume());
//                values.put(Constants.COLUMN_BOWL_CANNY_IMAGE_PATH, bowl.getCannyImagePath());
//
//                //Attempt to insert the client information into the transaction table
//                try {
//                    ret = database.insert( TABLE_BOWLS, null, values);
//                } catch (Exception e) {
//                    Log.e(TAG, "Unable to add Bowls to database " + e.getMessage());
//                }
//                //Close database connection
//                database.close();
//            }
//        }
//        return ret;
//    }


    public Contact getContactById(long id){
        List<Contact> tempContactList = getAllContact();
        for (Contact contact : tempContactList){
            if (contact.getId() == id){
                return contact;
            }
        }
        return null;
    }

    public Chore getChoreById(long id){
        List<Chore> tempChoreList = getAllChore();
        for (Chore chore : tempChoreList){
            if (chore.getId() == id){
                return chore;
            }
        }
        return null;
    }
//    public Bowl getBowlById(long id){
//        List<Bowl> tempBowlList = getAllBowls();
//        for (Bowl bowl : tempBowlList){
//            if (bowl.getId() == id){
//                return bowl;
//            }
//        }
//        return null;
//    }


    public boolean contactExists(long id){
        //Check if there is an existing customer
        List<Contact> tempContactList = getAllContact();
        for (Contact contact : tempContactList){
            if (contact.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean choreExists(long id){
        //Check if there is an existing customer
        List<Chore> tempChoreList = getAllChore();
        for (Chore chore : tempChoreList){
            if (chore.getId() == id){
                return true;
            }
        }
        return false;
    }
//    public boolean bowlExists(long id){
//        //Check if there is an existing customer
//        List<Bowl> tempBowlList = getAllBowls();
//        for (Bowl bowl : tempBowlList){
//            if (bowl.getId() == id){
//                return true;
//            }
//        }
//        return false;
//    }


    public int removeContact(Contact contact){
        int ret =0;
        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            SQLiteDatabase database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Attempt to delete the recipe information into the transaction table
                try {
                    ret = database.delete(TABLE_CONTACTS, Constants.COLUMN_CONTACT_ID + "=" + contact.getId(), null);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to delete Family to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }
    public int removeChore(Chore chore){
        int ret =0;
        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            SQLiteDatabase database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Attempt to delete the recipe information into the transaction table
                try {
                    ret = database.delete(TABLE_CHORES, Constants.COLUMN_CHORE_ID + "=" + chore.getId(), null);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to delete Recipe to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }
//    public int removeBowl(Bowl bowl){
//        int ret =0;
//        //Lock database for writing
//        synchronized (databaseLock) {
//            //Get a writable database
//            SQLiteDatabase database = getWritableDatabase();
//
//            //Ensure the database exists
//            if (database != null) {
//                //Attempt to delete the recipe information into the transaction table
//                try {
//                    ret = database.delete(TABLE_BOWLS, Constants.COLUMN_BOWL_ID + "=" + bowl.getId(), null);
//                } catch (Exception e) {
//                    Log.e(TAG, "Unable to delete Bowl to database " + e.getMessage());
//                }
//                //Close database connection
//                database.close();
//            }
//        }
//        return ret;
//    }


    public int updateContact(Contact contact) {
        int ret = 0;
        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            SQLiteDatabase database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Prepare the Recipe information that will be saved to the database
                ContentValues values = new ContentValues();

                values.put(Constants.COLUMN_CONTACT_NAME, contact.getName());
                values.put(Constants.COLUMN_PHONENUMBER, contact.getPhoneNumber());

//                values.put(Constants.COLUMN_IMAGE_PATH, contact.getImagePath());

                //Attempt to insert the client information into the transaction table
                try {
                    ret = database.update(TABLE_CONTACTS, values, Constants.COLUMN_CONTACT_ID + "=" + contact.getId(), null);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to update Family to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }

    public int updateChore(Chore chore) {
        int ret = 0;
        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            SQLiteDatabase database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Prepare the Recipe information that will be saved to the database
                ContentValues values = new ContentValues();
                values.put(Constants.COLUMN_CHORE_NAME, chore.getChoreName());
                values.put(Constants.COLUMN_POINT, chore.getmPoint());
                values.put(Constants.COLUMN_HOWLONG, chore.getmHowlong());
                values.put(Constants.COLUMN_DUEDATE, chore.getDueDate());
                values.put(Constants.COLUMN_NOTE, chore.getmNote());
                values.put(Constants.COLUMN_IMAGE_PATH, chore.getImagePath());

                //Attempt to insert the client information into the transaction table
                try {
                    ret = database.update(TABLE_CHORES, values, Constants.COLUMN_CHORE_ID + "=" + chore.getId(), null);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to update Recipe to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }

//    public int updateBowl(Bowl bowl) {
//        int ret = 0;
//        //Lock database for writing
//        synchronized (databaseLock) {
//            //Get a writable database
//            SQLiteDatabase database = getWritableDatabase();
//
//            //Ensure the database exists
//            if (database != null) {
//                //Prepare the Recipe information that will be saved to the database
//                ContentValues values = new ContentValues();
//
//                values.put(Constants.COLUMN_BOWL_NAME, bowl.getName());
//                values.put(Constants.COLUMN_BOWL_TYPE, bowl.getType());
//
//                values.put(Constants.COLUMN_BOWL_ROWS, bowl.getRowsLength());
//                values.put(Constants.COLUMN_BOWL_COLS, bowl.getColsLength());
//
//                values.put(Constants.COLUMN_BOWL_EDGE_LEFT_X, bowl.getEdgeLeftTop().toString());
//                values.put(Constants.COLUMN_BOWL_EDGE_LEFT_Y, bowl.getEdgeLeftDown().toString());
//                values.put(Constants.COLUMN_BOWL_EDGE_RIGHT_X, bowl.getEdgeRightTop().toString());
//                values.put(Constants.COLUMN_BOWL_EDGE_RIGHT_Y, bowl.getEdgeRightDown().toString());
//
//                values.put(Constants.COLUMN_BOWL_HEIGHT, bowl.getHeight());
//                values.put(Constants.COLUMN_BOWL_WIDTH, bowl.getWidth());
//                values.put(Constants.COLUMN_BOWL_VOLUME, bowl.getVolume());
//                values.put(Constants.COLUMN_BOWL_CANNY_IMAGE_PATH, bowl.getCannyImagePath());
//
//                //Attempt to insert the client information into the transaction table
//                try {
//                    ret = database.update(TABLE_BOWLS, values, Constants.COLUMN_BOWL_ID + "=" + bowl.getId(), null);
//                } catch (Exception e) {
//                    Log.e(TAG, "Unable to update Bowl to database " + e.getMessage());
//                }
//                //Close database connection
//                database.close();
//            }
//        }
//        return ret;
//    }

}


