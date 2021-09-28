package co.kr.service.drugdataapi.model.feignclient.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RareDrugIngredientBody {
    @SerializedName("pageNo")
    private final Integer pageNo;
    @SerializedName("totalCount")
    private final Integer totalCount;
    @SerializedName("numOfRows")
    private final Integer numOfRows;
    @SerializedName("items")
    private final List<RareDrugIngredientItem> items;
}
