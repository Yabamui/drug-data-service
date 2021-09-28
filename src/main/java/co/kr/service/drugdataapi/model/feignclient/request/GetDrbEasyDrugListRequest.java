package co.kr.service.drugdataapi.model.feignclient.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetDrbEasyDrugListRequest {
    private final String serviceKey;
    private final int pageNo;
    private final int numOfRows;
    private final String type;
    @SerializedName("entpName")
    private final String enterpriseName;
    private final String itemName;
    private final String itemSeq;
    @SerializedName("efcyQesitm")
    private final String efficacy;
    @SerializedName("useMethodQesitm")
    private final String useMethod;
    @SerializedName("atpnWarnQesitm")
    private final String attentionPointWarn;
    @SerializedName("atpnQesitm")
    private final String attentionPoint;
    @SerializedName("intrcQesitm")
    private final String introduction;
    @SerializedName("seQesitm")
    private final String sideEffect;
    @SerializedName("depositMethodQesitm")
    private final String depositMethod;
    @SerializedName("openDe")
    private final String openDate;
    @SerializedName("updateDe")
    private final String updateDate;
}
