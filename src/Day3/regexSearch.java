package Day3;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.regex.Matcher;




public class regexSearch {

    String regexStringWithCapture;
    
    
    
    
    public static void main(String[] args) {
        //System.out.println(useRegex("mul(2,23)"));
        System.out.println(useRegex("mul(2,2e3)", "mul\\([0-9]+,[0-9]+\\)"));
    }
    
    regexSearch(String input, String regexPattern){
            System.out.println(useRegex("mul(2,23)", "mul\\([0-9]+,[0-9]+\\)"));
    }

    regexSearch(String regexPattern){
        //System.out.println(useRegex("mul(2,23)", "mul\\([0-9]+,[0-9]+\\)"));

        setPatternWithCapture(regexPattern);
    }

    
    public void setPatternWithCapture(String regexPattern){
        regexStringWithCapture = regexPattern;
    }   

    public ArrayList<int[]> findCapturedMatches(final String input) {

        ArrayList<int[]> capturedMatches = new ArrayList<>();
        // Compile regular expression
        final Pattern pattern = Pattern.compile(regexStringWithCapture, Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            // Extract the captured groups as strings
            String firstNumberStr = matcher.group(1); // First number as string
            String secondNumberStr = matcher.group(2); // Second number as string

            // Convert to integers
            int firstNumber = Integer.parseInt(firstNumberStr);
            int secondNumber = Integer.parseInt(secondNumberStr);

        
            capturedMatches.add(new int[]{firstNumber, secondNumber}); 
        }

        return capturedMatches;

    }
    public static boolean useRegex(final String input, String regexPattern) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }
}