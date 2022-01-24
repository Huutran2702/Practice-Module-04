package com.codegym.managercity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne(targetEntity = Nation.class)
    private Nation nation;

    @Min(0)
    private BigDecimal area;
    @Min(0)
    private BigDecimal population;
    @Min(0)
    private BigDecimal gdp;

    private String description;

}
