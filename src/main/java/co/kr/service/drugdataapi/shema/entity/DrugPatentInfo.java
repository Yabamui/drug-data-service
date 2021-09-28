package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_patent_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugPatentInfo extends EntityBaseAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ingredient_name_eng", length = 256, columnDefinition = "varchar(256) comment '성분명(영)'")
    private String ingredientNameEng;

    @Column(name = "ingredient_name", length = 256, columnDefinition = "varchar(256) comment '성분명(한)'")
    private String ingredientName;

    @Column(name = "goods_name_eng", length = 256, columnDefinition = "varchar(256) comment '제품명(영)'")
    private String goodsNameEng;

    @Column(name = "goods_name", length = 256, columnDefinition = "varchar(256) comment '제품명(한)'")
    private String goodsName;

    @Column(name = "selling_corp", length = 256, columnDefinition = "varchar(256) comment '허가업체'")
    private String sellingCorp;

    @Column(name = "dosage_form", length = 256, columnDefinition = "varchar(256) comment '제형'")
    private String dosageForm;

    @Column(name = "strength", length = 256, columnDefinition = "varchar(256) comment '함량'")
    private String strength;

    @Column(name = "grouping_no", length = 256, columnDefinition = "varchar(256) comment '분류법호'")
    private String groupingNo;

    @Column(name = "pms_expired_date", length = 20, columnDefinition = "varchar(20) comment 'PMS만료일'")
    private String pmsExpiredDate;

    @Column(name = "kor_suit_yn", length = 256, columnDefinition = "varchar(256) comment '국내소송여부'")
    private String korSuitYn;

    @Column(name = "item_seq", length = 20, columnDefinition = "varchar(20) comment '품목번호'")
    private String itemSeq;

    @Column(name = "page_category", length = 256, columnDefinition = "varchar(256) comment '범주'")
    private String pageCategory;

    @Column(name = "patent_category", length = 256, columnDefinition = "varchar(256) comment '구분'")
    private String patentCategory;

    @Column(name = "domestic_invention_name", length = 65535, columnDefinition = "text comment '발명의 명칭'")
    private String domesticInventionName;

    @Column(name = "patentee", length = 256, columnDefinition = "varchar(256) comment '특허권자'")
    private String patentee;

    @Column(name = "domestic_patent_no", length = 64, columnDefinition = "varchar(64) comment '등록번호'")
    private String domesticPatentNo;

    @Column(name = "domestic_patent_status", length = 64, columnDefinition = "varchar(64) comment '상태'")
    private String domesticPatentStatus;

    @Column(name = "domestic_end_date", length = 20, columnDefinition = "varchar(20) comment '존속기간만료일'")
    private String domesticEndDate;

    @Builder
    private DrugPatentInfo(final String ingredientNameEng, final String ingredientName, final String goodsNameEng,
                           final String goodsName, final String sellingCorp, final String dosageForm, final String strength,
                           final String groupingNo, final String pmsExpiredDate, final String korSuitYn, final String itemSeq,
                           final String pageCategory, final String patentCategory, final String domesticInventionName,
                           final String patentee, final String domesticPatentNo, final String domesticPatentStatus,
                           final String domesticEndDate) {
        this.ingredientNameEng = ingredientNameEng;
        this.ingredientName = ingredientName;
        this.goodsNameEng = goodsNameEng;
        this.goodsName = goodsName;
        this.sellingCorp = sellingCorp;
        this.dosageForm = dosageForm;
        this.strength = strength;
        this.groupingNo = groupingNo;
        this.pmsExpiredDate = pmsExpiredDate;
        this.korSuitYn = korSuitYn;
        this.itemSeq = itemSeq;
        this.pageCategory = pageCategory;
        this.patentCategory = patentCategory;
        this.domesticInventionName = domesticInventionName;
        this.patentee = patentee;
        this.domesticPatentNo = domesticPatentNo;
        this.domesticPatentStatus = domesticPatentStatus;
        this.domesticEndDate = domesticEndDate;
    }

}
