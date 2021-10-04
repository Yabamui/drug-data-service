package co.kr.service.drugdataapi.model.feignclient.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEffectUsageQuantityAreaBody.DrugEffectUsageQuantityAreaBodyBuilder.class)
public class DrugEffectUsageQuantityAreaBody {
    @JsonProperty("pageNo")
    private final Integer pageNo;
    @JsonProperty("totalCount")
    private final Integer totalCount;
    @JsonProperty("numOfRows")
    private final Integer numOfRows;
    @JsonProperty("items")
    private final List<DrugEffectUsageQuantityAreaItem> items;
}
