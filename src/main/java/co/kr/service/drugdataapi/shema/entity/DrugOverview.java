package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_overview")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugOverview extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "item_seq", length = 64, nullable = false, columnDefinition = "varchar(64) comment '품목기준코드'")
    private String itemSeq;

    @Column(name = "enterprise_name", length = 256, columnDefinition = "varchar(256) comment '업체명'")
    private String enterpriseName;

    @Column(name = "item_name", length = 256, columnDefinition = "varchar(256) comment '제품명'")
    private String itemName;

    @Column(name = "efficacy", length = 65535, columnDefinition = "text comment '효능'")
    private String efficacy;

    @Column(name = "use_method", length = 65535, columnDefinition = "text comment '사용법'")
    private String useMethod;

    @Column(name = "attention_point_warn", length = 65535, columnDefinition = "text comment '주의사항 경고'")
    private String attentionPointWarn;

    @Column(name = "attention_point", length = 65535, columnDefinition = "text comment '주의사항'")
    private String attentionPoint;

    @Column(name = "introduction", length = 65535, columnDefinition = "text comment '상호작용'")
    private String introduction;

    @Column(name = "side_effect", length = 65535, columnDefinition = "text comment '부작용'")
    private String sideEffect;

    @Column(name = "deposit_method", length = 65535, columnDefinition = "text comment '보관법'")
    private String depositMethod;

    @Column(name = "open_date", length = 20, columnDefinition = "varchar(20) comment '공개일자'")
    private String openDate;

    @Column(name = "update_date", length = 20, columnDefinition = "varchar(20) comment '수정일자'")
    private String updateDate;

    @Column(name = "item_image", length = 128, columnDefinition = "varchar(128) comment '낱알이미지'")
    private String itemImage;

    @Builder
    private DrugOverview(final String itemSeq, final String enterpriseName, final String itemName, final String efficacy,
                         final String useMethod, final String attentionPointWarn, final String attentionPoint,
                         final String introduction, final String sideEffect, final String depositMethod,
                         final String openDate, final String updateDate, final String itemImage) {
        this.itemSeq = itemSeq;
        this.enterpriseName = enterpriseName;
        this.itemName = itemName;
        this.efficacy = efficacy;
        this.useMethod = useMethod;
        this.attentionPointWarn = attentionPointWarn;
        this.attentionPoint = attentionPoint;
        this.introduction = introduction;
        this.sideEffect = sideEffect;
        this.depositMethod = depositMethod;
        this.openDate = openDate;
        this.updateDate = updateDate;
        this.itemImage = itemImage;
    }
}
