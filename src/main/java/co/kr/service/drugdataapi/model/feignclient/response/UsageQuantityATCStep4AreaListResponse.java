package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = UsageQuantityATCStep4AreaListResponse.UsageQuantityATCStep4AreaListResponseBuilder.class)
public class UsageQuantityATCStep4AreaListResponse {
    @JsonProperty("header")
    private final DrugResponseHeader header;
    @JsonProperty("body")
    private final UsageQuantityBody<UsageQuantityATCStep4AreaListItem> body;
}
