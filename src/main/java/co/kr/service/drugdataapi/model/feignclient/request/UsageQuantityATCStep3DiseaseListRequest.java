package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = UsageQuantityATCStep3DiseaseListRequest.UsageQuantityATCStep3DiseaseListRequestBuilder.class)
public class UsageQuantityATCStep3DiseaseListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    @JsonProperty("diagYm")
    private final String diagnosisYm;
    @JsonProperty("insupTp")
    private final String insurerCode;
    @JsonProperty("cpmdPrscTp")
    private final String providerTypeCode;
    @JsonProperty("atcStep3Cd")
    private final String atcStep3Code;
}
