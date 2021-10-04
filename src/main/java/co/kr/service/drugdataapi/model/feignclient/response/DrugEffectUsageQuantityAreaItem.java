package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEffectUsageQuantityAreaItem.DrugEffectUsageQuantityAreaItemBuilder.class)
public class DrugEffectUsageQuantityAreaItem {
    @JsonProperty("diagYm")
    private final String diagnosisDate;
    @JsonProperty("insupTpCd")
    private final String insupTpCd;
    @JsonProperty("meftDivNo")
    private final String meftDivNo;
    @JsonProperty("meftDivNoNm")
    private final String meftDivNoNm;
    @JsonProperty("msupUseAmt")
    private final String msupUseAmt;
    @JsonProperty("sgguCd")
    private final String sgguCd;
    @JsonProperty("sgguCdNm")
    private final String sgguCdNm;
    @JsonProperty("sidoCd")
    private final String sidoCd;
    @JsonProperty("sidoCdNm")
    private final String sidoCdNm;
    @JsonProperty("totUseQty")
    private final String totUseQty;
}
