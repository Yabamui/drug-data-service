package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugPatentInfoListRequest.DrugPatentInfoListRequestBuilder.class)
public class DrugPatentInfoListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("eng_name")
    private final String rareDrugNo;
    @JsonProperty("kor_name")
    private final String productName;
    @JsonProperty("DRUG_CPNT_KOR_NM")
    private final String drugComponent;
}
