package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_effect_usage_quantity_area", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugEffectUsageQuantityArea extends EntityBaseAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "diagnosis_date", length = 6, columnDefinition = "varchar(6) comment '진료년월'")
    private String diagnosisDate;

    @Column(name = "insurer_code", length = 1, columnDefinition = "varchar(1) comment '보험자구분(0:전체,4:건강보험,5:의료급여,7:보훈)'")
    private String insurerCode;

    @Column(name = "drug_effect_no", length = 3, columnDefinition = "varchar(3) comment '약효분류군(검색유형 : 일치(=)) (의약품사용정보서비스>의약품코드정보조회(getMsupCdInfoList)에서msupCdTp=1로확인가능)'")
    private String drugEffectNo;

    @Column(name = "drug_effect_no_name", length = 256, columnDefinition = "varchar(256) comment '약효분류군명'")
    private String drugEffectNoName;

    @Column(name = "use_amount", length = 20, columnDefinition = "varchar(20) comment '금액(단위:원)'")
    private String useAmount;

    @Column(name = "sido_code", length = 6, columnDefinition = "varchar(6) comment '시도코드'")
    private String sidoCode;

    @Column(name = "sido_code_name", length = 400, columnDefinition = "varchar(400) comment '시도코드명'")
    private String sidoCodeName;

    @Column(name = "sigungu_code", length = 6, columnDefinition = "varchar(6) comment '시군구코드'")
    private String sigunguCode;

    @Column(name = "sigungu_code_name", length = 400, columnDefinition = "varchar(400) comment '시군구코드명'")
    private String sigunguCodeName;

    @Column(name = "total_use_quantity", length = 25, columnDefinition = "varchar(25) comment '수량(소수점 포함, 소수점 4째 자리까지 제공됨)'")
    private String totalUseQuantity;

    @Builder
    private DrugEffectUsageQuantityArea(final String diagnosisDate, final String insurerCode, final String drugEffectNo,
                                        final String drugEffectNoName, final String useAmount, final String sidoCode,
                                        final String sidoCodeName, final String sigunguCode, final String sigunguCodeName,
                                        final String totalUseQuantity) {
        this.diagnosisDate = diagnosisDate;
        this.insurerCode = insurerCode;
        this.drugEffectNo = drugEffectNo;
        this.drugEffectNoName = drugEffectNoName;
        this.useAmount = useAmount;
        this.sidoCode = sidoCode;
        this.sidoCodeName = sidoCodeName;
        this.sigunguCode = sigunguCode;
        this.sigunguCodeName = sigunguCodeName;
        this.totalUseQuantity = totalUseQuantity;
    }
}
