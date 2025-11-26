package com.ecommerce.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non Binary"),
    TRANSGENDER_MALE("Transgender Male"),
    TRANSGENDER_FEMALE("Transgender Female"),
    GENDER_FLUID("Gender Fluid"),
    AGENDER("A Gender"),
    BIGENDER("Bi Gender"),
    TWO_SPIRIT("Two Spirit"),
    OTHER("Other"),
    PREFER_NOT_TO_SAY("Prefers not to disclose gender identity");

    private final String displayName;
}
