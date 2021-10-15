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

import com.example.bvm.model.Ingredients;
import com.example.bvm.response.CustomResponse;
import com.example.bvm.service.IngredientService;


@RestController
public class IngredientsController {
	 @Autowired
	    private IngredientService ingredientService;

	    @GetMapping("/get_all_ingredients")
	    public ResponseEntity getAllIngredients() {
	        try {
	            List<Ingredients> ingredientsList = ingredientService.getAllIngredients();
	            return correctResponse(ingredientsList, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @PostMapping("/add_ingredients")
	    public ResponseEntity addIngredient(@RequestBody Ingredients ingredients) {
	        try {
	            Ingredients ingredients1 = ingredientService.addIngredient(ingredients);
	            return correctResponse(ingredients1, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @PutMapping("/update_ingredients")
	    public ResponseEntity updateIngredients(@RequestBody Ingredients ingredients) {
	        try {
	            Ingredients ingredients1 = ingredientService.updateIngredients(ingredients);
	            return correctResponse(ingredients1, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @DeleteMapping("/delete_ingredients/{id}")
	    public ResponseEntity deleteIngredients(@PathVariable long id) {
	        try {
	            boolean output = ingredientService.deleteIngredients(id);
	            return correctResponse(output, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch (Exception ex) {
	            return errorResponse(ex);
	        }
	    }

	    @GetMapping("/get_ingredients/{id}")
	    public ResponseEntity getIngredients(@PathVariable Long id) {
	        try {
	            Ingredients ingredients = ingredientService.getIngredients(id);
	            return correctResponse(ingredients, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
	        }
	        catch (Exception ex) {
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
