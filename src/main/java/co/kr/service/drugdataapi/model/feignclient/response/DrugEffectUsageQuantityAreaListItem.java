package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEffectUsageQuantityAreaListItem.DrugEffectUsageQuantityAreaListItemBuilder.class)
public class DrugEffectUsageQuantityAreaListItem {
    @JsonProperty("diagYm")
    private final String diagnosisDate;
    @JsonProperty("insupTpCd")
    private final String insurerCode;
    @JsonProperty("meftDivNo")
    private final String drugEffectNo;
    @JsonProperty("meftDivNoNm")
    private final String drugEffectName;
    @JsonProperty("msupUseAmt")
    private final String useAmount;
    @JsonProperty("sgguCd")
    private final String sigunguCode;
    @JsonProperty("sgguCdNm")
    private final String sigunguName;
    @JsonProperty("sidoCd")
    private final String sidoCode;
    @JsonProperty("sidoCdNm")
    private final String sidoName;
    @JsonProperty("totUseQty")
    private final String totalUseQuantity;
}
