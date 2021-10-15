package com.example.bvm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bvm.model.Ingredients;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredients, Long> {

}
