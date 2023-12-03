package one;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class DirtyDataEncoder {

    private static final Map<String, String> MAPPING = Map.of(
            "one", "one1one", "two", "two2two", "three", "three3three",
            "four", "four4four", "five", "five5five", "six", "six6six",
            "seven", "seven7seven", "eight", "eight8eight", "nine", "nine9nine");

    public List<Integer> processFileFromResourcesPath(final String filePath) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return br.lines()
                .map(this::mapStringToNumber)
                .map(str -> str.replaceAll("[a-z]", ""))
                .map(str -> str.replaceAll("(\\d)\\d*(\\d)", "$1$2"))
                .map(str -> {
                    if (str.length() < 2) {
                        return str + str;
                    }
                    return str;
                })
                .filter(StringUtils::isNotBlank)
                .map(Integer::valueOf)
                .toList();
    }

    private String mapStringToNumber(final String str) {
        String result = str;
        for (Map.Entry<String, String> entry : MAPPING.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
