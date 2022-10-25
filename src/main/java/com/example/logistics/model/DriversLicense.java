package com.example.logistics.model;

import com.example.logistics.enums.DriverCategory;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for mapping Entity DriversLicense
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drivers_licenses")
public class DriversLicense {

    /**
     * Field for storage driver license id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Field for storage driver license date of expired
     */
    @Column(name = "date_of_expired")
    private LocalDate dateOfExpired;

    /**
     * Field for storage driver license categories
     */
    @ElementCollection
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 100)
    @Enumerated(EnumType.STRING)
    private List<DriverCategory> categories = new ArrayList<>();

    /**
     * Field for storage driver license owner
     * @see Driver
     */
    @OneToOne(mappedBy = "driversLicense")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Driver owner;

}
