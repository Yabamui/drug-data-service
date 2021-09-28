package co.kr.service.drugdataapi.model.customproperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PublicDataApiProperties {
    private String key;
    private String encodeKey;
}
