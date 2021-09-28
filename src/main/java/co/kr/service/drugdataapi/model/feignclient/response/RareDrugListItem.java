package co.kr.service.drugdataapi.model.feignclient.response;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RareDrugListItem {
    @SerializedName("RARITY_DRUG_APPOINT_NO")
    private final String rareDrugAppointNo;
    @SerializedName("MANUFPLACE_NAME")
    private final String manufacturePlaceName;
    @SerializedName("APPLY_NAME")
    private final String applyName;
    @SerializedName("APPLY_TEL_NO")
    private final String applyTelNo;
    @SerializedName("MANUFPLACE_ZIP_NO")
    private final String manufacturePlaceZipNo;
    @SerializedName("MANUFPLACE_ADDR1")
    private final String manufacturePlaceAdd1;
    @SerializedName("MANUFPLACE_ADDR2")
    private final String manufacturePlaceAdd2;
    @SerializedName("MANUFPLACE_TEL_NO")
    private final String manufacturePlaceTelNo;
    @SerializedName("PRODT_NAME")
    private final String productName;
    @SerializedName("TARGET_DISEASE")
    private final String targetDisease;
    @SerializedName("GOODS_NAME")
    private final String goodsName;
    @SerializedName("MANUF_NAME")
    private final String manufactureName;
    @SerializedName("APPOINT_DATE")
    private final String appointmentDate;
    @SerializedName("APPOINT_CANCEL_DATE")
    private final String appointmentCancelDate;
    @SerializedName("RECEIPT_NO")
    private final String receiptNo;
}
