package com.example.aqbahinventoryapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventorySorter {

    // QuickSort Implementation for Quantity Sorting (Descending or Ascending)
    public static void sortByQuantity(List<InventoryItem> items, boolean ascending) {
        if (items == null || items.size() <= 1) return;
        quickSortQuantity(items, 0, items.size() - 1, ascending);
    }

    private static void quickSortQuantity(List<InventoryItem> items, int low, int high, boolean ascending) {
        if (low < high) {
            int pivotIndex = partitionQuantity(items, low, high, ascending);
            quickSortQuantity(items, low, pivotIndex - 1, ascending);
            quickSortQuantity(items, pivotIndex + 1, high, ascending);
        }
    }

    private static int partitionQuantity(List<InventoryItem> items, int low, int high, boolean ascending) {
        int pivot = items.get(high).getQuantity();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = ascending ? (items.get(j).getQuantity() <= pivot) : (items.get(j).getQuantity() >= pivot);
            if (condition) {
                i++;
                Collections.swap(items, i, j);
            }
        }
        Collections.swap(items, i + 1, high);
        return i + 1;
    }

    // Binary Search Implementation O(log n) on sorted list by Name
    public static int binarySearchByName(List<InventoryItem> sortedItems, String targetName) {
        if (sortedItems == null || targetName == null) return -1;

        int low = 0;
        int high = sortedItems.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = targetName.compareToIgnoreCase(sortedItems.get(mid).getName());

            if (res == 0) return mid;
            if (res > 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
