package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugClinicalTrialInfoListRequest.DrugClinicalTrialInfoListRequestBuilder.class)
public class DrugClinicalTrialInfoListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("approval_time")
    private final String approvalTime; // format : yyyy-MM-dd or yyyy-MM-dd HH:mm:ss
    @JsonProperty("goods_name")
    private final String goodsName;
}
