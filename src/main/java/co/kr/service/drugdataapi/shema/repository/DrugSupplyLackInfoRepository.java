package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.DrugSupplyLackInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugSupplyLackInfoRepository extends JpaRepository<DrugSupplyLackInfo, String> {
}
