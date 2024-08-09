package org.example;

import java.util.List;

public interface WarehouseSelectionStrategy {
    Warehouse selectWarehouse(List<Warehouse> warehouseList);
}
