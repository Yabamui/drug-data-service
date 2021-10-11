package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugStabilityLetterListItem.DrugStabilityLetterListItemBuilder.class)
public class DrugStabilityLetterListItem {
    @JsonProperty("NO")
    private final String no;
    @JsonProperty("SUBJECT")
    private final String subject;
    @JsonProperty("CHARGE_NM")
    private final String writer;
    @JsonProperty("REG_DATE")
    private final String registrationDate;
    @JsonProperty("FILE_URL")
    private final String fileUrl;

    @Override
    public String toString() {
        return this.no+this.subject+this.writer +this.registrationDate +this.fileUrl;
    }

    public String getHashCode() {
        return DigestUtils.sha256Hex(this.toString()).toUpperCase();
    }
}
