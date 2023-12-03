package one;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalibrationDataDecoderTest {

    @Test
    void test() {
        assertEquals(List.of(12, 38, 15, 77, 11, 11), new CalibrationDataDecoder().processFileFromResourcesPath("one/test_1.txt"));
    }
}