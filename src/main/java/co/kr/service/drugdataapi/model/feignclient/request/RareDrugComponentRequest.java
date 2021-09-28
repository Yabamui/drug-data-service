package co.kr.service.drugdataapi.model.feignclient.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RareDrugComponentRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @SerializedName("RARE_DRUG_NO")
    private final String rareDrugNo;
    @SerializedName("PRDT_NM")
    private final String productName;
    @SerializedName("DRUG_CPNT_KOR_NM")
    private final String drugComponent;
}
