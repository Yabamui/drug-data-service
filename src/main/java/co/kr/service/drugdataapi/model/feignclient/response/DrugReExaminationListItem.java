package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugReExaminationListItem.DrugReExaminationListItemBuilder.class)
public class DrugReExaminationListItem {
    @JsonProperty("ENTP_NAME")
    private final String enterpriseName;
    @JsonProperty("FACTORY_ADDR")
    private final String factoryAddress;
    @JsonProperty("BOSS_NAME")
    private final String representative;
    @JsonProperty("ITEM_NAME")
    private final String itemName;
    @JsonProperty("REPORT_FLAG_NM")
    private final String reportType;
    @JsonProperty("CLASS_NO_NM")
    private final String classificationName;
    @JsonProperty("ITEM_NO")
    private final String itemNo;
    @JsonProperty("REEXAM_START_DATE")
    private final String reExaminationStartDate;
    @JsonProperty("REEXAM_CODE_NM")
    private final String reExaminationCodeName;
    @JsonProperty("YEAR_REPORT_DEADLINE_DATE")
    private final String reportDeadlineDate;
    @JsonProperty("RESULT_DATE")
    private final String resultDate;

    @Override
    public String toString() {
        return this.enterpriseName + this.factoryAddress + this.representative + this.itemName + this.reportType + this.classificationName
                + this.itemNo + this.reExaminationStartDate + this.reExaminationCodeName + this.reportDeadlineDate + this.resultDate;
    }

    public String getHashCode() {
        return DigestUtils.sha256Hex(this.toString()).toUpperCase();
    }
}
