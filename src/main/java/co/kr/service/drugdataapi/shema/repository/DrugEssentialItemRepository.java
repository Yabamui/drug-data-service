package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.DrugEssentialItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugEssentialItemRepository extends JpaRepository<DrugEssentialItem, Integer> {
}
