package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "rare_drug_ingredient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RareDrugIngredient extends EntityBaseAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rare_drug_appointment_no", length = 20, nullable = false, columnDefinition = "varchar(20) comment '희귀의약품지정번호'")
    private String rareDrugAppointmentNo;

    @Column(name = "manufacture_name", length = 512, columnDefinition = "varchar(512) comment '제조원'")
    private String manufactureName;

    @Column(name = "goods_name", length = 256, columnDefinition = "varchar(256) comment '제품명'")
    private String goodsName;

    @Column(name = "product_name", length = 256, columnDefinition = "varchar(256) comment '제제명'")
    private String productName;

    @Column(name = "target_disease", length = 65535, columnDefinition = "text comment '대상질환'")
    private String targetDisease;

    @Column(name = "appointment_date", length = 20, columnDefinition = "varchar(20) comment '지정일자'")
    private String appointmentDate;

    @Column(name = "appointment_cancel_date", length = 20, columnDefinition = "varchar(20) comment '지정취소일자'")
    private String appointmentCancelDate;

    @Column(name = "receipt_no", length = 20, columnDefinition = "varchar(20) comment '접수번호'")
    private String receiptNo;

    @Column(name = "ingredient_seq", length = 20, columnDefinition = "varchar(20) comment '성분일련번호'")
    private String ingredientSeq;

    @Column(name = "ingredient_code", length = 20, columnDefinition = "varchar(20) comment '성분코드'")
    private String ingredientCode;

    @Column(name = "ingredient_name", length = 256, columnDefinition = "varchar(256) comment '성분명'")
    private String ingredientName;

    @Builder
    private RareDrugIngredient(final String rareDrugAppointmentNo, final String manufactureName, final String goodsName,
                               final String productName, final String targetDisease, final String appointmentDate,
                               final String appointmentCancelDate, final String receiptNo, final String ingredientSeq,
                               final String ingredientCode, final String ingredientName) {
        this.rareDrugAppointmentNo = rareDrugAppointmentNo;
        this.manufactureName = manufactureName;
        this.goodsName = goodsName;
        this.productName = productName;
        this.targetDisease = targetDisease;
        this.appointmentDate = appointmentDate;
        this.appointmentCancelDate = appointmentCancelDate;
        this.receiptNo = receiptNo;
        this.ingredientSeq = ingredientSeq;
        this.ingredientCode = ingredientCode;
        this.ingredientName = ingredientName;
    }

    public void updateByApi(final String manufactureName, final String goodsName, final String productName,
                            final String targetDisease, final String appointmentDate, final String appointmentCancelDate,
                            final String receiptNo, final String ingredientCode, final String ingredientName) {
        this.manufactureName = manufactureName;
        this.goodsName = goodsName;
        this.productName = productName;
        this.targetDisease = targetDisease;
        this.appointmentDate = appointmentDate;
        this.appointmentCancelDate = appointmentCancelDate;
        this.receiptNo = receiptNo;
        this.ingredientCode = ingredientCode;
        this.ingredientName = ingredientName;
    }
}
