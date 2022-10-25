package com.example.logistics.model;

import com.example.logistics.dto.DriverDto;
import com.example.logistics.enums.DriverCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class for mapping Entity Car
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cars")
public class Car {
    /**
     * Field for storage car id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Field for storage car category
     */
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Cars category should not be empty")
    private DriverCategory category;

    /**
     * Field for storage car number
     */
    @Column(name = "number")
    @NotEmpty(message = "Cars number should not be empty")
    @Size(max = 100, message = "Max size for car number = 100 characters")
    private String number;

    /**
     * Field for storage car drivers
     */
    @ManyToMany(mappedBy = "cars", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Driver> drivers;

}
