package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.RareDrugOverview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RareDrugOverviewRepository extends JpaRepository<RareDrugOverview, String> {
}
