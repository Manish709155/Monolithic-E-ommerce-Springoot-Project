package com.ecommerce.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Category {

    ELECTRONICS("E", "Electronics"),
    FASHION("F", "Fashion"),
    HOME_APPLIANCES("HA", "Home Appliances"),
    FURNITURE("FU", "Furniture"),
    GROCERY("G", "Grocery"),
    BEAUTY("B", "Beauty & Personal Care"),
    SPORTS("S", "Sports & Fitness"),
    TOYS("T", "Toys & Games"),
    BOOKS("BK", "Books & Stationery"),
    AUTOMOTIVE("A", "Automotive & Accessories"),
    JEWELRY("J", "Jewelry & Watches"),
    HEALTH("H", "Health & Wellness"),
    PET_SUPPLIES("P", "Pet Supplies"),
    OFFICE_SUPPLIES("O", "Office & School Supplies"),
    MUSIC("M", "Music & Instruments"),
    SOFTWARE("SW", "Software & Apps"),
    GARDEN("GD", "Garden & Outdoor"),
    BABY_PRODUCTS("BP", "Baby Products"),
    FOOTWEAR("FW", "Footwear"),
    TRAVEL("TR", "Travel & Luggage");

    private final String value;
    private final String label;

}
