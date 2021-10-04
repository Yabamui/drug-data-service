package co.kr.service.drugdataapi.model.feignclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugPatentInfoListItem.DrugPatentInfoListItemBuilder.class)
public class DrugPatentInfoListItem {
    @JsonProperty("INGR_ENG_NAME")
    private final String ingredientNameEng;
    @JsonProperty("INGR_KOR_NAME")
    private final String ingredientName;
    @JsonProperty("ITEM_NAME_ENG")
    private final String goodsNameEng;
    @JsonProperty("ITEM_NAME_KOR")
    private final String goodsName;
    @JsonProperty("SELLING_CORP")
    private final String sellingCorp;
    @JsonProperty("DOSAGE_FORM")
    private final String dosageForm;
    @JsonProperty("STRENGTH")
    private final String strength;
    @JsonProperty("GROUPING_NO")
    private final String groupingNo;
    @JsonProperty("PMS_EXP_DATE")
    private final String pmsExpiredDate;
    @JsonProperty("KOR_SUIT_YN")
    private final String korSuitYn;
    @JsonProperty("ITEM_SEQ")
    private final String itemSeq;
    @JsonProperty("PAGE_GB_NM")
    private final String pageCategory;
    @JsonProperty("PATENT_GB_CODE")
    private final String patentCategory;
    @JsonProperty("DOMESTIC_INVN_NM")
    private final String domesticInventionName;
    @JsonProperty("PATENTEE")
    private final String patentee;
    @JsonProperty("DOMESTIC_PATENT_NO")
    private final String domesticPatentNo;
    @JsonProperty("DOMESTIC_PATENT_STATUS")
    private final String domesticPatentStatus;
    @JsonProperty("DOMESTIC_END_DATE")
    private final String domesticEndDate;
}
