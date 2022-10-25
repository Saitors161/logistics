package com.example.logistics.repository;

import com.example.logistics.model.Car;
import com.example.logistics.model.Driver;
import com.example.logistics.model.DriversLicense;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Interface for mapping Entity Driver with Database by using JpaRepository
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Driver
 * @see JpaRepository
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    @Override
    @EntityGraph(attributePaths = {"driversLicense"})
    Driver save(Driver driver);

    @Override
    @EntityGraph(attributePaths = {"driversLicense"})
    List<Driver> findAll();
}
