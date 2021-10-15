package com.example.bvm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long indregient_id;
	
	@ManyToOne
    @JsonBackReference
    private Beverage beverage;

    @ManyToOne
    private Inventory inventories;
	
	private int quantiy_required;

	public Long getIndregient_id() {
		return indregient_id;
	}

	public void setIndregient_id(Long indregient_id) {
		this.indregient_id = indregient_id;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}

	public Inventory getInventories() {
		return inventories;
	}

	public void setInventories(Inventory inventories) {
		this.inventories = inventories;
	}

	public int getQuantiy_required() {
		return quantiy_required;
	}

	public void setQuantiy_required(int quantiy_required) {
		this.quantiy_required = quantiy_required;
	}
	
	
}
