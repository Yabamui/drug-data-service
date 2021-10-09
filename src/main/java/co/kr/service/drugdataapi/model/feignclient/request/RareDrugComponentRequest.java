package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = RareDrugComponentRequest.RareDrugComponentRequestBuilder.class)
public class RareDrugComponentRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("RARE_DRUG_NO")
    private final String rareDrugNo;
    @JsonProperty("PRDT_NM")
    private final String productName;
    @JsonProperty("DRUG_CPNT_KOR_NM")
    private final String drugComponent;
}
