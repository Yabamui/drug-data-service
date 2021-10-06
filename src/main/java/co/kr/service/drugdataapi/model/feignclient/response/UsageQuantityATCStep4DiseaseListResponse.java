package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = UsageQuantityATCStep4DiseaseListResponse.UsageQuantityATCStep4DiseaseListResponseBuilder.class)
public class UsageQuantityATCStep4DiseaseListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final UsageQuantityBody<UsageQuantityATCStep4DiseaseListItem> body;
}
