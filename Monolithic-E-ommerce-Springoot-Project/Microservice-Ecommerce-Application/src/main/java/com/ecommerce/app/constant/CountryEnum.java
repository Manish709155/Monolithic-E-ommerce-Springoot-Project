package com.ecommerce.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CountryEnum {

    AFGHANISTAN("Afghanistan"), ARMENIA("Armenia"), AZERBAIJAN("Azerbaijan"), BAHRAIN("Bahrain"),
    BANGLADESH("Bangladesh"), BHUTAN("Bhutan"), BRUNEI("Brunei"), CAMBODIA("Cambodia"), CHINA("China"),
    CYPRUS("Cyprus"), GEORGIA("Georgia"), INDIA("India"), INDONESIA("Indonesia"), IRAN("Iran"), IRAQ("Iraq"),
    ISRAEL("Israel"), JAPAN("Japan"), JORDAN("Jordan"), KAZAKHSTAN("Kazakhstan"), KUWAIT("Kuwait"),
    KYRGYZSTAN("Kyrgyzstan"), LAOS("Laos"), LEBANON("Lebanon"), MALAYSIA("Malaysia"), MALDIVES("Maldives"),
    MONGOLIA("Mongolia"), MYANMAR("Myanmar"), NEPAL("Nepal"), NORTH_KOREA("North Korea"), OMAN("Oman"),
    PAKISTAN("Pakistan"), PHILIPPINES("Philippines"), QATAR("Qatar"), SAUDI_ARABIA("Saudi Arabia"),
    SINGAPORE("Singapore"), SOUTH_KOREA("South Korea"), SRI_LANKA("Sri Lanka"),
    STATE_OF_PALESTINE("State of Palestine"), SYRIA("Syria"), TAJIKISTAN("Tajikistan"), THAILAND("Thailand"),
    TIMOR_LESTE("Timor-Leste"), TURKEY("Turkey"), TURKMENISTAN("Turkmenistan"),
    UNITED_ARAB_EMIRATES("United Arab Emirates"), UZBEKISTAN("Uzbekistan"), VIETNAM("Vietnam"), YEMEN("Yemen");

    final String displayName;
}
