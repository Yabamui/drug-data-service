package co.kr.service.drugdataapi.model.feignclient.response;

import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugSupplyLackListResponse.DrugSupplyLackListResponseBuilder.class)
public class DrugSupplyLackListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final DrugResponseBody<DrugSupplyLackListItem> body;

    public static DrugSupplyLackListResponse ofFail(final String resultCode, final String resultMsg) {
        return DrugSupplyLackListResponse.builder()
                .header(DrugResponseHeader.getInstance(resultCode, resultMsg))
                .body(DrugResponseBody.getInstance(0, 0, 0, Collections.emptyList()))
                .build();
    }
}
