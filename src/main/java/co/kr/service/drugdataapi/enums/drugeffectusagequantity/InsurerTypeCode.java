package co.kr.service.drugdataapi.enums.drugeffectusagequantity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InsurerTypeCode {
    CODE_ALL("0"),
    CODE_HEALTH("4"),
    CODE_MEDICINE("5"),
    CODE_VETERAN("7")
    ;

    private final String code;
}
