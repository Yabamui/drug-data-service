package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugReEvaluationListItem.DrugReEvaluationListItemBuilder.class)
public class DrugReEvaluationListItem {
    @JsonProperty("ENTP_NAME")
    private final String enterpriseName;
    @JsonProperty("ENTP_NO")
    private final String enterpriseNo;
    @JsonProperty("BOSS_NAME")
    private final String representative;
    @JsonProperty("ITEM_NAME")
    private final String itemName;
    @JsonProperty("ITEM_NO")
    private final String itemNo;
    @JsonProperty("ITEM_PERMIT_DATE")
    private final String itemPermissionDate;
    @JsonProperty("CLASS_NO_NM")
    private final String classificationName;
    @JsonProperty("SUBMIT_DOC_CODE_NM")
    private final String reEvaluationPresentationName;
    @JsonProperty("REEVALT_YEAR")
    private final String reEvaluationYear;
    @JsonProperty("RESULT_DATE")
    private final String resultDate;

    @Override
    public String toString() {
        return this.enterpriseName + this.enterpriseNo + this.representative + this.itemName + this.itemNo + this.itemPermissionDate
                + this.classificationName + this.reEvaluationPresentationName + this.reEvaluationYear + this.resultDate;
    }

    public String getHashCode() {
        return DigestUtils.sha256Hex(this.toString()).toUpperCase();
    }
}
