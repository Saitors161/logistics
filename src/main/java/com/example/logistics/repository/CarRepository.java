package com.example.logistics.repository;

import com.example.logistics.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Interface for mapping Entity Car with Database by using JpaRepository
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Car
 * @see JpaRepository
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
