package co.kr.service.drugdataapi.enums.drugeffectusagequantity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DrugResponseCode {
    CODE_SUCCESS("00", "정상"),
    CODE_FAIL_REQUEST_EMPTY("9999", "request is empty"),
    CODE_FAIL_RESPONSE_EMPTY("9998", "response is empty"),
    CODE_FAIL_RESPONSE_STATUS_NOT_OK("9997", "response status not ok"),
    CODE_FAIL_RESPONSE_BODY_EMPTY("9996", "response body is empty"),
    CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL("9995", "response body convert fail")
    ;

    private final String code;
    private final String message;
}
