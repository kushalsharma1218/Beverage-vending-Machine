package com.example.bvm.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Beverage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long beverage_Id;
	
	private String beverage_name;
	private boolean available;
	
	 @OneToMany(mappedBy = "beverage")
	 @JsonManagedReference
	 private List<Ingredients> ingredients;
	
	public Long getBeverage_Id() {
		return beverage_Id;
	}
	public void setBeverage_Id(Long beverage_Id) {
		this.beverage_Id = beverage_Id;
	}
	public String getBeverage_name() {
		return beverage_name;
	}
	public void setBeverage_name(String beverage_name) {
		this.beverage_name = beverage_name;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public List<Ingredients> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
}
