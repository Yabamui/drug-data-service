package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = UsageQuantityDrugEffectDiseaseListResponse.UsageQuantityDrugEffectDiseaseListResponseBuilder.class)
public class UsageQuantityDrugEffectDiseaseListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final UsageQuantityBody<UsageQuantityDrugEffectDiseaseListItem> body;
}
