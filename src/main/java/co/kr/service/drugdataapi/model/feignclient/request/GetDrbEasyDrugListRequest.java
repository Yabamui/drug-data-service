package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = GetDrbEasyDrugListRequest.GetDrbEasyDrugListRequestBuilder.class)
public class GetDrbEasyDrugListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("entpName")
    private final String enterpriseName;
    private final String itemName;
    private final String itemSeq;
    @JsonProperty("efcyQesitm")
    private final String efficacy;
    @JsonProperty("useMethodQesitm")
    private final String useMethod;
    @JsonProperty("atpnWarnQesitm")
    private final String attentionPointWarn;
    @JsonProperty("atpnQesitm")
    private final String attentionPoint;
    @JsonProperty("intrcQesitm")
    private final String introduction;
    @JsonProperty("seQesitm")
    private final String sideEffect;
    @JsonProperty("depositMethodQesitm")
    private final String depositMethod;
    @JsonProperty("openDe")
    private final String openDate;
    @JsonProperty("updateDe")
    private final String updateDate;
}
