public class Validator {
    private final String LETTER_COORDS_REGEX = "[abcdefghij]{1}";
    private final String DIRECTION_REGEX = "[hv]{1}";
    
    public Validator() {

    }

    public boolean validateString(String input) {
        return !input.isBlank();
    }

    public boolean validateStringInRange(String input, String regex) {
        return input.matches(regex);
    }

    public boolean isNumeric(String input) {
        return input.matches("[\\d]+");
    }

    public boolean isNumberInRange(int start, int end, int input) {
        return (start <= input && input <= end);
    }

    public boolean checkCoordinates(String[] coordinates) {
        boolean letterCoordinate = validateStringInRange(coordinates[0], this.LETTER_COORDS_REGEX);
        boolean numericCoordinate = false;
        if (isNumeric(coordinates[1])) {
            int tempNumericValue = Integer.parseInt(coordinates[1]);
            if (isNumberInRange(1, 10, tempNumericValue)) {
                numericCoordinate = true;
            }
        } 
        return (letterCoordinate && numericCoordinate);
    }
}