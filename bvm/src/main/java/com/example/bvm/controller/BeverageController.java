package com.example.bvm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bvm.model.Beverage;
import com.example.bvm.model.Ingredients;
import com.example.bvm.model.Inventory;
import com.example.bvm.response.CustomResponse;
import com.example.bvm.service.BeverageService;
import com.example.bvm.service.InventoryService;

@RestController
public class BeverageController {

	@Autowired(required = true)
    private BeverageService beverageService;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/get_all_beverage")
    public ResponseEntity getAllBeverages() {
        try {
            List<Beverage> list = beverageService.getBeverages();
            return correctResponse(list,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PostMapping("/add_beverage")
    public ResponseEntity addBeverage(@RequestBody Beverage beverage) {
        try {
            Beverage beverage1 = beverageService.addBeverage(beverage);
            return correctResponse(beverage1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping("/update_beverage")
    public ResponseEntity updateBeverage(@RequestBody Beverage beverage) {
        try {
            Beverage beverage1 = beverageService.updateBeverage(beverage);
            return correctResponse(beverage1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @DeleteMapping("/delete_beverage/{id}")
    public ResponseEntity deleteBeverage(@PathVariable Long id) {
        try {
            beverageService.deleteBeverage(id);
            return correctResponse("Beverage deleted successfully",HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/get_beverage/{id}")
    public ResponseEntity getBeverage(@PathVariable Long id) {
        try {
            Beverage beverage = beverageService.getBeverage(id);
            return correctResponse(beverage,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/bevareg_available/{id}")
    public ResponseEntity checkAvailability(@PathVariable Long id) {
        try {
            boolean output = beverageService.checkAvailability(id);
            return correctResponse(output,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/get_bevarage_available")
    public ResponseEntity checkAvailability() {
        try {
            List<Beverage> beverageList = beverageService.checkAvailability();
            return correctResponse(beverageList,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/order_bevarage/{id}")
    public ResponseEntity orderBeverage(@PathVariable Long id) {
        try {
            if(!beverageService.checkAvailability(id)) {
                List<Beverage> beverageList = beverageService.checkAvailability();
                return correctResponse(beverageList,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"Requested Beverage is not available. Here are the beverages which are available",HttpStatus.BAD_REQUEST);
            }
            else {
                Beverage beverage = beverageService.getBeverage(id);
                List<Ingredients> ingredientsList = beverage.getIngredients();
                for(Ingredients i : ingredientsList) {
                    Inventory inventories = inventoryService.reduceInventory(i.getInventories(),i.getQuantiy_required());
                    inventoryService.updateInventory(inventories);
                }
                beverageService.updateList();
                return correctResponse(beverage,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
            }
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

