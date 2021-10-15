package com.example.bvm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bvm.model.Beverage;


@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {

}
