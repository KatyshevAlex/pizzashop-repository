package com.pizzashop.repository.enity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzashop.repository.enity.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "topping", schema = "pizzashop")
@SequenceGenerator(name = "sq_topping", sequenceName = "sq_topping", allocationSize = 1, schema = "pizzashop")
public class Topping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_topping")
    @Column(name = "id")
    Long id;
    String name;
    Double price;
    Integer calories;

    @ManyToMany(mappedBy = "toppings", targetEntity = Pizza.class)
    @JsonIgnoreProperties("toppings")
    private Collection<Pizza> pizzas;
}
