package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_clinical_trial_info", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugClinicalTrialInfo extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "hash_code", length = 32, columnDefinition = "varchar(32) comment '해시정보'")
    private String hashCode;

    @Column(name = "apply_name", length = 256, columnDefinition = "varchar(256) comment '신청자'")
    private String applyName;

    @Column(name = "approval_time", length = 256, columnDefinition = "varchar(256) comment '승인일'")
    private String approvalTime;

    @Column(name = "lab_name", length = 65535, columnDefinition = "text comment '실시기관명'")
    private String labName;

    @Column(name = "goods_name", length = 256, columnDefinition = "varchar(256) comment '제품명'")
    private String goodsName;

    @Column(name = "clinical_trial_title", length = 65535, columnDefinition = "text comment '시험제목'")
    private String clinicalTrialTitle;

    @Column(name = "clinical_trial_step", length = 256, columnDefinition = "varchar(256) comment '단계'")
    private String clinicalTrialStep;

    @Builder
    private DrugClinicalTrialInfo(final String hashCode, final String applyName, final String approvalTime,
                                  final String labName, final String goodsName, final String clinicalTrialTitle,
                                  final String clinicalTrialStep) {
        this.hashCode = hashCode;
        this.applyName = applyName;
        this.approvalTime = approvalTime;
        this.labName = labName;
        this.goodsName = goodsName;
        this.clinicalTrialTitle = clinicalTrialTitle;
        this.clinicalTrialStep = clinicalTrialStep;
    }
}
