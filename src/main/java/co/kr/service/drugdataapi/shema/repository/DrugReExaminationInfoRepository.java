package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.DrugReExaminationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugReExaminationInfoRepository extends JpaRepository<DrugReExaminationInfo, String> {
}
