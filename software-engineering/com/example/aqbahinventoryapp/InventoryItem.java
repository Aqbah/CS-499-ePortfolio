package com.example.aqbahinventoryapp;

public class InventoryItem {
    private long id;
    private String name;
    private String sku;
    private int quantity;
    private int threshold;

    public InventoryItem(long id, String name, String sku, int quantity, int threshold) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }
    public int getQuantity() { return quantity; }
    public int getThreshold() { return threshold; }

    // Setters
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSku(String sku) { this.sku = sku; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setThreshold(int threshold) { this.threshold = threshold; }
}
