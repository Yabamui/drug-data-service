package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = RareDrugListItem.RareDrugListItemBuilder.class)
public class RareDrugListItem {
    @JsonProperty("RARITY_DRUG_APPOINT_NO")
    private final String rareDrugAppointNo;
    @JsonProperty("MANUFPLACE_NAME")
    private final String manufacturePlaceName;
    @JsonProperty("APPLY_NAME")
    private final String applyName;
    @JsonProperty("APPLY_TEL_NO")
    private final String applyTelNo;
    @JsonProperty("MANUFPLACE_ZIP_NO")
    private final String manufacturePlaceZipNo;
    @JsonProperty("MANUFPLACE_ADDR1")
    private final String manufacturePlaceAdd1;
    @JsonProperty("MANUFPLACE_ADDR2")
    private final String manufacturePlaceAdd2;
    @JsonProperty("MANUFPLACE_TEL_NO")
    private final String manufacturePlaceTelNo;
    @JsonProperty("PRODT_NAME")
    private final String productName;
    @JsonProperty("TARGET_DISEASE")
    private final String targetDisease;
    @JsonProperty("GOODS_NAME")
    private final String goodsName;
    @JsonProperty("MANUF_NAME")
    private final String manufactureName;
    @JsonProperty("APPOINT_DATE")
    private final String appointmentDate;
    @JsonProperty("APPOINT_CANCEL_DATE")
    private final String appointmentCancelDate;
    @JsonProperty("RECEIPT_NO")
    private final String receiptNo;
}
