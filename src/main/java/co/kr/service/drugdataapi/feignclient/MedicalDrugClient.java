package co.kr.service.drugdataapi.feignclient;

import java.util.Map;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "medical-drug-client", value = "medical-drug-client", url = "http://apis.data.go.kr")
public interface MedicalDrugClient {
    /**
     * 의약품개요정보 조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList")
    Response getDrbEasyDrugList(@RequestParam Map<String, String> request);

    /**
     * 희귀의약품 정보
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/1470000/RareMdcinInfoService/getRareMdcinList")
    Response getRareDrugList(@RequestParam Map<String, String> request);

    /**
     * 희귀의약품성분조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/1471000/RareDrugCpntService/getRareDrugCpntInq")
    Response getRareDrugIngredient(@RequestParam Map<String, String> request);

    /**
     * 의약품 특허정보
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/1470000/MdcinPatentInfoService/getMdcinPatentInfoList")
    Response getDrugPatentInfoList(@RequestParam Map<String, String> request);

    /**
     * 필수의약품내역
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/1471000/MdcEssntlItemInfoService01/getMdcEssntlItemList01")
    Response getDrugEssentialItemList(@RequestParam Map<String, String> request);

    /**
     * 의약품사용정보조회서비스-약효분류군지역별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getMeftDivAreaList")
    Response getUsageQuantityDrugEffectAreaList(@RequestParam Map<String, String> request);

    /**
     * 약효분류군의료기관종별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getMeftDivClList")
    Response getUsageQuantityDrugEffectInstitutionList(@RequestParam Map<String, String> request);

    /**
     * 약효분류군상병별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getMeftDivSickList")
    Response getUsageQuantityDrugEffectDiseaseList(@RequestParam Map<String, String> request);

    /**
     * 3단계ATC별지역별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getAtcStp3AreaList")
    Response getUsageQuantityATCStep3AreaList(@RequestParam Map<String, String> request);

    /**
     * 3단계ATC의료기관종별목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getAtcStp3AreaList")
    Response getUsageQuantityATCStep3InstitutionList(@RequestParam Map<String, String> request);

    /**
     * 3단계ATC상병별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getAtcStp3SickList")
    Response getUsageQuantityATCStep3DiseaseList(@RequestParam Map<String, String> request);

    /**
     * 4단계ATC별지역별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getAtcStp4AreaList")
    Response getUsageQuantityATCStep4AreaList(@RequestParam Map<String, String> request);

    /**
     * 4단계ATC별의료기관종별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getAtcStp4ClList")
    Response getUsageQuantityATCStep4InstitutionList(@RequestParam Map<String, String> request);

    /**
     * 4단계ATC상병별사용량목록조회
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getAtcStp4SickList")
    Response getUsageQuantityATCStep4DiseaseList(@RequestParam Map<String, String> request);
}
