package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.DrugPatentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugPatentInfoRepository extends JpaRepository<DrugPatentInfo, Integer> {
}
