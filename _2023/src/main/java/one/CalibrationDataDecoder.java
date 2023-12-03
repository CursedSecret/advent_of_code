package one;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CalibrationDataDecoder {

    public List<Integer> processFileFromResourcesPath(final String filePath) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return br.lines()
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
}
