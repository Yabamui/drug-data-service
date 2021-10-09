package co.kr.service.drugdataapi.model.feignclient.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEssentialItemListRequest.DrugEssentialItemListRequestBuilder.class)
public class DrugEssentialItemListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @JsonProperty("essntl_item_name")
    private final String essentialItemName;
}
