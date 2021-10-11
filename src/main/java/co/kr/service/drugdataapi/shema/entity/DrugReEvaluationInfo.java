package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_re_evaluation_info", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugReEvaluationInfo extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "hash_code", length = 64, nullable = false, columnDefinition = "varchar(64) comment '해시정보'")
    private String hashCode;

    @Column(name = "enterprise_name", length = 256, columnDefinition = "varchar(256) comment '업체명'")
    private String enterpriseName;

    @Column(name = "enterprise_no", length = 256, columnDefinition = "varchar(256) comment '업허가번호'")
    private String enterpriseNo;

    @Column(name = "representative", length = 256, columnDefinition = "varchar(256) comment '대표자명'")
    private String representative;

    @Column(name = "item_name", length = 65535, columnDefinition = "text comment '품목명'")
    private String itemName;

    @Column(name = "item_no", length = 256, columnDefinition = "varchar(256) comment '품목허가번호'")
    private String itemNo;

    @Column(name = "item_permission_date", length = 20, columnDefinition = "varchar(20) comment '허가일자'")
    private String itemPermissionDate;

    @Column(name = "classification_name", length = 256, columnDefinition = "varchar(256) comment '약효군번호'")
    private String classificationName;

    @Column(name = "re_evaluation_presentation_name", length = 256, columnDefinition = "varchar(256) comment '재평가제출대상'")
    private String reEvaluationPresentationName;

    @Column(name = "re_evaluation_year", length = 8, columnDefinition = "varchar(8) comment '재평가대상년도'")
    private String reEvaluationYear;

    @Column(name = "result_date", length = 20, columnDefinition = "varchar(20) comment '처리일자'")
    private String resultDate;

    @Builder
    private DrugReEvaluationInfo(final String hashCode, final String enterpriseName, final String enterpriseNo,
                                 final String representative, final String itemName, final String itemNo, final String itemPermissionDate,
                                 final String classificationName, final String reEvaluationPresentationName,
                                 final String reEvaluationYear, final String resultDate) {
        this.hashCode = hashCode;
        this.enterpriseName = enterpriseName;
        this.enterpriseNo = enterpriseNo;
        this.representative = representative;
        this.itemName = itemName;
        this.itemNo = itemNo;
        this.itemPermissionDate = itemPermissionDate;
        this.classificationName = classificationName;
        this.reEvaluationPresentationName = reEvaluationPresentationName;
        this.reEvaluationYear = reEvaluationYear;
        this.resultDate = resultDate;
    }

}
