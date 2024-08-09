package org.example;

import java.util.List;

public class NearestWarehouseSelectionStrategy implements WarehouseSelectionStrategy {

    @Override
    public Warehouse selectWarehouse(List<Warehouse> warehouseList) {
        // algo to pick the nearest warehouse, for now I am just picking the first warehouse
        // for demo purpose
        return warehouseList.get(0);
    }
}
