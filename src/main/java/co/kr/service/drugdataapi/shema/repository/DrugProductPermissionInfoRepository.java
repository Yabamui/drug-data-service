package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.DrugProductPermissionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugProductPermissionInfoRepository extends JpaRepository<DrugProductPermissionInfo, String> {
}
