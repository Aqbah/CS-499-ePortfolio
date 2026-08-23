package com.example.aqbahinventoryapp;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryRepository {
    private final DatabaseHelper dbHelper;
    private final Map<Long, InventoryItem> itemCache = new HashMap<>();

    public InventoryRepository(Context context) {
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    // Dynamic O(1) retrieval using HashMap Cache
    public List<InventoryItem> getAllItems() {
        List<InventoryItem> items = dbHelper.getAllItems();
        itemCache.clear();
        for (InventoryItem item : items) {
            itemCache.put(item.getId(), item);
        }
        return items;
    }

    // Fast O(1) lookup
    public InventoryItem getItem(long id) {
        if (itemCache.containsKey(id)) {
            return itemCache.get(id);
        }
        InventoryItem item = dbHelper.getItem(id);
        if (item != null) {
            itemCache.put(item.getId(), item);
        }
        return item;
    }

    // Binary Search interface method O(log n)
    public InventoryItem searchByName(String name) {
        List<InventoryItem> items = getAllItems();
        // Ensure sorted state before binary searching
        items.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        int index = InventorySorter.binarySearchByName(items, name);
        return index != -1 ? items.get(index) : null;
    }

    public long addItem(String name, String sku, int qty, int threshold) {
        long id = dbHelper.addItem(name, sku, qty, threshold);
        if (id != -1) {
            itemCache.put(id, new InventoryItem(id, name, sku, qty, threshold));
        }
        return id;
    }

    public boolean updateQuantity(long id, int newQty) {
        boolean success = dbHelper.updateQuantity(id, newQty);
        if (success && itemCache.containsKey(id)) {
            itemCache.get(id).setQuantity(newQty);
        }
        return success;
    }

    public boolean deleteItem(long id) {
        boolean success = dbHelper.deleteItem(id);
        if (success) {
            itemCache.remove(id);
        }
        return success;
    }

    public boolean createUser(String username, String password) {
        return dbHelper.createUser(username, password);
    }

    public boolean validateUser(String username, String password) {
        return dbHelper.validateUser(username, password);
    }
}
