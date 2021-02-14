package com.pizzashop.repository.config;

import com.pizzashop.repository.enity.Customer;
import com.pizzashop.repository.enity.security.Privilege;
import com.pizzashop.repository.enity.security.Role;
import com.pizzashop.repository.enity.security.enums.PrivilegeType;
import com.pizzashop.repository.enity.security.enums.RoleType;
import com.pizzashop.repository.repository.CustomerRepo;
import com.pizzashop.repository.repository.PrivilegeRepo;
import com.pizzashop.repository.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PrivilegeRepo privilegeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        List<Privilege> privilegeList = new ArrayList<>();


        for (PrivilegeType s: PrivilegeType.values()) {
            Privilege p = createPrivilegeIfNotFound(s);
            privilegeList.add(p);
        }

        for (RoleType rt: RoleType.values()){
            createRoleIfNotFound(rt,privilegeList);
        }

        createApplicationUser();
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(PrivilegeType pt) {

        Privilege privilege = privilegeRepo.findByType(pt.toString());
        if (privilege == null) {
            privilege = new Privilege(pt);
            privilegeRepo.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(RoleType roleType, List<Privilege> privileges) {
        Role role = roleRepo.findByRoleType(roleType.getRole());
        if (role == null) {
            role = new Role(roleType);
            role.setPrivileges(privileges);
            roleRepo.save(role);
        }
        return role;
    }

    @Transactional
    private void createApplicationUser(){
        Customer customer = customerRepo.findByLogin("eureka");
        if(customer == null) {
            String role =  RoleType.ROLE_APPLICATION.getRole();
            Role appRole = roleRepo.findByRoleType(role);
            customer = new Customer();
            customer.setLogin("eureka");
            customer.setPassword(passwordEncoder.encode("password"));
            customer.setRoles(Arrays.asList(appRole));
            customer.setEnabled(true);
            customerRepo.save(customer);

            alreadySetup = true;
        }
    }
}
