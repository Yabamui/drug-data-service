package co.kr.service.drugdataapi.model.feignclient.response;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GetDrbEasyDrugListHeader {
    @SerializedName("resultCode")
    private final String resultCode;
    @SerializedName("resultMsg")
    private final String resultMsg;
}
