package com.pizzashop.repository.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzashop.repository.enity.security.Privilege;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pizzas", schema = "pizzashop")
@SequenceGenerator(name = "sq_pizzas", sequenceName = "sq_pizzas", allocationSize = 1, schema = "pizzashop")
public class Pizza implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pizzas")
    @Column(name = "id")
    Long id;


    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Topping.class)
    @JoinTable(
            schema = "pizzashop",
            name = "pizzas_ingredients",
            joinColumns = @JoinColumn( name = "pizza_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "topping_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("pizzas")
    List<Topping> toppings;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    Customer customer;
}
