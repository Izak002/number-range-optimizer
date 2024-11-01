package numberrangesummarizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;

/**
 * Test suite for NumberRangeSummarizer implementation.
 * Tests various scenarios including normal cases, edge cases, and error conditions.
 */
class NumberRangeSummarizerTest {
    private NumberRangeSummarizer summarizer;

    /**
     * Sets up a fresh instance of NumberRangeSummarizer before each test
     */
    @BeforeEach
    void setUp() {
        summarizer = new UniqueNumberRangeSummarizer();
    }

    /**
     * Tests normal case with multiple ranges and individual numbers
     * Input: "1,3,6,7,8,12,13,14,15,21,22,23,24,31"
     * Expected: "1, 3, 6-8, 12-15, 21-24, 31"
     */
    @Test
    void testNormalCase() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", result,
                "Expected grouped ranges (e.g., 6-8) for normal input, but got a different result.");
    }

    /**
     * Tests handling of duplicate numbers in input
     * Input: "1,1,1,2,2,3,4,5,5"
     * Expected: "1-5"
     */
    @Test
    void testWithDuplicates() {
        String input = "1,1,1,2,2,3,4,5,5";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1-5", result,
                "Duplicate values should be ignored; expected range 1-5, but result did not match.");
    }

    /**
     * Tests mixed sequences of consecutive and non-consecutive numbers
     * Input: "2,5,8,9,10,11,14"
     * Expected: "2, 5, 8-11, 14"
     */
    @Test
    void testMixedSequences() {
        String input = "2,5,8,9,10,11,14";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("2, 5, 8-11, 14", result,
                "Expected mixed sequence handling (e.g., 8-11) to group ranges appropriately.");
    }

    /**
     * Tests input with various whitespace patterns
     * Input: "1, 2, 3,    4,5,     6"
     * Expected: "1-6"
     */
    @Test
    void testWithWhitespace() {
        String input = "1, 2, 3,    4,5,     6";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1-6", result,
                "Whitespace should be ignored; expected range 1-6, but result did not match.");
    }

    /**
     * Tests empty string input
     * Expected: Empty collection
     */
    @Test
    void testEmptyString() {
        String input = "";
        Collection<Integer> numbers = summarizer.collect(input);
        assertTrue(numbers.isEmpty(),
                "Empty input string should return an empty collection, but collection was not empty.");
    }

    /**
     * Tests input with a single number
     * Input: "1"
     * Expected: "1"
     */
    @Test
    void testSingleNumber() {
        String input = "1";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1", result,
                "Single number input should return the same number as result, but result did not match.");
    }

    /**
     * Tests input with sequential numbers
     * Input: "1,2,3,4,5"
     * Expected: "1-5"
     */
    @Test
    void testSequentialNumbers() {
        String input = "1,2,3,4,5";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1-5", result,
                "Expected a single range 1-5 for sequential input, but result did not match.");
    }

    /**
     * Tests handling of invalid number format
     * Input: "1,2,three,4"
     * Expected: IllegalArgumentException
     */
    @Test
    void testInvalidNumber() {
        String input = "1,2,three,4";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(input));
        assertEquals("Invalid number: three", exception.getMessage(),
                "Expected IllegalArgumentException for invalid number 'three', but received a different exception.");
    }

    /**
     * Tests handling of negative numbers
     * Input: "1,2,-3,4"
     * Expected: IllegalArgumentException
     */
    @Test
    void testNegativeNumber() {
        String input = "1,2,-3,4";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(input));
        assertEquals("Negative numbers are not supported", exception.getMessage(),
                "Expected IllegalArgumentException for negative number support, but received a different exception.");
    }
}