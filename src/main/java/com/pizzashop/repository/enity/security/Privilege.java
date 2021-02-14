package com.pizzashop.repository.enity.security;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzashop.repository.enity.security.enums.PrivilegeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "privileges", schema = "pizzashop")
@SequenceGenerator(name = "sq_privileges", sequenceName = "sq_privileges", allocationSize = 1, schema = "pizzashop")
public class Privilege {

    public Privilege(PrivilegeType pt){
        this.privilegeType = pt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_privileges")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PrivilegeType privilegeType;

    @ManyToMany(mappedBy = "privileges", targetEntity = Role.class)
    @JsonIgnoreProperties("privileges")
    private Collection<Role> roles;
}