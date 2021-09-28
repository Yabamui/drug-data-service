package co.kr.service.drugdataapi.model.feignclient.response;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DrugPatentInfoListItem {
    @SerializedName("INGR_ENG_NAME")
    private final String ingredientNameEng;
    @SerializedName("INGR_KOR_NAME")
    private final String ingredientName;
    @SerializedName("ITEM_NAME_ENG")
    private final String goodsNameEng;
    @SerializedName("ITEM_NAME_KOR")
    private final String goodsName;
    @SerializedName("SELLING_CORP")
    private final String sellingCorp;
    @SerializedName("DOSAGE_FORM")
    private final String dosageForm;
    @SerializedName("STRENGTH")
    private final String strength;
    @SerializedName("GROUPING_NO")
    private final String groupingNo;
    @SerializedName("PMS_EXP_DATE")
    private final String pmsExpiredDate;
    @SerializedName("KOR_SUIT_YN")
    private final String korSuitYn;
    @SerializedName("ITEM_SEQ")
    private final String itemSeq;
    @SerializedName("PAGE_GB_NM")
    private final String pageCategory;
    @SerializedName("PATENT_GB_CODE")
    private final String patentCategory;
    @SerializedName("DOMESTIC_INVN_NM")
    private final String domesticInventionName;
    @SerializedName("PATENTEE")
    private final String patentee;
    @SerializedName("DOMESTIC_PATENT_NO")
    private final String domesticPatentNo;
    @SerializedName("DOMESTIC_PATENT_STATUS")
    private final String domesticPatentStatus;
    @SerializedName("DOMESTIC_END_DATE")
    private final String domesticEndDate;
}
