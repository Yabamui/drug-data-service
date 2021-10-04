package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEssentialItemListResponse.DrugEssentialItemListResponseBuilder.class)
public class DrugEssentialItemListResponse {
    @JsonProperty("header")
    private final GetDrbEasyDrugListHeader header;
    @JsonProperty("body")
    private final DrugEssentialItemListBody body;
}
