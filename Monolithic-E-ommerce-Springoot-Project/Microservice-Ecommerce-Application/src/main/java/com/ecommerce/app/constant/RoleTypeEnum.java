package com.ecommerce.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    ADMIN("Admin"),
    ORGANIZATION_EMPLOYEE("Organization Employee"),
    USER("User");

    private final String displayName;


}
