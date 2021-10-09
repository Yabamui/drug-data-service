package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = RareDrugListRequest.RareDrugListRequestBuilder.class)
public class RareDrugListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("goods_name")
    private final String goodsName;
    @JsonProperty("manuf_name")
    private final String manufactureName;
    @JsonProperty("appoint_date")
    private final String appointmentDate;
}
