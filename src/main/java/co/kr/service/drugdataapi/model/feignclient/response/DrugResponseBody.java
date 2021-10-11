package co.kr.service.drugdataapi.model.feignclient.response;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.util.CollectionUtils;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonDeserialize(builder = DrugResponseBody.DrugResponseBodyBuilder.class)
public class DrugResponseBody <T> {
    @JsonProperty("pageNo")
    private final Integer pageNo;
    @JsonProperty("totalCount")
    private final Integer totalCount;
    @JsonProperty("numOfRows")
    private final Integer numOfRows;
    @JsonProperty("items")
    private final List<T> items;

    public static <T> DrugResponseBody<T> getInstance(final int pageNo, final int totalCount, final int numOfRows,
                                                   final List<T> items) {
        return DrugResponseBody.<T>builder()
                .pageNo(pageNo)
                .totalCount(totalCount)
                .numOfRows(numOfRows)
                .items(items)
                .build();
    }

    public static <T> DrugResponseBody<T> ofEmpty() {
        return DrugResponseBody.<T>builder()
                .pageNo(0)
                .totalCount(0)
                .numOfRows(0)
                .items(Collections.emptyList())
                .build();
    }

    public boolean isItemEmpty() {
        return CollectionUtils.isEmpty(this.items) || totalCount == 0;
    }
}
