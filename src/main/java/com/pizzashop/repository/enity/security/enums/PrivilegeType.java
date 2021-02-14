package com.pizzashop.repository.enity.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum PrivilegeType {
    USE_CART,
    PAY,
    ADMINISTRATE
}