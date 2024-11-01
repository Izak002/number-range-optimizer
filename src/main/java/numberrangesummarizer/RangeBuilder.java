package numberrangesummarizer;

/**
 * Builds a string representation of ranges from a sequence of numbers.
 * For example: Input sequence 1,2,3,5,7,8,9 produces output "1-3, 5, 7-9"
 */
public class RangeBuilder {
    private int start;          // Start of current range
    private int current;        // Last number in current range
    private boolean isBuilding; // Indicates if currently building a range
    private final StringBuilder result; // Stores the final range string
    
    /**
     * Creates a new RangeBuilder instance with empty result
     */
    public RangeBuilder() {
        this.result = new StringBuilder();
        this.isBuilding = false;
    }
    
    /**
     * Processes a number and adds it to the appropriate range
     * @param number The number to process
     */
    public void processNumber(int number) {
        if (!isBuilding) {
            startNewRange(number);
            return;
        }
        
        // If number is sequential, extend range. Otherwise, close and start new
        if (number == current + 1) {
            extendCurrentRange(number);
        } else {
            closeCurrentRange();
            startNewRange(number);
        }
    }
    
    /**
     * Starts a new range with the given number
     */
    private void startNewRange(int number) {
        if (result.length() > 0) {
            result.append(", ");
        }
        start = number;
        current = number;
        isBuilding = true;
    }
    
    /**
     * Extends the current range to include the new number
     */
    private void extendCurrentRange(int number) {
        current = number;
    }
    
    /**
     * Closes the current range and adds it to the result
     */
    private void closeCurrentRange() {
        if (start == current) {
            result.append(start);
        } else {
            result.append(start).append("-").append(current);
        }
    }
    
    /**
     * Builds and returns the final range string
     * @return Formatted string of number ranges
     */
    public String build() {
        if (isBuilding) {
            closeCurrentRange();
            isBuilding = false;
        }
        return result.toString();
    }
}