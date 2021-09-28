package co.kr.service.drugdataapi.shema.repository;

import co.kr.service.drugdataapi.shema.entity.RareDrugIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RareDrugIngredientRepository extends JpaRepository<RareDrugIngredient, Integer> {
}
