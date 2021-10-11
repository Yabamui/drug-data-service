package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_product_permission_info", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugProductPermissionInfo extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "hash_code", length = 64, nullable = false, columnDefinition = "varchar(64) comment '해시정보'")
    private String hashCode;

    @Column(name = "enterprise_name", length = 256, columnDefinition = "varchar(256) comment '업체명'")
    private String enterpriseName;

    @Column(name = "industry", length = 256, columnDefinition = "varchar(256) comment '업종'")
    private String industry;

    @Column(name = "serial_no", length = 20, columnDefinition = "varchar(20) comment '품목일련번호'")
    private String serialNo;

    @Column(name = "drug_type", length = 256, columnDefinition = "varchar(256) comment '전문/일반 의약품 구분'")
    private String drugType;

    @Column(name = "product_type", length = 256, columnDefinition = "varchar(256) comment '분류명'")
    private String productType;

    @Column(name = "permission_no", length = 256, columnDefinition = "varchar(256) comment '품목허가번호'")
    private String permissionNo;

    @Column(name = "permission_type", length = 256, columnDefinition = "varchar(256) comment '신고/허가구분'")
    private String permissionType;

    @Column(name = "item_seq", length = 256, columnDefinition = "varchar(256) comment '품목기준코드'")
    private String itemSeq;

    @Column(name = "item_name", length = 65535, columnDefinition = "text comment '품목명'")
    private String itemName;

    @Column(name = "item_permission_date", length = 256, columnDefinition = "varchar(256) comment '품목허가일자'")
    private String itemPermissionDate;

    @Column(name = "item_ingredient_name", length = 65535, columnDefinition = "text comment '주성분'")
    private String itemIngredientName;

    @Column(name = "item_ingredient_count", length = 256, columnDefinition = "varchar(256) comment '주성분수'")
    private String itemIngredientCount;

    @Column(name = "cancel_date", length = 256, columnDefinition = "varchar(256) comment '취하일자'")
    private String cancelDate;

    @Column(name = "cancel_name", length = 256, columnDefinition = "varchar(256) comment '취하구분'")
    private String cancelName;

    @Column(name = "product_image_url", length = 256)
    private String productImageUrl;

    @Builder
    private DrugProductPermissionInfo(final String hashCode, final String enterpriseName, final String industry,
                                      final String serialNo, final String drugType, final String productType,
                                      final String permissionNo, final String permissionType, final String itemSeq,
                                      final String itemName, final String itemPermissionDate, final String itemIngredientName,
                                      final String itemIngredientCount, final String cancelDate, final String cancelName,
                                      final String productImageUrl) {
        this.hashCode = hashCode;
        this.enterpriseName = enterpriseName;
        this.industry = industry;
        this.serialNo = serialNo;
        this.drugType = drugType;
        this.productType = productType;
        this.permissionNo = permissionNo;
        this.permissionType = permissionType;
        this.itemSeq = itemSeq;
        this.itemName = itemName;
        this.itemPermissionDate = itemPermissionDate;
        this.itemIngredientName = itemIngredientName;
        this.itemIngredientCount = itemIngredientCount;
        this.cancelDate = cancelDate;
        this.cancelName = cancelName;
        this.productImageUrl = productImageUrl;
    }
}
