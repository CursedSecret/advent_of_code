def input  = new ArrayList()

new File('input-test.txt').eachLine { line ->
    input.add(line)
}

// println input

public class Counter {

    private int currentState

    private int zeroCounter

    public Counter() {
        currentState = 50
    }

    public Counter(int startState) {
        currentState = startState
    }

    public void increment(int integer) {
        currentState += integer
        // println currentState
        if (currentState % 100  == 0) {
            zeroCounter++
        }
    }

}

public class CounterTest {

    public void testCounter() {
        def underTest = new Counter(0)
        assert underTest.zeroCounter == 0

        underTest.increment(100)
        assert underTest.zeroCounter == 1

        underTest.increment(-50)
        assert underTest.zeroCounter == 1
        underTest.increment(-50)
        assert underTest.zeroCounter == 2
    }

}

public class InputConverter {

    public int convertInputStringToNumber(String input) {
        def negative = input.startsWith('L')
        def result = Integer.parseInt(input.substring(1))
        if (negative) {
            result *= -1
        }
        return result
    }

}

public class ConverterTest {

    public void testConverter() {
        def underTest = new InputConverter()
        assert underTest.convertInputStringToNumber('L68') == -68
        assert underTest.convertInputStringToNumber('R67') == 67
    }

}

def counterTest = new CounterTest()
counterTest.testCounter()

def converterTest = new ConverterTest()
converterTest.testConverter()

def counter = new Counter()
def inputConverter = new InputConverter()

input.stream()
.map(inputConverter::convertInputStringToNumber)
.forEach(counter::increment)

def result = counter.zeroCounter

println result
