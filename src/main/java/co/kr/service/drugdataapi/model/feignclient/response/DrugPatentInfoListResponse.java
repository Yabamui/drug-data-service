package co.kr.service.drugdataapi.model.feignclient.response;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DrugPatentInfoListResponse {
    private final GetDrbEasyDrugListHeader header;
    private final DrugPatentInfoListBody body;
}
