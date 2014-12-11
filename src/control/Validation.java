package control;

/**
 * Created by sdovhtc on 12/10/2014.
 */

public class Validation {

    public static final String GROUP_PATTERN = "^[A-Z0-9][a-z0-9]+(-[A-Z0-9]{0,1}[a-z0-9]+)*$";
    public static final String SUBJECT_PATTERN = "^[A-Z][a-z]+(-[A-Z]{0,1}[a-z]+)*$";
    public static final String FIRST_NAME_PATTERN = "^[A-Z][a-z]+$";
    public static final String LAST_NAME_PATTERN = "^[A-Z][a-z]+(-[A-Z][a-z]+)*$";
    public static final String MARK_PATTERN = "^[1-5]$";
    public static final String ID_PATTERN = "^[0-9]+$";

    public static boolean validate(String inputData, String pattern) {
        return inputData.matches(pattern);
    }

}
