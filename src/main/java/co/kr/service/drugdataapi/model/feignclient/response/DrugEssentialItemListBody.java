package co.kr.service.drugdataapi.model.feignclient.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEssentialItemListBody.DrugEssentialItemListBodyBuilder.class)
public class DrugEssentialItemListBody {
    @JsonProperty("pageNo")
    private final Integer pageNo;
    @JsonProperty("totalCount")
    private final Integer totalCount;
    @JsonProperty("numOfRows")
    private final Integer numOfRows;
    @JsonProperty("items")
    private final List<DrugEssentialItemListItem> items;
}
