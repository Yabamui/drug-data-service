package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_stability_letter_info", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugStabilityLetterInfo extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "hash_code", length = 64, nullable = false, columnDefinition = "varchar(64) comment '해시정보'")
    private String hashCode;

    @Column(name = "no", length = 256, columnDefinition = "varchar(256) comment '관리번호'")
    private String no;

    @Column(name = "subject", length = 256, columnDefinition = "varchar(256) comment '제목'")
    private String subject;

    @Column(name = "writer", length = 256, columnDefinition = "varchar(256) comment '작성자'")
    private String writer;

    @Column(name = "registration_date", length = 20, columnDefinition = "varchar(20) comment '등록일자'")
    private String registrationDate;

    @Column(name = "file_url", length = 256, columnDefinition = "varchar(256) comment '파일URL'")
    private String fileUrl;

    @Builder
    private DrugStabilityLetterInfo(final String hashCode, final String no, final String subject, final String writer,
                                    final String registrationDate, final String fileUrl) {
        this.hashCode = hashCode;
        this.no = no;
        this.subject = subject;
        this.writer = writer;
        this.registrationDate = registrationDate;
        this.fileUrl = fileUrl;
    }
}
