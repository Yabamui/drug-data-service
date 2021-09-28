package co.kr.service.drugdataapi.model.feignclient.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RareDrugListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @SerializedName("goods_name")
    private final String goodsName;
    @SerializedName("manuf_name")
    private final String manufactureName;
    @SerializedName("appoint_date")
    private final String appointmentDate;
}
