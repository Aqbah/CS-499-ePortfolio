package com.example.aqbahinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    public static final String DB_NAME = "inventory_app.db";
    public static final int DB_VERSION = 2; // Incremented for schema migration

    // Users Table
    public static final String TABLE_USERS = "users";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    // Inventory Table
    public static final String TABLE_INVENTORY = "inventory";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_SKU = "sku";
    public static final String COL_QTY = "quantity";
    public static final String COL_THRESHOLD = "threshold";
    public static final String COL_CREATED_BY = "created_by";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        // Enforce Foreign Key constraints
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_USERNAME + " TEXT PRIMARY KEY, " +
                COL_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(createUsers);

        // Schema with constraints, foreign key, and input verification
        String createInventory = "CREATE TABLE " + TABLE_INVENTORY + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_SKU + " TEXT, " +
                COL_QTY + " INTEGER NOT NULL DEFAULT 0 CHECK (" + COL_QTY + " >= 0), " +
                COL_THRESHOLD + " INTEGER NOT NULL DEFAULT 0 CHECK (" + COL_THRESHOLD + " >= 0), " +
                COL_CREATED_BY + " TEXT, " +
                "FOREIGN KEY(" + COL_CREATED_BY + ") REFERENCES " + TABLE_USERS + "(" + COL_USERNAME + ") " +
                "ON DELETE SET NULL ON UPDATE CASCADE)";
        db.execSQL(createInventory);

        // Indexing for accelerated search performance
        String createIndex = "CREATE INDEX idx_inventory_name ON " + TABLE_INVENTORY + "(" + COL_NAME + ")";
        db.execSQL(createIndex);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }

    // ---------------- Users ----------------
    public boolean createUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USERNAME, username.trim());
        cv.put(COL_PASSWORD, password);

        long result = -1;
        db.beginTransaction();
        try {
            result = db.insertOrThrow(TABLE_USERS, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Error inserting user safely: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
        return result != -1;
    }

    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                TABLE_USERS,
                new String[]{COL_USERNAME},
                COL_USERNAME + "=? AND " + COL_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null
        );
        boolean ok = (c != null && c.moveToFirst());
        if (c != null) c.close();
        return ok;
    }

    // ---------------- Inventory ----------------
    public long addItem(String name, String sku, int qty, int threshold, String username) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_SKU, sku);
        cv.put(COL_QTY, qty);
        cv.put(COL_THRESHOLD, threshold);
        cv.put(COL_CREATED_BY, username);

        long id = -1;
        db.beginTransaction();
        try {
            id = db.insertOrThrow(TABLE_INVENTORY, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Transaction failed for addItem: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
        return id;
    }

    public boolean deleteItem(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = 0;
        db.beginTransaction();
        try {
            rows = db.delete(TABLE_INVENTORY, COL_ID + "=?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Error performing delete transaction: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
        return rows > 0;
    }

    public boolean updateQuantity(long id, int newQty) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_QTY, newQty);

        int rows = 0;
        db.beginTransaction();
        try {
            rows = db.update(TABLE_INVENTORY, cv, COL_ID + "=?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Error updating quantity: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
        return rows > 0;
    }

    public List<InventoryItem> getAllItems() {
        ArrayList<InventoryItem> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_INVENTORY, null, null, null, null, null, COL_NAME + " ASC");
        if (c != null) {
            while (c.moveToNext()) {
                InventoryItem item = new InventoryItem(
                        c.getLong(c.getColumnIndexOrThrow(COL_ID)),
                        c.getString(c.getColumnIndexOrThrow(COL_NAME)),
                        c.getString(c.getColumnIndexOrThrow(COL_SKU)),
                        c.getInt(c.getColumnIndexOrThrow(COL_QTY)),
                        c.getInt(c.getColumnIndexOrThrow(COL_THRESHOLD))
                );
                items.add(item);
            }
            c.close();
        }
        return items;
    }

    public InventoryItem getItem(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_INVENTORY, null, COL_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        InventoryItem item = null;
        if (c != null && c.moveToFirst()) {
            item = new InventoryItem(
                    c.getLong(c.getColumnIndexOrThrow(COL_ID)),
                    c.getString(c.getColumnIndexOrThrow(COL_NAME)),
                    c.getString(c.getColumnIndexOrThrow(COL_SKU)),
                    c.getInt(c.getColumnIndexOrThrow(COL_QTY)),
                    c.getInt(c.getColumnIndexOrThrow(COL_THRESHOLD))
            );
            c.close();
        }
        return item;
    }
}
