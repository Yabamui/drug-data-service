package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = RareDrugIngredientItem.RareDrugIngredientItemBuilder.class)
public class RareDrugIngredientItem {
    @JsonProperty("RARE_DRUG_NO")
    private final String rareDrugAppointmentNo;
    @JsonProperty("MFTR_NM")
    private final String manufactureName;
    @JsonProperty("TRGT_DISS_NM")
    private final String targetDisease;
    @JsonProperty("PRDT_NM")
    private final String goodsName;
    @JsonProperty("MDCT_NM")
    private final String productName;
    @JsonProperty("DSGN_YMD")
    private final String appointmentDate;
    @JsonProperty("DSGN_RTRCN_YMD")
    private final String appointmentCancelDate;
    @JsonProperty("RCPT_PRCS_NO")
    private final String receiptNo;
    @JsonProperty("CPNT_SEQ")
    private final String ingredientSeq;
    @JsonProperty("CPNT_CD")
    private final String ingredientCode;
    @JsonProperty("CPNT_KOR_NM")
    private final String ingredientName;
}
