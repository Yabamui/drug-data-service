package co.kr.service.drugdataapi.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "medical-drug-client", value = "medical-drug-client", url = "http://apis.data.go.kr")
public interface MedicalDrugClient {
}
