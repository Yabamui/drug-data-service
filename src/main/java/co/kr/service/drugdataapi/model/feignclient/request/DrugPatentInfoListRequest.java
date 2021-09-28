package co.kr.service.drugdataapi.model.feignclient.request;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DrugPatentInfoListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @SerializedName("eng_name")
    private final String rareDrugNo;
    @SerializedName("kor_name")
    private final String productName;
    @SerializedName("DRUG_CPNT_KOR_NM")
    private final String drugComponent;
}
