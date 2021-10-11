package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugAdministrativeDispositionListItem.DrugAdministrativeDispositionListItemBuilder.class)
public class DrugAdministrativeDispositionListItem {
    @JsonProperty("ENTP_NAME")
    private final String enterpriseName;
    @JsonProperty("ADDR")
    private final String address;
    @JsonProperty("ENTP_NO")
    private final String enterpriseNo;
    @JsonProperty("ITEM_NAME")
    private final String itemName;
    @JsonProperty("BEF_APPLY_LAW")
    private final String violationLaw;
    @JsonProperty("EXPOSE_CONT")
    private final String violationContents;
    @JsonProperty("ADM_DISPS_SEQ")
    private final String administrativeDispositionSeq;
    @JsonProperty("ADM_DISPS_NAME")
    private final String administrativeDispositionName;
    @JsonProperty("LAST_SETTLE_DATE")
    private final String administrativeDispositionDate;
    @JsonProperty("DISPS_TERM_DATE")
    private final String administrativeDispositionPeriod;

    @Override
    public String toString() {
        return this.enterpriseName + this.address + this.enterpriseNo + this.itemName + this.violationLaw + this.violationContents
                + this.administrativeDispositionSeq + this.administrativeDispositionName + this.administrativeDispositionDate
                + this.administrativeDispositionPeriod;
    }

    public String getHashCode() {
        return DigestUtils.sha256Hex(this.toString()).toUpperCase();
    }
}
