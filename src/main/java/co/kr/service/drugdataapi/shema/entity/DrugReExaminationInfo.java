package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_re_examination_info", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugReExaminationInfo extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "hash_code", length = 64, nullable = false, columnDefinition = "varchar(64) comment '해시정보'")
    private String hashCode;

    @Column(name = "enterprise_name", length = 256, columnDefinition = "varchar(256) comment '업체명'")
    private String enterpriseName;

    @Column(name = "factory_address", length = 256, columnDefinition = "varchar(256) comment '업체소제지'")
    private String factoryAddress;

    @Column(name = "representative", length = 256, columnDefinition = "varchar(256) comment '대표자명'")
    private String representative;

    @Column(name = "item_name", length = 65535, columnDefinition = "text comment '품목명'")
    private String itemName;

    @Column(name = "item_no", length = 256, columnDefinition = "varchar(256) comment '품목허가번호'")
    private String itemNo;

    @Column(name = "report_type", length = 40, columnDefinition = "varchar(40) comment '보고구분'")
    private String reportType;

    @Column(name = "classification_name", length = 256, columnDefinition = "varchar(256) comment '약효군번호'")
    private String classificationName;

    @Column(name = "re_examination_start_date", length = 20, columnDefinition = "varchar(20) comment '허가일자'")
    private String reExaminationStartDate;

    @Column(name = "re_examination_code_name", length = 60, columnDefinition = "varchar(60) comment '연차구분'")
    private String reExaminationCodeName;

    @Column(name = "report_deadline_date", length = 20, columnDefinition = "varchar(20) comment '보고일자'")
    private String reportDeadlineDate;

    @Column(name = "result_date", length = 20, columnDefinition = "varchar(20) comment '처리일자'")
    private String resultDate;

    @Builder
    private DrugReExaminationInfo(final String hashCode, final String enterpriseName, final String factoryAddress,
                                  final String representative, final String itemName, final String itemNo,
                                  final String reportType, final String classificationName, final String reExaminationStartDate,
                                  final String reExaminationCodeName, final String reportDeadlineDate, final String resultDate) {
        this.hashCode = hashCode;
        this.enterpriseName = enterpriseName;
        this.factoryAddress = factoryAddress;
        this.representative = representative;
        this.itemName = itemName;
        this.itemNo = itemNo;
        this.reportType = reportType;
        this.classificationName = classificationName;
        this.reExaminationStartDate = reExaminationStartDate;
        this.reExaminationCodeName = reExaminationCodeName;
        this.reportDeadlineDate = reportDeadlineDate;
        this.resultDate = resultDate;
    }
}
