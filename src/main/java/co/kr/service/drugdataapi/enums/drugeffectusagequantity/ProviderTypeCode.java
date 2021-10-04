package co.kr.service.drugdataapi.enums.drugeffectusagequantity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProviderTypeCode {
    CODE_PHARMACY("01"),
    CODE_PRESCRIPTION("02")
    ;

    private final String code;
}
