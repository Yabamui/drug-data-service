package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugProductPermissionListItem.DrugProductPermissionListItemBuilder.class)
public class DrugProductPermissionListItem {
    @JsonProperty("ITEM_SEQ")
    private final String itemSeq;
    @JsonProperty("ITEM_NAME")
    private final String itemName;
    @JsonProperty("ENTP_NAME")
    private final String enterpriseName;
    @JsonProperty("ITEM_PERMIT_DATE")
    private final String itemPermissionDate;
    @JsonProperty("INDUTY")
    private final String industry;
    @JsonProperty("PRDLST_STDR_CODE")
    private final String serialNo;
    @JsonProperty("SPCLTY_PBLC")
    private final String drugType;
    @JsonProperty("PRDUCT_TYPE")
    private final String productType;
    @JsonProperty("PRDUCT_PRMISN_NO")
    private final String permissionNo;
    @JsonProperty("ITEM_INGR_NAME")
    private final String itemIngredientName;
    @JsonProperty("ITEM_INGR_CNT")
    private final String itemIngredientCount;
    @JsonProperty("PERMIT_KIND_CODE")
    private final String permissionType;
    @JsonProperty("CANCEL_DATE")
    private final String cancelDate;
    @JsonProperty("CANCEL_NAME")
    private final String cancelName;
    @JsonProperty("BIG_PRDT_IMG_URL")
    private final String productImageUrl;

    @Override
    public String toString() {
        return this.itemSeq + this.itemName + this.enterpriseName + this.itemPermissionDate + this.industry + this.serialNo
                + this.drugType + this.productType + this.permissionNo + this.itemIngredientName + this.itemIngredientCount
                + this.permissionType + this.cancelDate + this.cancelName + this.productImageUrl;
    }

    public String getHashCode() {
        return DigestUtils.sha256Hex(this.toString()).toUpperCase();
    }
}
