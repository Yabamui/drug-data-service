package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = GetDrbEasyDrugListItem.GetDrbEasyDrugListItemBuilder.class)
public class GetDrbEasyDrugListItem {
    @JsonProperty("entpName")
    private final String enterpriseName;
    @JsonProperty("itemName")
    private final String itemName;
    @JsonProperty("itemSeq")
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
    @JsonProperty("itemImage")
    private final String itemImage;
}
