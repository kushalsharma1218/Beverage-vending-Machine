package com.example.bvm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bvm.model.Inventory;
import com.example.bvm.repository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
    private InventoryRepository inventoryRepo;

    public Inventory addInventory(Inventory inventory) throws Exception {
        if(inventory == null) {
            throw new Exception("No inventory found");
        }
        if(inventory.getItem_name() == null || inventory.getQuantity() == 0)
            throw new Exception("values are missing");
        return inventoryRepo.save(inventory);
    }

    public List<Inventory> getInventories() throws Exception {
        List<Inventory> listOfInventories = inventoryRepo.findAll();
        if(listOfInventories.isEmpty()) {
            throw new Exception("NO Inventories Found");
        }
        return listOfInventories;
    }

    public Inventory updateInventory(Inventory inventory) throws Exception {
        if(inventory == null) {
            throw new Exception("No inventory received");
        }
        Inventory inventories1 = getInventory(inventory.getItemId());

        if(inventory.getItem_name() == null)
        	inventory.setItem_name(inventories1.getItem_name());

        if(inventory.getQuantity() == 0)
        	inventory.setQuantity(inventories1.getQuantity());

        return inventoryRepo.save(inventory);
    }

    public boolean deleteInventory(Long id) throws Exception {
        Inventory inventory = getInventory(id);
        try {
            inventoryRepo.delete(inventory);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public Inventory getInventory(Long id) throws Exception {
        Optional<Inventory> inventory = inventoryRepo.findById(id);
        if(!inventory.isPresent())
            throw new Exception("No such inventory exist");
        return inventory.get();
    }

    public Inventory updateQuantity(Long inventoryId, Integer quantity) throws Exception {
    	Inventory inventory = getInventory(inventoryId);
    	inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepo.save(inventory);
    }

    public boolean checkEmpty(Long inventoryId) throws Exception {
        try {
        	Inventory inventory = getInventory(inventoryId);
            return inventory.getQuantity() == 0;
        }
        catch (NullPointerException ex) {
            throw new Exception("No such inventory exist");
        }
    }

    public boolean checkQuantity(Long inventoryId, Integer quantity) throws Exception {
        try {
        	Inventory inventory = getInventory(inventoryId);
            return inventory.getQuantity() >= quantity;
        }
        catch (NullPointerException ex) {
            throw new Exception("No such inventory exist");
        }
    }

    public List<Inventory> getAllEmptiedInventories() throws Exception {
        try {
            List<Inventory> list = getInventories();
            list.removeIf(n -> (n.getQuantity() > 1));
            if(list.isEmpty())
                throw new Exception("Stock is available");
            return list;
        }
        catch (NullPointerException ex) {
            throw new Exception("Unexpected Error");
        }
    }

    public Inventory reduceInventory(Inventory inventory, Integer reduction) {
    	inventory.setQuantity(Math.abs(inventory.getQuantity() - reduction));
        return inventoryRepo.save(inventory);
    }
}
