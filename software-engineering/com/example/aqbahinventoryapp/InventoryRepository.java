package com.example.aqbahinventoryapp;

import android.content.Context;
import java.util.List;

public class InventoryRepository {
    private final DatabaseHelper dbHelper;

    public InventoryRepository(Context context) {
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public List<InventoryItem> getAllItems() {
        return dbHelper.getAllItems();
    }

    public InventoryItem getItem(long id) {
        return dbHelper.getItem(id);
    }

    public long addItem(String name, String sku, int qty, int threshold) {
        return dbHelper.addItem(name, sku, qty, threshold);
    }

    public boolean updateQuantity(long id, int newQty) {
        return dbHelper.updateQuantity(id, newQty);
    }

    public boolean deleteItem(long id) {
        return dbHelper.deleteItem(id);
    }

    public boolean createUser(String username, String password) {
        return dbHelper.createUser(username, password);
    }

    public boolean validateUser(String username, String password) {
        return dbHelper.validateUser(username, password);
    }
}
