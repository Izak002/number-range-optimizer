package numberrangesummarizer;
import java.util.*;

/**
 * Implements NumberRangeSummarizer to collect and summarize sequences of integers.
 * Handles string input conversion to numbers and creates range-based summaries.
 * Example: "1,3,6,7,8,12" -> "1, 3, 6-8, 12"
 */
public class UniqueNumberRangeSummarizer implements NumberRangeSummarizer {
    
    /**
     * Converts a comma-separated string of numbers into a sorted collection of unique integers
     * @param input String of comma-separated numbers (e.g., "1,2,3,1,2")
     * @return Collection of unique, sorted integers
     * @throws IllegalArgumentException if input contains invalid numbers or negative values
     */
    @Override
    public Collection<Integer> collect(String input) {
        if (input == null) {
            return Collections.emptyList();
        }
        return processInputString(input);
    }

    /**
     * Processes input string and converts it to a set of unique integers
     * @param input The input string to process
     * @return TreeSet of unique, sorted integers
     */
    private Collection<Integer> processInputString(String input) {
        Set<Integer> uniqueNumbers = new TreeSet<>(); // TreeSet automatically sorts and removes duplicates
        
        for (String numStr : input.split(",")) {
            if (numStr.trim().isEmpty()) {
                continue;
            }
            
            try {
                uniqueNumbers.add(validateAndParse(numStr.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format: " + numStr.trim());
            }
        }
        
        return uniqueNumbers;
    }
    
    /**
     * Validates and parses a string to a non-negative integer
     * @param numStr String to parse
     * @return Parsed integer value
     * @throws IllegalArgumentException if number is negative or invalid
     */
    private int validateAndParse(String numStr) {
        try {
            int num = Integer.parseInt(numStr);
            if (num < 0) {
                throw new IllegalArgumentException("Negative numbers are not supported");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + numStr);
        }
    }

    /**
     * Creates a summary of number ranges from a collection of integers
     * @param input Collection of integers to summarize
     * @return String representing ranges (e.g., "1-3, 5, 7-9")
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return buildRangeSummary(input);
    }
    
    /**
     * Builds a range summary string using RangeBuilder
     * @param numbers Collection of numbers to process
     * @return Formatted string of number ranges
     */
    private String buildRangeSummary(Collection<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        
        RangeBuilder builder = new RangeBuilder();
        
        for (Integer number : sortedNumbers) {
            builder.processNumber(number);
        }
        
        return builder.build();
    }
}