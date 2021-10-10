package co.kr.service.drugdataapi.model.feignclient.response;

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
}
