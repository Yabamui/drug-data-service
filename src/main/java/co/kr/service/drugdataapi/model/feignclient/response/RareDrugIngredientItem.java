package co.kr.service.drugdataapi.model.feignclient.response;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RareDrugIngredientItem {
    @SerializedName("RARE_DRUG_NO")
    private final String rareDrugAppointmentNo;
    @SerializedName("MFTR_NM")
    private final String manufactureName;
    @SerializedName("TRGT_DISS_NM")
    private final String targetDisease;
    @SerializedName("PRDT_NM")
    private final String goodsName;
    @SerializedName("MDCT_NM")
    private final String productName;
    @SerializedName("DSGN_YMD")
    private final String appointmentDate;
    @SerializedName("DSGN_RTRCN_YMD")
    private final String appointmentCancelDate;
    @SerializedName("RCPT_PRCS_NO")
    private final String receiptNo;
    @SerializedName("CPNT_SEQ")
    private final String ingredientSeq;
    @SerializedName("CPNT_CD")
    private final String ingredientCode;
    @SerializedName("CPNT_KOR_NM")
    private final String ingredientName;
}
