package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = RareDrugIngredientResponse.RareDrugIngredientResponseBuilder.class)
public class RareDrugIngredientResponse {
    @JsonProperty("header")
    private final GetDrbEasyDrugListHeader header;
    @JsonProperty("body")
    private final RareDrugIngredientBody body;
}
