package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_administrative_disposition_info", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugAdministrativeDispositionInfo extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "hash_code", length = 64, nullable = false, columnDefinition = "varchar(64) comment '해시정보'")
    private String hashCode;

    @Column(name = "enterprise_name", length = 256, columnDefinition = "varchar(256) comment '업체명'")
    private String enterpriseName;

    @Column(name = "address", length = 256, columnDefinition = "varchar(256) comment '업체소제지'")
    private String address;

    @Column(name = "enterprise_no", length = 256, columnDefinition = "varchar(256) comment '업허가번호'")
    private String enterpriseNo;

    @Column(name = "item_name", length = 65535, columnDefinition = "text comment '제품명'")
    private String itemName;

    @Column(name = "violation_law", length = 65535, columnDefinition = "text comment '위반법명'")
    private String violationLaw;

    @Column(name = "violation_contents", length = 65535, columnDefinition = "text comment '위반내용'")
    private String violationContents;

    @Column(name = "administrative_disposition_seq", length = 256, columnDefinition = "varchar(256) comment '행정처분일련번호'")
    private String administrativeDispositionSeq;

    @Column(name = "administrative_disposition_name", length = 65535, columnDefinition = "text comment '행정처분명'")
    private String administrativeDispositionName;

    @Column(name = "administrative_disposition_date", length = 20, columnDefinition = "varchar(20) comment '행정처분일자(최종확정일자)'")
    private String administrativeDispositionDate;

    @Column(name = "administrative_disposition_period", length = 40, columnDefinition = "varchar(40) comment '행정처분기간'")
    private String administrativeDispositionPeriod;

    @Builder
    private DrugAdministrativeDispositionInfo(final String hashCode, final String enterpriseName, final String address,
                                              final String enterpriseNo, final String itemName, final String violationLaw,
                                              final String violationContents, final String administrativeDispositionSeq,
                                              final String administrativeDispositionName, final String administrativeDispositionDate,
                                              final String administrativeDispositionPeriod) {
        this.hashCode = hashCode;
        this.enterpriseName = enterpriseName;
        this.address = address;
        this.enterpriseNo = enterpriseNo;
        this.itemName = itemName;
        this.violationLaw = violationLaw;
        this.violationContents = violationContents;
        this.administrativeDispositionSeq = administrativeDispositionSeq;
        this.administrativeDispositionName = administrativeDispositionName;
        this.administrativeDispositionDate = administrativeDispositionDate;
        this.administrativeDispositionPeriod = administrativeDispositionPeriod;
    }
}
