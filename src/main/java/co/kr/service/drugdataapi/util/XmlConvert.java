package co.kr.service.drugdataapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class XmlConvert {
    private static final ObjectMapper mapper = new XmlMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> String toString(final T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static <T> T toObject(final String jsonString, final TypeReference<T> reference) {
        try {
            return mapper.readValue(jsonString, reference);
        } catch (JsonProcessingException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }
}
