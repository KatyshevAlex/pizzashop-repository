package com.pizzashop.repository.enity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzashop.repository.enity.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customers", schema = "pizzashop")
@SequenceGenerator(name = "sq_customers", sequenceName = "sq_customers", allocationSize = 1, schema = "pizzashop")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_customers")
    @Column(name = "id")
    private Long id;

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private int loginAttempt;
    private boolean tokenExpired;
    private LocalDateTime lastSeen;

    private String payingInfo;
    private Pizza notFinished;


    @OneToMany(mappedBy="customer", fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Pizza> cart;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class)
    @JoinTable(
            schema = "pizzashop",
            name = "customer_roles",
            joinColumns = @JoinColumn( name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("customers")
    private Collection<Role> roles;
}