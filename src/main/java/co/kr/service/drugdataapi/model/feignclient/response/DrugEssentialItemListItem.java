package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugEssentialItemListItem.DrugEssentialItemListItemBuilder.class)
public class DrugEssentialItemListItem {
    @JsonProperty("ESSNTL_ITEM_NAME")
    private final String essentialItemName;
    @JsonProperty("MED_EFFICACY")
    private final String medicalEfficacy;
    @JsonProperty("APPOINT_DATE")
    private final String appointDate;
}
