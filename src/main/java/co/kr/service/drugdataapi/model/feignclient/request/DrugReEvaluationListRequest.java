package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugSupplyLackListRequest.DrugSupplyLackListRequestBuilder.class)
public class DrugReEvaluationListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("entp_name")
    private final String enterpriseName;
    @JsonProperty("entp_no")
    private final String enterpriseNo;
    @JsonProperty("item_name")
    private final String itemName;
    @JsonProperty("item_no")
    private final String itemNo;
}
