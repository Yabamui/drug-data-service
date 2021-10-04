package co.kr.service.drugdataapi.shema.entity;

import java.io.Serializable;

import javax.persistence.*;
import co.kr.service.drugdataapi.shema.EntityBaseAudit;
import lombok.*;

@Entity
@Table(name = "drug_essential_item", schema = "drug_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DrugEssentialItem extends EntityBaseAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "essential_item_name", length = 256)
    private String essentialItemName;

    @Column(name = "medical_efficacy", length = 65535)
    private String medicalEfficacy;

    @Column(name = "appoint_date", length = 8)
    private String appointDate;

    @Builder
    private DrugEssentialItem(final String essentialItemName, final String medicalEfficacy, final String appointDate) {
        this.essentialItemName = essentialItemName;
        this.medicalEfficacy = medicalEfficacy;
        this.appointDate = appointDate;
    }

    public void updateByApiResponse(final String medicalEfficacy, final String appointDate) {
        this.medicalEfficacy = medicalEfficacy;
        this.appointDate = appointDate;
    }
}
