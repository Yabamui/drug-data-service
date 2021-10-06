package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = UsageQuantityATCStep4DiseaseListItem.UsageQuantityATCStep4DiseaseListItemBuilder.class)
public class UsageQuantityATCStep4DiseaseListItem {
    @JsonProperty("diagYm")
    private final String diagnosisDate;
    @JsonProperty("insupTpCd")
    private final String insurerCode;
    @JsonProperty("msupUseAmt")
    private final String useAmount;
    @JsonProperty("st3SickSym")
    private final String diseaseCode;
    @JsonProperty("st3SickSymNm")
    private final String diseaseName;
    @JsonProperty("totUseQty")
    private final String totalUseQuantity;
    @JsonProperty("atcStep4Cd")
    private final String atcStep4Code;
    @JsonProperty("atcStep4CdNm")
    private final String atcStep4Name;
}
