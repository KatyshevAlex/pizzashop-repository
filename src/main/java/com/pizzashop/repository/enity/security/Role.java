package com.pizzashop.repository.enity.security;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.pizzashop.repository.enity.Customer;
import com.pizzashop.repository.enity.security.enums.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles", schema = "pizzashop")
@SequenceGenerator(name = "sq_roles", sequenceName = "sq_roles", allocationSize = 1, schema = "pizzashop")
public class Role {

    public Role(RoleType rt){
        this.roleType = rt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_roles")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles", targetEntity = Customer.class)
    @JsonIgnoreProperties("roles")
    private Collection<Customer> customers;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Privilege.class)
    @JoinTable(
            schema = "pizzashop",
            name = "roles_privileges",
            joinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "privilege_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("roles")
    private Collection<Privilege> privileges;
}
