package co.kr.service.drugdataapi.model.feignclient.response;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RareDrugListResponse {
    private final GetDrbEasyDrugListHeader header;
    private final RareDrugListBody body;
}
