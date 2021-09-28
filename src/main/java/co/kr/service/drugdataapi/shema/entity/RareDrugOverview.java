package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "rare_drug_overview")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RareDrugOverview extends EntityBaseAudit implements Serializable {
    @Id
    @Column(name = "rare_drug_appointment_no", length = 20, nullable = false, columnDefinition = "varchar(20) comment '희귀의약품지정번호'")
    private String rareDrugAppointmentNo;

    @Column(name = "goods_name", length = 256, columnDefinition = "varchar(256) comment '제품명'")
    private String goodsName;

    @Column(name = "product_name", length = 256, columnDefinition = "varchar(256) comment '제제명'")
    private String productName;

    @Column(name = "target_disease", length = 65535, columnDefinition = "text comment '대상질환'")
    private String targetDisease;

    @Column(name = "apply_name", length = 256, columnDefinition = "varchar(256) comment '신청인명'")
    private String applyName;

    @Column(name = "apply_tel_no", length = 40, columnDefinition = "varchar(40) comment '신청인전화번호'")
    private String applyTelNo;

    @Column(name = "manufacture_name", length = 512, columnDefinition = "varchar(512) comment '제조원'")
    private String manufactureName;

    @Column(name = "manufacture_place_name", length = 256, columnDefinition = "varchar(256) comment '제조소명'")
    private String manufacturePlaceName;

    @Column(name = "manufacture_place_zip_no", length = 10, columnDefinition = "varchar(10) comment '제조소우편번호'")
    private String manufacturePlaceZipNo;

    @Column(name = "manufacture_place_add1", length = 256, columnDefinition = "varchar(256) comment '제조소주소1'")
    private String manufacturePlaceAdd1;

    @Column(name = "manufacture_place_add2", length = 256, columnDefinition = "varchar(256) comment '제조소주소2'")
    private String manufacturePlaceAdd2;

    @Column(name = "manufacture_place_tel_no", length = 20, columnDefinition = "varchar(20) comment '제조소전화번호'")
    private String manufacturePlaceTelNo;

    @Column(name = "appointment_date", length = 20, columnDefinition = "varchar(20) comment '지정일자'")
    private String appointmentDate;

    @Column(name = "appointment_cancel_date", length = 20, columnDefinition = "varchar(20) comment '지정취소일자'")
    private String appointmentCancelDate;

    @Column(name = "receipt_no", length = 20, columnDefinition = "varchar(20) comment '접수번호'")
    private String receiptNo;

    @Builder
    private RareDrugOverview(final String rareDrugAppointmentNo, final String goodsName, final String productName,
                             final String targetDisease, final String applyName, final String applyTelNo,
                             final String manufactureName, final String manufacturePlaceName, final String manufacturePlaceZipNo,
                             final String manufacturePlaceAdd1, final String manufacturePlaceAdd2, final String manufacturePlaceTelNo,
                             final String appointmentDate, final String appointmentCancelDate, final String receiptNo) {
        this.rareDrugAppointmentNo = rareDrugAppointmentNo;
        this.goodsName = goodsName;
        this.productName = productName;
        this.targetDisease = targetDisease;
        this.applyName = applyName;
        this.applyTelNo = applyTelNo;
        this.manufactureName = manufactureName;
        this.manufacturePlaceName = manufacturePlaceName;
        this.manufacturePlaceZipNo = manufacturePlaceZipNo;
        this.manufacturePlaceAdd1 = manufacturePlaceAdd1;
        this.manufacturePlaceAdd2 = manufacturePlaceAdd2;
        this.manufacturePlaceTelNo = manufacturePlaceTelNo;
        this.appointmentDate = appointmentDate;
        this.appointmentCancelDate = appointmentCancelDate;
        this.receiptNo = receiptNo;
    }

}
