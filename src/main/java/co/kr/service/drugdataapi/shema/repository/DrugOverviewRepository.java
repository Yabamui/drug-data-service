package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.DrugOverview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugOverviewRepository extends JpaRepository<DrugOverview, String> {
}
