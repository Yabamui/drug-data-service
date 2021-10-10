package co.kr.service.drugdataapi.model.feignclient.response;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.util.DigestUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugSupplyLackListItem.DrugSupplyLackListItemBuilder.class)
public class DrugSupplyLackListItem {
    @JsonProperty("REPORT_PGS_CODE")
    private final String reportProgressCode;
    @JsonProperty("SHORT_SUPPLY_REPORT_SEQ")
    private final String shortSupplyReportSeq;
    @JsonProperty("ENTP_SEQ")
    private final String enterpriseSeq;
    @JsonProperty("ENTP_NAME")
    private final String enterpriseName;
    @JsonProperty("ENTP_NO")
    private final String enterpriseNo;
    @JsonProperty("REPORT_ADDR")
    private final String enterpriseAddress;
    @JsonProperty("REPORT_NAME")
    private final String reporter;
    @JsonProperty("REPORT_TEL_NO")
    private final String reporterTelNo;
    @JsonProperty("DEPT_RECEIPT_NO")
    private final String departmentReceiptNo;
    @JsonProperty("ITEM_SEQ")
    private final String itemSeq;
    @JsonProperty("ITEM_NAME")
    private final String itemName;
    @JsonProperty("EDI_CODE")
    private final String ediCode;
    @JsonProperty("SHORT_SUPPLY_EXPT_DATE")
    private final String shortSupplyExpectationDate;
    @JsonProperty("SHORT_SUPPLY_REASON")
    private final String shortSupplyReason;
    @JsonProperty("LAST_SUPPLY_DATE")
    private final String lastSupplyDate;
    @JsonProperty("LAST_SUPPLY_FLAG")
    private final String lastSupplyType;
    @JsonProperty("INV_QTY_DATE")
    private final String inventoryQuantityDate;
    @JsonProperty("INV_QTY")
    private final String inventoryQuantity;
    @JsonProperty("TREATMENT_INFU")
    private final String treatmentImpact;
    @JsonProperty("SUPPLY_PLAN")
    private final String supplyPlan;
    @JsonProperty("SUPPLY_PLAN_DATE")
    private final String supplyPlanDate;
    @JsonProperty("REPORT_DATE")
    private final String reportDate;
    @JsonProperty("EXAM_RESULT_TIME")
    private final String progressDate;
    @JsonProperty("OPEN_AGREE_YN_NM")
    private final String openAgreeValue;

    @Override
    public String toString() {
        return this.reportProgressCode + this.shortSupplyReportSeq + this.enterpriseSeq + this.enterpriseName
                + this.enterpriseNo + this.enterpriseAddress + this.reporter + this.reporterTelNo + this.departmentReceiptNo
                + this.itemSeq + this.itemName + this.ediCode + this.shortSupplyExpectationDate + this.shortSupplyReason
                + this.lastSupplyDate + this.lastSupplyType + this.inventoryQuantityDate + this.inventoryQuantity
                + this.treatmentImpact + this.supplyPlan + this.supplyPlanDate + this.reportDate + this.progressDate + openAgreeValue;
    }

    public String getHashCode() {
        return DigestUtils.md5DigestAsHex(this.toString().getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }
}
