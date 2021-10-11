package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugClinicalTrialInfoListItem.DrugClinicalTrialInfoListItemBuilder.class)
public class DrugClinicalTrialInfoListItem {
    @JsonProperty("APPLY_ENTP_NAME")
    private final String applyName;
    @JsonProperty("APPROVAL_TIME")
    private final String approvalTime;
    @JsonProperty("LAB_NAME")
    private final String labName;
    @JsonProperty("GOODS_NAME")
    private final String goodsName;
    @JsonProperty("CLINIC_EXAM_TITLE")
    private final String clinicalTrialTitle;
    @JsonProperty("CLINIC_STEP_NAME")
    private final String clinicalTrialStep;

    @Override
    public String toString() {
        return this.getApplyName() + this.getApprovalTime() + this.getLabName() + this.getGoodsName()
                + this.getClinicalTrialTitle() + this.getClinicalTrialStep();
    }

    public String getHashCode() {
        return DigestUtils.sha256Hex(this.toString()).toUpperCase();
    }
}
