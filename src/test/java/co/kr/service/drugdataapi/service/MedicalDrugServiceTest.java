package co.kr.service.drugdataapi.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.kr.service.drugdataapi.enums.drugeffectusagequantity.DrugResponseCode;
import co.kr.service.drugdataapi.enums.drugeffectusagequantity.InsurerTypeCode;
import co.kr.service.drugdataapi.enums.drugeffectusagequantity.ProviderTypeCode;
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
    @Autowired
    private DrugClinicalTrialInfoRepository drugClinicalTrialInfoRepository;
    @Autowired
    private DrugSupplyLackInfoRepository drugSupplyLackInfoRepository;
    @Autowired
    private DrugReEvaluationInfoRepository drugReEvaluationInfoRepository;
    @Autowired
    private DrugReExaminationInfoRepository drugReExaminationInfoRepository;
    @Autowired
    private DrugAdministrativeDispositionInfoRepository drugAdministrativeDispositionInfoRepository;
    @Autowired
    private DrugStabilityLetterInfoRepository drugStabilityLetterInfoRepository;
    @Autowired
    private DrugProductPermissionInfoRepository drugProductPermissionInfoRepository;

    private static final String RESPONSE_TYPE = "json";

    @Test
    void getDrbEasyDrugListTest() {
        final Map<String, String> request = JsonConvert.toMap(GetDrbEasyDrugListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .type(RESPONSE_TYPE)
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
                .type(RESPONSE_TYPE)
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
                .type(RESPONSE_TYPE)
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
                .type(RESPONSE_TYPE)
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
                .type(RESPONSE_TYPE)
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
    void getUsageQuantityDrugEffectAreaListTest() {
        final String drugEffectNo = "111";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityDrugEffectAreaListRequest.builder()
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

        final Response response = this.medicalDrugClient.getUsageQuantityDrugEffectAreaList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityDrugEffectAreaListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getUsageQuantityDrugEffectInstitutionListTest() {
        final String drugEffectNo = "111";
        final String institutionCode = "1";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityDrugEffectInstitutionListRequest.builder()
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

        final Response response = this.medicalDrugClient.getUsageQuantityDrugEffectInstitutionList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityDrugEffectInstitutionListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getUsageQuantityDrugEffectDiseaseListTest() {
        final String drugEffectNo = "111";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityDrugEffectDiseaseListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("201608")
                .drugEffectNo(drugEffectNo)
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getUsageQuantityDrugEffectDiseaseList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityDrugEffectDiseaseListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getUsageQuantityATCStep3AreaListTest() {
        final String atcStep3Code = "A01A";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityATCStep3AreaListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("202001")
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .sidoCode("110000")
                .sigunguCode("110001")
                .atcStep3Code(atcStep3Code)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getUsageQuantityATCStep3AreaList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityATCStep3AreaListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getUsageQuantityATCStep3InstitutionListTest() {
        final String atcStep3Code = "A01A";
        final String institutionCode = "01";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityATCStep3InstitutionListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("202001")
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .sidoCode("110000")
                .sigunguCode("110001")
                .atcStep3Code(atcStep3Code)
                .institutionCode(institutionCode)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getUsageQuantityATCStep3InstitutionList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityATCStep3InstitutionListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getUsageQuantityATCStep3DiseaseListTest() {
        final String atcStep3Code = "A01A";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityATCStep3DiseaseListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("202001")
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .atcStep3Code(atcStep3Code)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getUsageQuantityATCStep3DiseaseList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityATCStep3DiseaseListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getUsageQuantityATCStep4AreaListTest() {
        final String atcStep4Code = "A01AC";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityATCStep4AreaListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("202001")
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .sidoCode("110000")
                .sigunguCode("110001")
                .atcStep4Code(atcStep4Code)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getUsageQuantityATCStep4AreaList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityATCStep4AreaListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");

        if (CollectionUtils.isEmpty(responseData.getBody().getItems())) {
            log.info("response item is empty");
        }
    }

    @Test
    void getUsageQuantityATCStep4InstitutionListTest() {
        final String atcStep4Code = "B01AC";
        final String institutionCode = "01";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityATCStep4InstitutionListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("202001")
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .sidoCode("110000")
                .sigunguCode("110001")
                .atcStep4Code(atcStep4Code)
                .institutionCode(institutionCode)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getUsageQuantityATCStep4InstitutionList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityATCStep4InstitutionListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getUsageQuantityATCStep4DiseaseListTest() {
        final String atcStep4Code = "B01AC";

        final Map<String, String> request = JsonConvert.toMap(UsageQuantityATCStep4DiseaseListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .diagnosisYm("202001")
                .insurerCode(InsurerTypeCode.CODE_ALL.getCode())
                .providerTypeCode(ProviderTypeCode.CODE_PHARMACY.getCode())
                .atcStep4Code(atcStep4Code)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getUsageQuantityATCStep4DiseaseList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final UsageQuantityATCStep4DiseaseListResponse responseData = XmlConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void getDrugClinicalTrialInfoListClientTest() {
        final Map<String, String> request = JsonConvert.toMap(DrugClinicalTrialInfoListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(1)
                .numOfRows(100)
                .type(RESPONSE_TYPE)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugClinicalTrialInfoList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugClinicalTrialInfoListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void createDrugClinicalTrialInfoTest() {
        int pageNo = 1;
        final int numOfRows = 100;
        int totalPage = 0;

        do {
            log.info("pageNo : " + pageNo + " / totalPage : " + totalPage);
            final Map<String, String> request = JsonConvert.toMap(DrugClinicalTrialInfoListRequest.builder()
                    .serviceKey(publicDataApiProperties.getEncodeKey())
                    .pageNo(pageNo)
                    .numOfRows(numOfRows)
                    .type(RESPONSE_TYPE)
                    .build());

            assertThat(request).isNotEmpty();

            final Response response = this.medicalDrugClient.getDrugClinicalTrialInfoList(request);

            if (Objects.isNull(response)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getMessage());
                break;
            }

            if (response.status() != HttpStatus.OK.value()) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getMessage());
                break;
            }

            final String responseBody = this.getBodyString(response);

            if (StringUtils.isBlank(responseBody)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getMessage());
                break;
            }

            final DrugClinicalTrialInfoListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
            });

            if (Objects.isNull(responseData) || Objects.isNull(responseData.getHeader()) || Objects.isNull(responseData.getBody())) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getCode() + " / "
                        + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getMessage());
                break;
            }

            if (responseData.getHeader().isFail()) {
                log.error("response code : " + responseData.getHeader().getResultCode() + " / message : " + responseData.getHeader().getResultMsg());
                return;
            }

            if (responseData.getBody().isItemEmpty()) {
                log.info("response item or total-count is empty");
                return;
            }

            if (totalPage == 0) {
                totalPage = this.getTotalPage(responseData.getBody().getTotalCount(), numOfRows);
            }

            // entity & repository 생성 및 저장
            final List<DrugClinicalTrialInfo> drugClinicalTrialInfos = responseData.getBody().getItems().stream()
                    .map(item -> DrugClinicalTrialInfo.builder()
                            .hashCode(item.getHashCode())
                            .applyName(item.getApplyName())
                            .approvalTime(item.getApprovalTime())
                            .labName(item.getLabName())
                            .goodsName(item.getGoodsName())
                            .clinicalTrialTitle(item.getClinicalTrialTitle())
                            .clinicalTrialStep(item.getClinicalTrialStep())
                            .build())
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(drugClinicalTrialInfos)) {
                log.error("entity generate is fail");
            }

            this.drugClinicalTrialInfoRepository.saveAll(drugClinicalTrialInfos);
            pageNo ++;
        } while (pageNo <= totalPage);
    }

    @Test
    void getDrugSupplyLackListClientTest() {
        int pageNo = 1;
        final int numOfRows = 1;

        final Map<String, String> request = JsonConvert.toMap(DrugSupplyLackListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(pageNo)
                .numOfRows(numOfRows)
                .type(RESPONSE_TYPE)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugSupplyLackList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugSupplyLackListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void createDrugSupplyLackInfoTest() {
        int pageNo = 1;
        final int numOfRows = 100;
        int totalPage = 0;

        do {
            log.info("pageNo : " + pageNo + " / totalPage : " + totalPage);
            final Map<String, String> request = JsonConvert.toMap(DrugSupplyLackListRequest.builder()
                    .serviceKey(publicDataApiProperties.getEncodeKey())
                    .pageNo(pageNo)
                    .numOfRows(numOfRows)
                    .type(RESPONSE_TYPE)
                    .build());

            assertThat(request).isNotEmpty();

            final Response response = this.medicalDrugClient.getDrugSupplyLackList(request);

            if (Objects.isNull(response)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getMessage());
                break;
            }

            if (response.status() != HttpStatus.OK.value()) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getMessage());
                break;
            }

            final String responseBody = this.getBodyString(response);

            if (StringUtils.isBlank(responseBody)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getMessage());
                break;
            }

            final DrugSupplyLackListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
            });

            if (Objects.isNull(responseData) || Objects.isNull(responseData.getHeader()) || Objects.isNull(responseData.getBody())) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getCode() + " / "
                        + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getMessage());
                break;
            }

            if (responseData.getHeader().isFail()) {
                log.error("response code : " + responseData.getHeader().getResultCode() + " / message : " + responseData.getHeader().getResultMsg());
                return;
            }

            if (responseData.getBody().isItemEmpty()) {
                log.info("response item or total-count is empty");
                return;
            }

            if (totalPage == 0) {
                totalPage = this.getTotalPage(responseData.getBody().getTotalCount(), numOfRows);
            }

            // entity & repository 생성 및 저장
            final List<DrugSupplyLackInfo> drugSupplyLackInfos = responseData.getBody().getItems().stream()
                    .map(item -> DrugSupplyLackInfo.builder()
                            .hashCode(item.getHashCode())
                            .reportProgressCode(item.getReportProgressCode())
                            .shortSupplyReportSeq(item.getShortSupplyReportSeq())
                            .enterpriseSeq(item.getEnterpriseSeq())
                            .enterpriseName(item.getEnterpriseName())
                            .enterpriseNo(item.getEnterpriseNo())
                            .enterpriseAddress(item.getEnterpriseAddress())
                            .reporter(item.getReporter())
                            .reporterTelNo(item.getReporterTelNo())
                            .departmentReceiptNo(item.getDepartmentReceiptNo())
                            .itemSeq(item.getItemSeq())
                            .itemName(item.getItemName())
                            .ediCode(item.getEdiCode())
                            .shortSupplyExpectationDate(item.getShortSupplyExpectationDate())
                            .shortSupplyReason(item.getShortSupplyReason())
                            .lastSupplyDate(item.getLastSupplyDate())
                            .lastSupplyType(item.getLastSupplyType())
                            .inventoryQuantityDate(item.getInventoryQuantityDate())
                            .inventoryQuantity(item.getInventoryQuantity())
                            .treatmentImpact(item.getTreatmentImpact())
                            .supplyPlan(item.getSupplyPlan())
                            .supplyPlanDate(item.getSupplyPlanDate())
                            .reportDate(item.getReportDate())
                            .resultDate(item.getResultDate())
                            .openAgreeValue(item.getOpenAgreeValue())
                            .build())
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(drugSupplyLackInfos)) {
                log.error("entity generate is fail");
                return;
            }

            this.drugSupplyLackInfoRepository.saveAll(drugSupplyLackInfos);
            pageNo ++;
        } while (pageNo <= totalPage);
    }

    @Test
    void getDrugReEvaluationListClientTest() {
        final int pageNo = 1;
        final int numOfRows = 1;

        final Map<String, String> request = JsonConvert.toMap(DrugReEvaluationListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(pageNo)
                .numOfRows(numOfRows)
                .type(RESPONSE_TYPE)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugReEvaluationList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugReEvaluationListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void createDrugReEvaluationInfoTest() {
        int pageNo = 1;
        final int numOfRows = 100;
        int totalPage = 0;

        do {
            log.info("pageNo : " + pageNo + " / totalPage : " + totalPage);
            final Map<String, String> request = JsonConvert.toMap(DrugReEvaluationListRequest.builder()
                    .serviceKey(publicDataApiProperties.getEncodeKey())
                    .pageNo(pageNo)
                    .numOfRows(numOfRows)
                    .type(RESPONSE_TYPE)
                    .build());

            assertThat(request).isNotEmpty();

            final Response response = this.medicalDrugClient.getDrugReEvaluationList(request);

            if (Objects.isNull(response)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getMessage());
                break;
            }

            if (response.status() != HttpStatus.OK.value()) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getMessage());
                break;
            }

            final String responseBody = this.getBodyString(response);

            if (StringUtils.isBlank(responseBody)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getMessage());
                break;
            }

            final DrugReEvaluationListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
            });

            if (Objects.isNull(responseData) || Objects.isNull(responseData.getHeader()) || Objects.isNull(responseData.getBody())) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getCode() + " / "
                        + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getMessage());
                break;
            }

            if (responseData.getHeader().isFail()) {
                log.error("response code : " + responseData.getHeader().getResultCode() + " / message : " + responseData.getHeader().getResultMsg());
                return;
            }

            if (responseData.getBody().isItemEmpty()) {
                log.info("response item or total-count is empty");
                return;
            }

            if (totalPage == 0) {
                totalPage = this.getTotalPage(responseData.getBody().getTotalCount(), numOfRows);
            }

            final List<DrugReEvaluationInfo> drugReEvaluationInfos = responseData.getBody().getItems().stream()
                    .map(item -> DrugReEvaluationInfo.builder()
                            .hashCode(item.getHashCode())
                            .enterpriseName(item.getEnterpriseName())
                            .enterpriseNo(item.getEnterpriseNo())
                            .representative(item.getRepresentative())
                            .itemName(item.getItemName())
                            .itemNo(item.getItemNo())
                            .itemPermissionDate(item.getItemPermissionDate())
                            .classificationName(item.getClassificationName())
                            .reEvaluationPresentationName(item.getReEvaluationPresentationName())
                            .reEvaluationYear(item.getReEvaluationYear())
                            .resultDate(item.getResultDate())
                            .build())
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(drugReEvaluationInfos)) {
                log.error("entity generate is fail");
            }

            this.drugReEvaluationInfoRepository.saveAll(drugReEvaluationInfos);
            pageNo ++;
        } while (pageNo <= totalPage);
    }

    @Test
    void getDrugReExaminationListClientTest() {
        final int pageNo = 1;
        final int numOfRows = 1;

        final Map<String, String> request = JsonConvert.toMap(DrugReExaminationListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(pageNo)
                .numOfRows(numOfRows)
                .type(RESPONSE_TYPE)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugReExaminationList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugReExaminationListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void createDrugReExaminationInfoTest() {
        int pageNo = 1;
        final int numOfRows = 100;
        int totalPage = 0;

        do {
            log.info("pageNo : " + pageNo + " / totalPage : " + totalPage);
            final Map<String, String> request = JsonConvert.toMap(DrugReExaminationListRequest.builder()
                    .serviceKey(publicDataApiProperties.getEncodeKey())
                    .pageNo(pageNo)
                    .numOfRows(numOfRows)
                    .type(RESPONSE_TYPE)
                    .build());

            assertThat(request).isNotEmpty();

            final Response response = this.medicalDrugClient.getDrugReExaminationList(request);

            if (Objects.isNull(response)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getMessage());
                break;
            }

            if (response.status() != HttpStatus.OK.value()) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getMessage());
                break;
            }

            final String responseBody = this.getBodyString(response);

            if (StringUtils.isBlank(responseBody)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getMessage());
                break;
            }

            final DrugReExaminationListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
            });

            if (Objects.isNull(responseData) || Objects.isNull(responseData.getHeader()) || Objects.isNull(responseData.getBody())) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getCode() + " / "
                        + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getMessage());
                break;
            }

            if (responseData.getHeader().isFail()) {
                log.error("response code : " + responseData.getHeader().getResultCode() + " / message : " + responseData.getHeader().getResultMsg());
                return;
            }

            if (responseData.getBody().isItemEmpty()) {
                log.info("response item or total-count is empty");
                return;
            }

            if (totalPage == 0) {
                totalPage = this.getTotalPage(responseData.getBody().getTotalCount(), numOfRows);
            }

            // entity & repository 생성 및 저장
            final List<DrugReExaminationInfo> drugReExaminationInfos = responseData.getBody().getItems().stream()
                    .map(item -> DrugReExaminationInfo.builder()
                            .hashCode(item.getHashCode())
                            .enterpriseName(item.getEnterpriseName())
                            .factoryAddress(item.getFactoryAddress())
                            .representative(item.getRepresentative())
                            .itemName(item.getItemName())
                            .itemNo(item.getItemNo())
                            .reportType(item.getReportType())
                            .classificationName(item.getClassificationName())
                            .reExaminationStartDate(item.getReExaminationStartDate())
                            .reExaminationCodeName(item.getReExaminationCodeName())
                            .reportDeadlineDate(item.getReportDeadlineDate())
                            .resultDate(item.getResultDate())
                            .build())
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(drugReExaminationInfos)) {
                log.error("entity generate is fail");
                return;
            }

            this.drugReExaminationInfoRepository.saveAll(drugReExaminationInfos);
            pageNo ++;
        } while (pageNo <= totalPage);
    }

    @Test
    void getDrugAdministrativeDispositionListClientTest() {
        final int pageNo = 1;
        final int numOfRows = 1;

        final Map<String, String> request = JsonConvert.toMap(DrugAdministrativeDispositionListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(pageNo)
                .numOfRows(numOfRows)
                .type(RESPONSE_TYPE)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugAdministrativeDispositionList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugAdministrativeDispositionListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void createDrugAdministrativeDispositionInfo() {
        int pageNo = 1;
        final int numOfRows = 100;
        int totalPage = 0;

        do {
            log.info("pageNo : " + pageNo + " / totalPage : " + totalPage);
            final Map<String, String> request = JsonConvert.toMap(DrugAdministrativeDispositionListRequest.builder()
                    .serviceKey(publicDataApiProperties.getEncodeKey())
                    .pageNo(pageNo)
                    .numOfRows(numOfRows)
                    .type(RESPONSE_TYPE)
                    .build());

            assertThat(request).isNotEmpty();

            final Response response = this.medicalDrugClient.getDrugAdministrativeDispositionList(request);

            if (Objects.isNull(response)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getMessage());
                break;
            }

            if (response.status() != HttpStatus.OK.value()) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getMessage());
                break;
            }

            final String responseBody = this.getBodyString(response);

            if (StringUtils.isBlank(responseBody)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getMessage());
                break;
            }

            final DrugAdministrativeDispositionListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
            });

            if (Objects.isNull(responseData) || Objects.isNull(responseData.getHeader()) || Objects.isNull(responseData.getBody())) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getCode() + " / "
                        + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getMessage());
                break;
            }

            if (responseData.getHeader().isFail()) {
                log.error("response code : " + responseData.getHeader().getResultCode() + " / message : " + responseData.getHeader().getResultMsg());
                return;
            }

            if (responseData.getBody().isItemEmpty()) {
                log.info("response item or total-count is empty");
                return;
            }

            if (totalPage == 0) {
                totalPage = this.getTotalPage(responseData.getBody().getTotalCount(), numOfRows);
            }

            // entity & repository 생성 및 저장
            final List<DrugAdministrativeDispositionInfo> drugAdministrativeDispositionInfos = responseData.getBody().getItems().stream()
                    .map(item -> DrugAdministrativeDispositionInfo.builder()
                            .hashCode(item.getHashCode())
                            .enterpriseName(item.getEnterpriseName())
                            .address(item.getAddress())
                            .enterpriseNo(item.getEnterpriseNo())
                            .itemName(item.getItemName())
                            .violationLaw(item.getViolationLaw())
                            .violationContents(item.getViolationContents())
                            .administrativeDispositionSeq(item.getAdministrativeDispositionSeq())
                            .administrativeDispositionName(item.getAdministrativeDispositionName())
                            .administrativeDispositionDate(item.getAdministrativeDispositionDate())
                            .administrativeDispositionPeriod(item.getAdministrativeDispositionPeriod())
                            .build())
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(drugAdministrativeDispositionInfos)) {
                log.error("entity generate is fail");
                return;
            }

            this.drugAdministrativeDispositionInfoRepository.saveAll(drugAdministrativeDispositionInfos);
            pageNo ++;
        } while (pageNo <= totalPage);
    }

    @Test
    void getDrugStabilityLetterListClientTest() {
        final int pageNo = 1;
        final int numOfRows = 1;

        final Map<String, String> request = JsonConvert.toMap(DrugStabilityLetterListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(pageNo)
                .numOfRows(numOfRows)
                .type(RESPONSE_TYPE)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugStabilityLetterList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugStabilityLetterListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void createDrugStabilityLetterInfo() {
        int pageNo = 1;
        final int numOfRows = 100;
        int totalPage = 0;

        do {
            log.info("pageNo : " + pageNo + " / totalPage : " + totalPage);
            final Map<String, String> request = JsonConvert.toMap(DrugStabilityLetterListRequest.builder()
                    .serviceKey(publicDataApiProperties.getEncodeKey())
                    .pageNo(pageNo)
                    .numOfRows(numOfRows)
                    .type(RESPONSE_TYPE)
                    .build());

            assertThat(request).isNotEmpty();

            final Response response = this.medicalDrugClient.getDrugStabilityLetterList(request);

            if (Objects.isNull(response)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getMessage());
                break;
            }

            if (response.status() != HttpStatus.OK.value()) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getMessage());
                break;
            }

            final String responseBody = this.getBodyString(response);

            if (StringUtils.isBlank(responseBody)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getMessage());
                break;
            }

            final DrugStabilityLetterListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
            });

            if (Objects.isNull(responseData) || Objects.isNull(responseData.getHeader()) || Objects.isNull(responseData.getBody())) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getCode() + " / "
                        + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getMessage());
                break;
            }

            if (responseData.getHeader().isFail()) {
                log.error("response code : " + responseData.getHeader().getResultCode() + " / message : " + responseData.getHeader().getResultMsg());
                return;
            }

            if (responseData.getBody().isItemEmpty()) {
                log.info("response item or total-count is empty");
                return;
            }

            if (totalPage == 0) {
                totalPage = this.getTotalPage(responseData.getBody().getTotalCount(), numOfRows);
            }

            // entity & repository 생성 및 저장
            final List<DrugStabilityLetterInfo> drugStabilityLetterInfos = responseData.getBody().getItems().stream()
                    .map(item -> DrugStabilityLetterInfo.builder()
                            .hashCode(item.getHashCode())
                            .no(item.getNo())
                            .subject(item.getSubject())
                            .writer(item.getWriter())
                            .registrationDate(item.getRegistrationDate())
                            .fileUrl(item.getFileUrl())
                            .build())
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(drugStabilityLetterInfos)) {
                log.error("entity generate is fail");
                return;
            }

            this.drugStabilityLetterInfoRepository.saveAll(drugStabilityLetterInfos);
            pageNo ++;
        } while (pageNo <= totalPage);
    }

    @Test
    void getDrugProductPermissionListClientTest() {
        final int pageNo = 1;
        final int numOfRows = 1;

        final Map<String, String> request = JsonConvert.toMap(DrugProductPermissionListRequest.builder()
                .serviceKey(publicDataApiProperties.getEncodeKey())
                .pageNo(pageNo)
                .numOfRows(numOfRows)
                .type(RESPONSE_TYPE)
                .build());

        assertThat(request).isNotEmpty();

        final Response response = this.medicalDrugClient.getDrugProductPermissionList(request);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value());

        final String responseBody = this.getBodyString(response);

        assertThat(responseBody).isNotBlank();

        final DrugProductPermissionListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
        });

        assertThat(responseData).isNotNull();
        assertThat(responseData.getHeader().getResultCode()).isEqualTo("00");
        assertThat(responseData.getBody().getItems()).isNotEmpty();
    }

    @Test
    void creatDrugProductPermissionInfo() {
        int pageNo = 600;
        final int numOfRows = 100;
        int totalPage = 0;

        do {
            log.info("pageNo : " + pageNo + " / totalPage : " + totalPage);
            final Map<String, String> request = JsonConvert.toMap(DrugProductPermissionListRequest.builder()
                    .serviceKey(publicDataApiProperties.getEncodeKey())
                    .pageNo(pageNo)
                    .numOfRows(numOfRows)
                    .type(RESPONSE_TYPE)
                    .build());

            assertThat(request).isNotEmpty();

            final Response response = this.medicalDrugClient.getDrugProductPermissionList(request);

            if (Objects.isNull(response)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_EMPTY.getMessage());
                break;
            }

            if (response.status() != HttpStatus.OK.value()) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_STATUS_NOT_OK.getMessage());
                break;
            }

            final String responseBody = this.getBodyString(response);

            if (StringUtils.isBlank(responseBody)) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getCode() + " / " + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_EMPTY.getMessage());
                break;
            }

            final DrugProductPermissionListResponse responseData = JsonConvert.toObject(responseBody, new TypeReference<>() {
            });

            if (Objects.isNull(responseData) || Objects.isNull(responseData.getHeader()) || Objects.isNull(responseData.getBody())) {
                log.error(DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getCode() + " / "
                        + DrugResponseCode.CODE_FAIL_RESPONSE_BODY_CONVERT_FAIL.getMessage());
                break;
            }

            if (responseData.getHeader().isFail()) {
                log.error("response code : " + responseData.getHeader().getResultCode() + " / message : " + responseData.getHeader().getResultMsg());
                return;
            }

            if (responseData.getBody().isItemEmpty()) {
                log.info("response item or total-count is empty");
                return;
            }

            if (totalPage == 0) {
                totalPage = this.getTotalPage(responseData.getBody().getTotalCount(), numOfRows);
            }

            // entity & repository 생성 및 저장
            final List<DrugProductPermissionInfo> drugProductPermissionInfos = responseData.getBody().getItems().stream()
                    .map(item -> DrugProductPermissionInfo.builder()
                            .hashCode(item.getHashCode())
                            .enterpriseName(item.getEnterpriseName())
                            .industry(item.getIndustry())
                            .serialNo(item.getSerialNo())
                            .drugType(item.getDrugType())
                            .productType(item.getProductType())
                            .permissionNo(item.getPermissionNo())
                            .permissionType(item.getPermissionType())
                            .itemSeq(item.getItemSeq())
                            .itemName(item.getItemName())
                            .itemPermissionDate(item.getItemPermissionDate())
                            .itemIngredientName(item.getItemIngredientName())
                            .itemIngredientCount(item.getItemIngredientCount())
                            .cancelDate(item.getCancelDate())
                            .cancelName(item.getCancelName())
                            .productImageUrl(item.getProductImageUrl())
                            .build())
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(drugProductPermissionInfos)) {
                log.error("entity generate is fail");
                return;
            }

            this.drugProductPermissionInfoRepository.saveAll(drugProductPermissionInfos);
            pageNo ++;
        } while (pageNo <= totalPage);
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

    private int getTotalPage(final int totalCount, final int numOfRow) {
        if (totalCount == 0 || numOfRow == 0) {
            return 0;
        }

        return BigDecimal.valueOf(totalCount).divide(BigDecimal.valueOf(numOfRow), RoundingMode.CEILING).intValue();
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

        final UsageQuantityDrugEffectAreaListResponse response = XmlConvert.toObject(xml, new TypeReference<>() {
        });

        assertThat(response).isNotNull();

        log.info(JsonConvert.toString(response));
    }
}
