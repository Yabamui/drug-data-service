package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEffectUsageQuantityDiseaseListItem.DrugEffectUsageQuantityDiseaseListItemBuilder.class)
public class DrugEffectUsageQuantityDiseaseListItem {
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
    @JsonProperty("st3SickSym")
    private final String diseaseCode;
    @JsonProperty("st3SickSymNm")
    private final String diseaseName;
    @JsonProperty("totUseQty")
    private final String totalUseQuantity;
}
