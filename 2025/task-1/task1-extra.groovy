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
        int oldState = currentState
        currentState += integer
        int oldDialState = oldState / 100
        int currentDialState = currentState / 100
        boolean differentSign = differentSign(oldState, currentState)
        int dialDifference = currentDialState - oldDialState
        dialDifference = dialDifference.abs()
        if (differentSign && oldState != 0) {
            dialDifference ++
        }
        zeroCounter += dialDifference
        println """
        Debug info:
        integer            = $integer
        oldState           = $oldState
        oldDialState       = $oldDialState
        currentState       = $currentState
        currentDialState   = $currentDialState
        differentSign      = $differentSign
        dialDifference.    = $dialDifference
        zeroCounter        = $zeroCounter
        """
    }

    private boolean differentSign(int a, int b) {
        return (a ^ b) < 0
    }

}

public class CounterTest {

    public void testCounter() {
        def underTest = new Counter()
        assert underTest.zeroCounter == 0

        underTest.increment(48)
        assert underTest.zeroCounter == 0
        underTest.increment(2)
        assert underTest.zeroCounter == 1
        underTest.increment(-51)
        assert underTest.zeroCounter == 2
        underTest.increment(2)
        assert underTest.zeroCounter == 2
        underTest.increment(-4)
        assert underTest.zeroCounter == 2
        underTest.increment(-51)
        assert underTest.zeroCounter == 3
        underTest.increment(150)
        assert underTest.zeroCounter == 5
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

println '''
|| Starting with the excersize ||
'''

input.stream()
.map(inputConverter::convertInputStringToNumber)
.forEach(counter::increment)

def result = counter.zeroCounter

// assert result == 6
println result
