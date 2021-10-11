package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_supply_lack_info", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugSupplyLackInfo extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "hash_code", length = 64, nullable = false, columnDefinition = "varchar(64) comment '해시정보'")
    private String hashCode;

    @Column(name = "report_progress_code", length = 256, columnDefinition = "varchar(256) comment '진행단계'")
    private String reportProgressCode;

    @Column(name = "short_supply_report_seq", length = 256, columnDefinition = "varchar(256) comment '보고번호'")
    private String shortSupplyReportSeq;

    @Column(name = "enterprise_seq", length = 256, columnDefinition = "varchar(256) comment '업체일련번호'")
    private String enterpriseSeq;

    @Column(name = "enterprise_name", length = 256, columnDefinition = "varchar(256) comment '업체명'")
    private String enterpriseName;

    @Column(name = "enterprise_no", length = 256, columnDefinition = "varchar(256) comment '업 허가번호'")
    private String enterpriseNo;

    @Column(name = "enterprise_address", length = 256, columnDefinition = "varchar(256) comment '업체소재지'")
    private String enterpriseAddress;

    @Column(name = "reporter", length = 256, columnDefinition = "varchar(256) comment '보고인성명'")
    private String reporter;

    @Column(name = "reporter_tel_no", length = 256, columnDefinition = "varchar(256) comment '보고인전화번호'")
    private String reporterTelNo;

    @Column(name = "department_receipt_no", length = 256, columnDefinition = "varchar(256) comment '부서접수번호'")
    private String departmentReceiptNo;

    @Column(name = "item_seq", length = 256, columnDefinition = "varchar(256) comment '품목기준코드'")
    private String itemSeq;

    @Column(name = "item_name", length = 256, columnDefinition = "varchar(256) comment '품목명'")
    private String itemName;

    @Column(name = "edi_code", length = 256, columnDefinition = "varchar(256) comment '표준코드'")
    private String ediCode;

    @Column(name = "short_supply_expectation_date", length = 20, columnDefinition = "varchar(20) comment '공급부족발생예상일자'")
    private String shortSupplyExpectationDate;

    @Column(name = "short_supply_reason", length = 65535, columnDefinition = "text comment '공급부족사유'")
    private String shortSupplyReason;

    @Column(name = "last_supply_date", length = 20, columnDefinition = "varchar(20) comment '마지막생산수입공급일자'")
    private String lastSupplyDate;

    @Column(name = "last_supply_type", length = 8, columnDefinition = "varchar(8) comment '생산/수입/공급구분'")
    private String lastSupplyType;

    @Column(name = "inventory_quantity_date", length = 20, columnDefinition = "varchar(20) comment '자사재고량기준일'")
    private String inventoryQuantityDate;

    @Column(name = "inventory_quantity", length = 20, columnDefinition = "varchar(20) comment '자사재고량'")
    private String inventoryQuantity;

    @Column(name = "treatment_impact", length = 65535, columnDefinition = "text comment '환자치료에미치는영향'")
    private String treatmentImpact;

    @Column(name = "supply_plan", length = 65535, columnDefinition = "text comment '공급정상화추진계획'")
    private String supplyPlan;

    @Column(name = "supply_plan_date", length = 20, columnDefinition = "varchar(20) comment '공급정상화예상일자'")
    private String supplyPlanDate;

    @Column(name = "report_date", length = 20, columnDefinition = "varchar(20) comment '보고일자'")
    private String reportDate;

    @Column(name = "result_date", length = 20, columnDefinition = "varchar(20) comment '처리일자'")
    private String resultDate;

    @Column(name = "open_agree_value", length = 40, columnDefinition = "varchar(40) comment '전자민원창구 공개여부'")
    private String openAgreeValue;

    @Builder
    private DrugSupplyLackInfo(final String hashCode, final String reportProgressCode, final String shortSupplyReportSeq,
                               final String enterpriseSeq, final String enterpriseName, final String enterpriseNo,
                               final String enterpriseAddress, final String reporter, final String reporterTelNo,
                               final String departmentReceiptNo, final String itemSeq, final String itemName,
                               final String ediCode, final String shortSupplyExpectationDate, final String shortSupplyReason,
                               final String lastSupplyDate, final String lastSupplyType, final String inventoryQuantityDate,
                               final String inventoryQuantity, final String treatmentImpact, final String supplyPlan,
                               final String supplyPlanDate, final String reportDate, final String resultDate,
                               final String openAgreeValue) {
        this.hashCode = hashCode;
        this.reportProgressCode = reportProgressCode;
        this.shortSupplyReportSeq = shortSupplyReportSeq;
        this.enterpriseSeq = enterpriseSeq;
        this.enterpriseName = enterpriseName;
        this.enterpriseNo = enterpriseNo;
        this.enterpriseAddress = enterpriseAddress;
        this.reporter = reporter;
        this.reporterTelNo = reporterTelNo;
        this.departmentReceiptNo = departmentReceiptNo;
        this.itemSeq = itemSeq;
        this.itemName = itemName;
        this.ediCode = ediCode;
        this.shortSupplyExpectationDate = shortSupplyExpectationDate;
        this.shortSupplyReason = shortSupplyReason;
        this.lastSupplyDate = lastSupplyDate;
        this.lastSupplyType = lastSupplyType;
        this.inventoryQuantityDate = inventoryQuantityDate;
        this.inventoryQuantity = inventoryQuantity;
        this.treatmentImpact = treatmentImpact;
        this.supplyPlan = supplyPlan;
        this.supplyPlanDate = supplyPlanDate;
        this.reportDate = reportDate;
        this.resultDate = resultDate;
        this.openAgreeValue = openAgreeValue;
    }
}
