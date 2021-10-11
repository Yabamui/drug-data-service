package co.kr.service.drugdataapi.model.feignclient.response;

import co.kr.service.drugdataapi.enums.drugeffectusagequantity.DrugResponseCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugResponseHeader.DrugResponseHeaderBuilder.class)
public class DrugResponseHeader {
    @JsonProperty("resultCode")
    private final String resultCode;
    @JsonProperty("resultMsg")
    private final String resultMsg;

    public static DrugResponseHeader getInstance(final String resultCode, final String resultMsg) {
        return DrugResponseHeader.builder().resultCode(resultCode).resultMsg(resultMsg).build();
    }

    public static DrugResponseHeader ofDrugResponse(final DrugResponseCode responseCode) {
        return DrugResponseHeader.builder().resultCode(responseCode.getCode()).resultMsg(responseCode.getMessage()).build();
    }

    public boolean isFail() {
        return !DrugResponseCode.CODE_SUCCESS.getCode().equals(resultCode);
    }
}
