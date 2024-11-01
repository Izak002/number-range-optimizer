# Number Range Summarizer

A Java implementation that collects numbers and returns a summarized string of ranges.

## Overview
This project provides a solution for converting a collection of numbers into a summarized string of ranges. For example, the string "1,3,6,7,8,12" will be converted to "1, 3, 6-8, 12".

## Assumptions Made
When developing this solution, several real-world scenarios were considered:

1. **Unordered Input**
   - Numbers might not come in sequential order
   - Input like "3,1,7,2,6" should still work correctly
   - Solution uses TreeSet to automatically sort numbers

2. **Data Quality Issues**
   - Duplicate numbers may exist in the input
   - Solution removes duplicates automatically using Set
   - Example: "1,1,1,2,2,3" → "1-3"

3. **Invalid Input Handling**
   - Input might contain non-numeric values ("one", "two")
   - Negative numbers might be provided
   - Empty or null input is possible
   - Whitespace variations in the input
   - Solution includes robust error handling for these cases

## Key Features
- Removes duplicates automatically
- Sorts numbers before processing
- Handles whitespace in input
- Validates input for non-numeric values
- Rejects negative numbers
- Returns empty result for null/empty input
- Creates proper ranges for consecutive numbers

## Example Usage
```java
NumberRangeSummarizer summarizer = new UniqueNumberRangeSummarizer();

// Basic usage
String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
Collection<Integer> numbers = summarizer.collect(input);
String result = summarizer.summarizeCollection(numbers);
// Result: "1, 3, 6-8, 12-15, 21-24, 31"
