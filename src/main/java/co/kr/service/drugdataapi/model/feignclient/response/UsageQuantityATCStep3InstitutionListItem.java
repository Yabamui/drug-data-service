package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = UsageQuantityATCStep3InstitutionListItem.UsageQuantityATCStep3InstitutionListItemBuilder.class)
public class UsageQuantityATCStep3InstitutionListItem {
    @JsonProperty("diagYm")
    private final String diagnosisDate;
    @JsonProperty("insupTpCd")
    private final String insurerCode;
    @JsonProperty("msupUseAmt")
    private final String useAmount;
    @JsonProperty("sgguCd")
    private final String sigunguCode;
    @JsonProperty("sgguCdNm")
    private final String sigunguName;
    @JsonProperty("sidoCdNm")
    private final String sidoName;
    @JsonProperty("totUseQty")
    private final String totalUseQuantity;
    @JsonProperty("recuClCd")
    private final String institutionCode;
    @JsonProperty("atcStep3Cd")
    private final String atcStep3Code;
    @JsonProperty("atcStep3CdNm")
    private final String atcStep3Name;
}
