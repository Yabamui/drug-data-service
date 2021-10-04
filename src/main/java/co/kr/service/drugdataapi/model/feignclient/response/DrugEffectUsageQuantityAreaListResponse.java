package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEffectUsageQuantityAreaListResponse.DrugEffectUsageQuantityAreaListResponseBuilder.class)
public class DrugEffectUsageQuantityAreaListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final DrugEffectUsageQuantityBody<DrugEffectUsageQuantityAreaListItem> body;
}
