package com.example.aqbahinventoryapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AddItemActivity extends AppCompatActivity {

    private InventoryRepository repository;
    private EditText etName, etSku, etQty, etThreshold;
    private static final String DEFAULT_SMS_TARGET = "5556";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        repository = new InventoryRepository(this);

        etName = findViewById(R.id.etItemName);
        etSku = findViewById(R.id.etSKU);
        etQty = findViewById(R.id.etQuantity);
        etThreshold = findViewById(R.id.etThreshold);

        Button btnSave = findViewById(R.id.btnSaveItem);
        btnSave.setOnClickListener(v -> saveItem());
    }

    private void saveItem() {
        String name = nonNull(etName.getText());
        String sku = nonNull(etSku.getText());
        String qtyStr = nonNull(etQty.getText());
        String thrStr = nonNull(etThreshold.getText());

        if (name.isEmpty() || qtyStr.isEmpty() || thrStr.isEmpty()) {
            Toast.makeText(this, "Name, Quantity, and Threshold are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int qty;
        int thr;

        // Defensive Programming: Safely parse integer inputs
        try {
            qty = Integer.parseInt(qtyStr);
            thr = Integer.parseInt(thrStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Quantity and Threshold must be valid integers", Toast.LENGTH_SHORT).show();
            return;
        }

        if (qty < 0 || thr < 0) {
            Toast.makeText(this, "Values cannot be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        long id = repository.addItem(name, sku, qty, thr);
        if (id != -1) {
            Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();

            if (qty <= thr && ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_GRANTED) {
                InventoryItem item = new InventoryItem(id, name, sku, qty, thr);
                SmsUtils.sendLowInventorySms(this, DEFAULT_SMS_TARGET, item);
            }

            finish();
        } else {
            Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show();
        }
    }

    private static String nonNull(CharSequence cs) {
        return cs == null ? "" : cs.toString().trim();
    }
}
