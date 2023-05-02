package com.example.taylor.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Codepromo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcodepromo;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    //public boolean isActive() {
        //LocalDate currentDate = LocalDate.now();
        //return startDate.compareTo(currentDate) <= 0 && endDate.compareTo(currentDate) >= 0;
    //}
}
