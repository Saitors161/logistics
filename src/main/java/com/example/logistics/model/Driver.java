package com.example.logistics.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class for mapping Entity Driver
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drivers")
public class Driver {

    /**
     * Field for storage driver id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Field for storage driver first name
     */
    @Column(name = "first_name")
    @NotEmpty(message = "Drivers first name should not be empty")
    @Size(max = 100, message = "Max size for drivers first name = 100 characters")
    private String firstName;

    /**
     * Field for storage driver last name
     */
    @Column(name = "lastName")
    @NotEmpty(message = "Drivers last name should not be empty")
    @Size(max = 100, message = "Max size for drivers last name = 100 characters")
    private String lastName;

    /**
     * Field for storage driver license
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drivers_license_id", referencedColumnName = "id")
    private DriversLicense driversLicense;


    /**
     * Field for storage driver cars
     * @see Car
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "drivers_cars",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
}
