package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.DrugClinicalTrialInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugClinicalTrialInfoRepository extends JpaRepository<DrugClinicalTrialInfo, Integer> {
}
