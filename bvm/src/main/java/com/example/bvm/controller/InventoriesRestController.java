package com.example.bvm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bvm.model.Inventory;
import com.example.bvm.response.CustomResponse;
import com.example.bvm.service.InventoryService;

@Controller
public class InventoriesRestController {
	 @Autowired
	    private InventoryService inventoriesService;

	    @GetMapping("/get_all_inventroies")
	    public ResponseEntity getAllInventories() {
	        HashMap<Object, Object> response = new HashMap<>();
	        try {
	            List<Inventory> list = inventoriesService.getInventories();
	            return correctResponse(list,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch(Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @PostMapping(path = "/add_inventory")
	    public ResponseEntity addInventory(@RequestBody Inventory inventory) {
	        try {
	            Inventory added = inventoriesService.addInventory(inventory);
	            return correctResponse(added,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch(Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @PutMapping("/update_inventory")
	    public ResponseEntity updateInventory(@RequestBody Inventory inventory) {
	        try {
	        	Inventory added = inventoriesService.updateInventory(inventory);
	            return correctResponse(added,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @DeleteMapping(path = "/delete_inventory/{id}")
	    public ResponseEntity deleteInventory(@PathVariable Long id) {
	        try {
	            if (inventoriesService.deleteInventory(id)) {
	                return correctResponse("Inventory deleted successfully",HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
	            } else {
	                return correctResponse("Inventory not deleted",HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"No such Inventory exist",HttpStatus.BAD_REQUEST);
	            }
	        }
	        catch (Exception ex){
	            return errorResponse(ex);
	        }
	    }

	    @GetMapping(path = "/get_inventory/{id}")
	    public ResponseEntity getInventory(@PathVariable Long id) {
	        try {
	        	Inventory inv = inventoriesService.getInventory(id);
	            return correctResponse(inv,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch (Exception ex){
	            return errorResponse(ex);
	        }
	    }

	    @GetMapping(path = "/update_quantity/{id}/{quantity}")
	    public ResponseEntity updateQuantity(@PathVariable Long id, @PathVariable Integer quantity) {
	        try {
	        	Inventory inventories = inventoriesService.updateQuantity(id, quantity);
	            if (inventories == null) {
	                return correctResponse("Null",HttpStatus.NO_CONTENT,HttpStatus.NO_CONTENT.value(),"No Such Inventory Exist",HttpStatus.NO_CONTENT);
	            } else {
	                return correctResponse(inventories,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
	            }
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @GetMapping(path = "/check/{id}")
	    public ResponseEntity checkEmpty(@PathVariable Long id) {
	        try {
	            HashMap<Object, Object> obj = new HashMap<>();
	            boolean output = inventoriesService.checkEmpty(id);
	            obj.put("value", output);
	            String message;
	            if(!output) {
	                obj.put("inventory", inventoriesService.getInventory(id));
	                message = "Inventory is not empty";
	            }
	            else
	                message = "Inventory is empty";
	            return correctResponse(obj,false,HttpStatus.OK.value(),message,HttpStatus.OK);
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @GetMapping(path = "/check_quantity/{id}/{quantity}")
	    public ResponseEntity checkQuantity(@PathVariable Long id, @PathVariable Integer quantity) {
	        try {
	            HashMap<Object, Object> obj = new HashMap<>();
	            boolean output = inventoriesService.checkQuantity(id, quantity);
	            obj.put("value",output);
	            String message;
	            if (output) {
	                obj.put("inventory", inventoriesService.getInventory(id));
	                message = "Inventory is sufficient";
	            } else {
	                message = "Inventory is not sufficient";
	            }
	            return correctResponse(obj,false,HttpStatus.OK.value(),message,HttpStatus.OK);
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @GetMapping(path = "/empty_inventories")
	    public ResponseEntity getEmptyInventories() {
	        try {
	            List<Inventory> list = inventoriesService.getAllEmptiedInventories();
	            return correctResponse(list,false,HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch(Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    public CustomResponse correctResponse(Object value, Object error, int statusCode, String message,HttpStatus status) {
	        return new CustomResponse(status, value, error, statusCode, message);
	    }

	    public CustomResponse errorResponse(Exception ex) {
	        return new CustomResponse(HttpStatus.NOT_FOUND, null, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	    }
}
