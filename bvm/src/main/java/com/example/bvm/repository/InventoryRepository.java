package com.example.bvm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bvm.model.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

}
