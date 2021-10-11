package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugStabilityLetterListRequest.DrugStabilityLetterListRequestBuilder.class)
public class DrugStabilityLetterListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("subject")
    private final String subject;
    @JsonProperty("regDate")
    private final String itemName;
    private final String order;
}
