package one;

import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TrieDataEncoder {

    private static final PatriciaTrie<String> MAPPING = new PatriciaTrie<>(Map.of(
            "one", "1", "two", "2", "three", "3", "four", "4",
            "five", "5", "six", "6", "seven", "7", "eight", "8",
            "nine", "9"));

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
        if (result.length() < 3) {
            return result;
        }
        int currentPrefixHead = 0;
        int currentPrefixTail = currentPrefixHead + 1;
        String prefix = result.substring(currentPrefixHead, currentPrefixTail);
        while (currentPrefixTail <= result.length()) { // scan while there are parts of the result left
            Map<String, String> prefixTree = MAPPING.prefixMap(prefix);
            while (!prefixTree.isEmpty() && currentPrefixTail <= result.length()) { // while the trie contains the prefix
                if (prefixTree.containsKey(prefix)) { // if match
                    String match = prefixTree.get(prefix);
                    result = result.substring(0, currentPrefixHead) + match + prefix.substring(1) + result.substring(currentPrefixTail);
                    result = result.replace(prefix, match);
                    currentPrefixTail -= prefix.length() - 1; // adjust indices to start after replaced prefix
                    currentPrefixHead = currentPrefixTail;
                }
                currentPrefixTail++;
                if (currentPrefixTail <= result.length()) {
                    prefix = result.substring(currentPrefixHead, currentPrefixTail);
                    prefixTree = MAPPING.prefixMap(prefix);
                }
            }
            currentPrefixHead++; // new prefix starting with next character
            currentPrefixTail = currentPrefixHead + 1;
            if (currentPrefixTail <= result.length()) {
                prefix = result.substring(currentPrefixHead, currentPrefixTail);
            }
        }
        return result;
    }
}
