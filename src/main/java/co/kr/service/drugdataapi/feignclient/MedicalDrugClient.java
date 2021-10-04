package co.kr.service.drugdataapi.feignclient;

import java.util.Map;

import feign.Response;
import lombok.Getter;
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
    Response getDrugEffectUsageQuantityAreaList(@RequestParam Map<String, String> request);

    /**
     * default format
     *
     * @param request 요청 정보
     * @return 응답 정보
     */
    @GetMapping("/B551182/msupUserInfoService/getMeftDivClList")
    Response getDrugEffectUsageQuantityInstitutionList(@RequestParam Map<String, String> request);


    /**
     * default format
     *
     * @param request
     * @return
     */
    @GetMapping("")
    Response get(@RequestParam Map<String, String> request);
}
