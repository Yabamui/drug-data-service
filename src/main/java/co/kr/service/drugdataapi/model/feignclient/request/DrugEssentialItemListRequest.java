package co.kr.service.drugdataapi.model.feignclient.request;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DrugEssentialItemListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @SerializedName("essntl_item_name")
    private final String essentialItemName;
}
