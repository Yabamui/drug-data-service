package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugProductPermissionListRequest.DrugProductPermissionListRequestBuilder.class)
public class DrugProductPermissionListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    private final String order;
    @JsonProperty("item_name")
    private final String itemName;
    @JsonProperty("entp_name")
    private final String enterpriseName;
    @JsonProperty("induty")
    private final String industry;
    @JsonProperty("prdlst_Stdr_code")
    private final String serialNo;
    @JsonProperty("spclty_pblc")
    private final String drugType;
    @JsonProperty("prduct_prmisn_no")
    private final String permissionNo;
}
