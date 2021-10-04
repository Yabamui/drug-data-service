package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEffectUsageQuantityInstitutionListResponse.DrugEffectUsageQuantityInstitutionListResponseBuilder.class)
public class DrugEffectUsageQuantityInstitutionListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final DrugEffectUsageQuantityBody<DrugEffectUsageQuantityInstitutionListItem> body;
}
