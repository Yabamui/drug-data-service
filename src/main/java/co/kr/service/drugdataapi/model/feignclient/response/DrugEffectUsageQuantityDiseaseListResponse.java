package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEffectUsageQuantityDiseaseListResponse.DrugEffectUsageQuantityDiseaseListResponseBuilder.class)
public class DrugEffectUsageQuantityDiseaseListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final DrugEffectUsageQuantityBody<DrugEffectUsageQuantityDiseaseListItem> body;
}
