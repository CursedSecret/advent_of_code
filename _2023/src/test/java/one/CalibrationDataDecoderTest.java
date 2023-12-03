package one;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalibrationDataDecoderTest {

    @Test
    void testOne() {
        assertEquals(List.of(12, 38, 15, 77, 11, 11), new CalibrationDataDecoder().processFileFromResourcesPath("one/test1.txt"));
    }

    @Test
    void testTwo() {
        assertEquals(List.of(29, 83, 83, 13, 24, 42, 14, 18, 76, 79, 11, 11, 11, 22, 66, 15, 22, 62), new DirtyDataEncoder().processFileFromResourcesPath("one/test2.txt"));
    }
}