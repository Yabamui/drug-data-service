package co.kr.service.drugdataapi.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.kr.service.drugdataapi.enums.drugeffectusagequantity.ProviderTypeCode;
import co.kr.service.drugdataapi.enums.drugeffectusagequantity.InsurerTypeCode;
import co.kr.service.drugdataapi.feignclient.MedicalDrugClient;
import co.kr.service.drugdataapi.model.customproperties.PublicDataApiProperties;
import co.kr.service.drugdataapi.model.feignclient.request.*;
import co.kr.service.drugdataapi.model.feignclient.response.*;
import co.kr.service.drugdataapi.shema.entity.*;
import co.kr.service.drugdataapi.shema.repository.*;
import co.kr.service.drugdataapi.util.JsonConvert;
import co.kr.service.drugdataapi.util.XmlConvert;
import com.fasterxml.jackson.core.type.TypeReference;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class MedicalDrugServiceTest {
    @Autowired
    private MedicalDrugClient medicalDrugClient;
    @Autowired
    private PublicDataApiProperties publicDataApiProperties;

    @Autowired
    private DrugOverviewRepository drugOverviewRepository;
    @Autowired
    private RareDrugOverviewRepository rareDrugOverviewRepository;
    @Autowired
    private RareDrugIngredientRepository rareDrugIngredientRepository;
    @Autowired
    private DrugPatentInfoRepository drugPatentInfoRepository;
    @Autowired
    private DrugEssentialItemRepository drugEssentialItemRepository;

    @Test
    void getDrbEasyDrugListTest() {
        final Map<String, String> request = JsonConvert.toMap(GetDrbEasyDrugListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .type("json")
                .build());

        final Response response = medicalDrugClient.getDrbEasyDrugList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final GetDrbEasyDrugListResponse responseData = this.getResponseDto(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");

        final List<DrugOverview> drugOverviews = responseData.getBody().getItems().stream()
                .map(item -> DrugOverview.builder()
                        .itemSeq(item.getItemSeq())
                        .enterpriseName(item.getEnterpriseName())
                        .itemName(item.getItemName())
                        .efficacy(item.getEfficacy())
                        .useMethod(item.getUseMethod())
                        .attentionPointWarn(item.getAttentionPointWarn())
                        .attentionPoint(item.getAttentionPoint())
                        .introduction(item.getIntroduction())
                        .sideEffect(item.getSideEffect())
                        .depositMethod(item.getDepositMethod())
                        .openDate(item.getOpenDate())
                        .updateDate(item.getUpdateDate())
                        .itemImage(item.getItemImage())
                        .build())
                .collect(Collectors.toList());

        assertThat(drugOverviews).isNotEmpty();

        this.drugOverviewRepository.saveAll(drugOverviews);
    }

    @Test
    void getRareDrugList() {
        final Map<String, String> request = JsonConvert.toMap(RareDrugListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(5)
                .numOfRows(100)
                .type("json")
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getRareDrugList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final RareDrugListResponse responseData = this.getResponseDto(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();

        log.info(String.valueOf(responseData.getBody().getItems().stream().filter(f -> StringUtils.isNotEmpty(f.getGoodsName())).flatMapToInt(item -> IntStream.of(item.getGoodsName().length())).max()));

        final List<RareDrugOverview> rareDrugOverviews = responseData.getBody().getItems().stream()
                .map(item -> RareDrugOverview.builder()
                        .rareDrugAppointmentNo(item.getRareDrugAppointNo())
                        .goodsName(item.getGoodsName())
                        .productName(item.getProductName())
                        .targetDisease(item.getTargetDisease())
                        .applyName(item.getApplyName())
                        .applyTelNo(item.getApplyTelNo())
                        .manufactureName(item.getManufactureName())
                        .manufacturePlaceName(item.getManufacturePlaceName())
                        .manufacturePlaceZipNo(item.getManufacturePlaceZipNo())
                        .manufacturePlaceAdd1(item.getManufacturePlaceAdd1())
                        .manufacturePlaceAdd2(item.getManufacturePlaceAdd2())
                        .manufacturePlaceTelNo(item.getManufacturePlaceTelNo())
                        .appointmentDate(item.getAppointmentDate())
                        .appointmentCancelDate(item.getAppointmentCancelDate())
                        .receiptNo(item.getReceiptNo())
                        .build())
                .collect(Collectors.toList());

        assertThat(rareDrugOverviews).isNotEmpty();

        this.rareDrugOverviewRepository.saveAll(rareDrugOverviews);
    }

    /**
     * rareDrugAppointmentNo 별 componentSeq 가 존재
     */
    @Test
    void getRareDrugComponentTest() {
        final Map<String, String> request = JsonConvert.toMap(RareDrugComponentRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(3)
                .numOfRows(100)
                .type("json")
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getRareDrugIngredient(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final RareDrugIngredientResponse responseData = this.getResponseDto(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();

        final List<RareDrugIngredient> rareDrugComponents = this.rareDrugIngredientRepository.findAll();

        if (CollectionUtils.isEmpty(rareDrugComponents)) {
            this.rareDrugIngredientRepository.saveAll(responseData.getBody().getItems().stream()
                    .map(item -> RareDrugIngredient.builder()
                            .rareDrugAppointmentNo(item.getRareDrugAppointmentNo())
                            .manufactureName(item.getManufactureName())
                            .goodsName(item.getGoodsName())
                            .productName(item.getProductName())
                            .targetDisease(item.getTargetDisease())
                            .appointmentDate(item.getAppointmentDate())
                            .appointmentCancelDate(item.getAppointmentCancelDate())
                            .receiptNo(item.getReceiptNo())
                            .ingredientSeq(item.getIngredientSeq())
                            .ingredientCode(item.getIngredientCode())
                            .ingredientName(item.getIngredientName())
                            .build())
                    .collect(Collectors.toList()));

            return;
        }

        this.rareDrugIngredientRepository.saveAll(responseData.getBody().getItems().stream()
                .map(item -> {
                    final RareDrugIngredient rareDrugComponent = rareDrugComponents.stream()
                            .filter(f -> item.getRareDrugAppointmentNo().equals(f.getRareDrugAppointmentNo()))
                            .filter(f -> item.getIngredientSeq().equals(f.getIngredientSeq()))
                            .findFirst().orElse(null);

                    if (Objects.isNull(rareDrugComponent)) {
                        return RareDrugIngredient.builder()
                                .rareDrugAppointmentNo(item.getRareDrugAppointmentNo())
                                .manufactureName(item.getManufactureName())
                                .goodsName(item.getGoodsName())
                                .productName(item.getProductName())
                                .targetDisease(item.getTargetDisease())
                                .appointmentDate(item.getAppointmentDate())
                                .appointmentCancelDate(item.getAppointmentCancelDate())
                                .receiptNo(item.getReceiptNo())
                                .ingredientSeq(item.getIngredientSeq())
                                .ingredientCode(item.getIngredientCode())
                                .ingredientName(item.getIngredientName())
                                .build();
                    }

                    rareDrugComponent.updateByApi(item.getManufactureName(), item.getGoodsName(), item.getProductName(),
                            item.getTargetDisease(), item.getAppointmentDate(), item.getAppointmentCancelDate(),
                            item.getReceiptNo(), item.getIngredientCode(), item.getIngredientName());

                    return rareDrugComponent;
                })
                .collect(Collectors.toList()));
    }

    @Test
    void getDrugPatentInfoListTest() {
        int page = 776;
        final int maxRaw = 77600;

        final StringBuilder builder = new StringBuilder();

        while (page * 100 <= maxRaw) {
            final String response = this.saveDrugPatentInfo(page);

            if (StringUtils.isEmpty(response)) {
                break;
            }

            builder.append(response).append("\n");
            page++;
        }

        log.info(builder.toString());
    }

    private String saveDrugPatentInfo(final int page) {
        final Map<String, String> request = JsonConvert.toMap(DrugPatentInfoListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(page)
                .numOfRows(100)
                .type("json")
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugPatentInfoList(request);

        if (Objects.isNull(response)) {
            return "page : " + page + " / response is empty";
        }

        final String responseBody = this.getBodyString(response);

        if (StringUtils.isEmpty(responseBody)) {
            return "page : " + page + " / responseBody is empty";
        }

        final DrugPatentInfoListResponse responseData = this.getResponseDto(responseBody, new TypeReference<>() {
        });

        if (Objects.isNull(responseData)) {
            return "page : " + page + " / responseData is empty";
        }

        if (!responseData.getHeader().getResultCode().equals("00")) {
            return "page : " + page + " / result code is " + responseData.getHeader().getResultCode();
        }

        if (CollectionUtils.isEmpty(responseData.getBody().getItems())) {
            return "";
        }

        final List<DrugPatentInfo> drugPatentInfos = responseData.getBody().getItems().stream()
                .map(item -> DrugPatentInfo.builder()
                        .ingredientName(item.getIngredientName())
                        .ingredientNameEng(item.getIngredientNameEng())
                        .goodsName(item.getGoodsName())
                        .goodsNameEng(item.getGoodsNameEng())
                        .sellingCorp(item.getSellingCorp())
                        .dosageForm(item.getDosageForm())
                        .strength(item.getStrength())
                        .groupingNo(item.getGroupingNo())
                        .pmsExpiredDate(item.getPmsExpiredDate())
                        .korSuitYn(item.getKorSuitYn())
                        .itemSeq(item.getItemSeq())
                        .pageCategory(item.getPageCategory())
                        .patentCategory(item.getPatentCategory())
                        .domesticInventionName(item.getDomesticInventionName())
                        .patentee(item.getPatentee())
                        .domesticPatentNo(item.getDomesticPatentNo())
                        .domesticPatentStatus(item.getDomesticPatentStatus())
                        .domesticEndDate(item.getDomesticEndDate())
                        .build())
                .collect(Collectors.toList());

        this.drugPatentInfoRepository.saveAll(drugPatentInfos);

        return "page : " + page + " / success";
    }

    @Test
    void getDrugEssentialItemListTest() {
        final Map<String, String> request = JsonConvert.toMap(DrugEssentialItemListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(1)
                .type("json")
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugEssentialItemList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugEssentialItemListResponse responseData = this.getResponseDto(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();

        final List<DrugEssentialItem> drugEssentialItems = this.drugEssentialItemRepository.findAll();

        if (CollectionUtils.isEmpty(drugEssentialItems)) {
            this.drugEssentialItemRepository.saveAll(responseData.getBody().getItems().stream()
                    .map(item -> DrugEssentialItem.builder()
                            .essentialItemName(item.getEssentialItemName())
                            .medicalEfficacy(item.getMedicalEfficacy())
                            .appointDate(item.getAppointDate())
                            .build())
                    .collect(Collectors.toList()));
            return;
        }

        for (DrugEssentialItemListItem apiResponse : responseData.getBody().getItems()) {
            final DrugEssentialItem drugEssentialItem = drugEssentialItems.stream()
                    .filter(f -> apiResponse.getEssentialItemName().equals(f.getEssentialItemName()))
                    .findFirst().orElse(null);

            if (Objects.isNull(drugEssentialItem)) {
                drugEssentialItems.add(DrugEssentialItem.builder()
                        .essentialItemName(apiResponse.getEssentialItemName())
                        .medicalEfficacy(apiResponse.getMedicalEfficacy())
                        .appointDate(apiResponse.getAppointDate())
                        .build());

                return;
            }

            drugEssentialItem.updateByApiResponse(apiResponse.getMedicalEfficacy(), apiResponse.getAppointDate());
        }

        this.drugEssentialItemRepository.saveAll(drugEssentialItems);
    }

    @Test
    void getDrugEffectUsageQuantityAreaListTest() {
        final String drugEffectNo = "111";

        final Map<String, String> request = JsonConvert.toMap(DrugEffectUsageQuantityAreaListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("202101")
                .drugEffectNo(drugEffectNo)
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .sidoCode("110000")
                .sigunguCode("110023")
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugEffectUsageQuantityAreaList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugEffectUsageQuantityAreaListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void get() {
        final String drugEffectNo = "111";
        final String institutionCode = "1";

        final Map<String, String> request = JsonConvert.toMap(DrugEffectUsageQuantityInstitutionListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .institutionCode(institutionCode)
                .diagnosisYm("201608")
                .drugEffectNo(drugEffectNo)
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .sidoCode("110000")
                .sigunguCode("110023")
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugEffectUsageQuantityInstitutionList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugEffectUsageQuantityInstitutionListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }


    private String getBodyString(final Response response) {
        try {
            return Util.toString(response.body().asReader(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    private <T> T getResponseDto(final String responseBody, final TypeReference<T> reference) {
        return JsonConvert.toObject(responseBody, reference);
    }

    @Test
    void convertJsonStringToObjectTest() {
        final String json = "{\"header\":{\"resultCode\":\"00\",\"resultMsg\":\"NORMAL SERVICE.\"},\"body\":{\"pageNo\":1,\"totalCount\":506,\"numOfRows\":1,\"items\":[{\"ESSNTL_ITEM_NAME\":\"페노바르비탈 정제\",\"MED_EFFICACY\":\"간질\",\"APPOINT_DATE\":\"20170630\"}]}}";

        final DrugEssentialItemListResponse response = JsonConvert.toObject(json, new TypeReference<>() {
        });

        assertThat(response).isNotNull();

        log.info(JsonConvert.toString(response));
    }

    @Test
    void convertXmlStringToObjectTest() {
        final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<response>\n" +
                "    <header>\n" +
                "        <resultCode>00</resultCode>\n" +
                "        <resultMsg>NORMAL SERVICE.</resultMsg>\n" +
                "    </header>\n" +
                "    <body>\n" +
                "        <items>\n" +
                "            <item>\n" +
                "                <diagYm>201604</diagYm>\n" +
                "                <insupTpCd>5</insupTpCd>\n" +
                "                <meftDivNo>111</meftDivNo>\n" +
                "                <meftDivNoNm>전신마취제</meftDivNoNm>\n" +
                "                <msupUseAmt>1048724</msupUseAmt>\n" +
                "                <sgguCd>110023</sgguCd>\n" +
                "                <sgguCdNm>광진구</sgguCdNm>\n" +
                "                <sidoCd>110000</sidoCd>\n" +
                "                <sidoCdNm>서울</sidoCdNm>\n" +
                "                <totUseQty>661</totUseQty>\n" +
                "            </item>\n" +
                "            <item>\n" +
                "                <diagYm>201604</diagYm>\n" +
                "                <insupTpCd>4</insupTpCd>\n" +
                "                <meftDivNo>111</meftDivNo>\n" +
                "                <meftDivNoNm>전신마취제</meftDivNoNm>\n" +
                "                <msupUseAmt>21652632</msupUseAmt>\n" +
                "                <sgguCd>110023</sgguCd>\n" +
                "                <sgguCdNm>광진구</sgguCdNm>\n" +
                "                <sidoCd>110000</sidoCd>\n" +
                "                <sidoCdNm>서울</sidoCdNm>\n" +
                "                <totUseQty>12886</totUseQty>\n" +
                "            </item>\n" +
                "        </items>\n" +
                "        <numOfRows>100</numOfRows>\n" +
                "        <pageNo>1</pageNo>\n" +
                "        <totalCount>2</totalCount>\n" +
                "    </body>\n" +
                "</response>";

        final DrugEffectUsageQuantityAreaListResponse response = XmlConvert.toObject(xml, new TypeReference<>() {
        });

        assertThat(response).isNotNull();

        log.info(JsonConvert.toString(response));
    }
}
