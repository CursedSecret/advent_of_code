package one;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Solution to the first challenge
        List<Integer> calibrationData = new CalibrationDataDecoder().processFileFromResourcesPath("one/puzzle_input.txt");
        System.out.println(calibrationData);
        System.out.println(calibrationData.stream()
                .reduce(0, Integer::sum));
    }
}
