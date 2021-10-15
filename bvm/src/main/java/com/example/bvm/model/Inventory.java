package com.example.bvm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long itemId;
	
	private String item_name;
	private int quantity;
	
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String iten_name) {
		this.item_name = iten_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
