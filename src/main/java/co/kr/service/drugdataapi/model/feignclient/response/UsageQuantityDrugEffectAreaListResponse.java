package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = UsageQuantityDrugEffectAreaListResponse.UsageQuantityDrugEffectAreaListResponseBuilder.class)
public class UsageQuantityDrugEffectAreaListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final UsageQuantityBody<UsageQuantityDrugEffectAreaListItem> body;
}
