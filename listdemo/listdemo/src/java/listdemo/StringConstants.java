/*
 * This interface contains constants for almost all strings used in the
 * applcation. This has several benefits compared to writing the strings
 * into the code, e.g.:
 * When you get the request parameters in a servlet, the parameter names
 * are Strings, and these strings occur in several places. If you misspell
 * the name once, the application will not work. If you define a constant
 * for the parameter name, the compiler will catch typos: If you type ADRESS
 * instead of ADDRESS, you will get an error message from the compiler
 *
 */
package listdemo;


/**
 *
 * @author evenal
 */
public interface StringConstants
{
    //
    // Request parameters
    /////////////////////
    String ID = "id";
    String NAME = "name";
    String ADDRESS = "address";
    String EXTRA = "extra";

    //
    // REquest attributes
    /////////////////////
    String STUDENT_DB = "studentdb";

    //
    // html patterns for two column (prompt+textfield forms
    ///////////////////////////////////////////////////////
    String ID_FIELD
            = "<tr>" +
            "<td>%s:</td>" +
            "<td><input type='text' readonly name='%s' value='%d'/></td>" +
            "</tr>\n";
    String TEXT_FIELD
            = "<tr>" +
            "<td>%s:</td>" +
            "<td><input type='text' name='%s' value='%s'/>" +
            "</td>" +
            "</tr>\n";
    String START_BUTTON_ROW = "<tr><td colspan='2'>";
    String BUTTON = "<button type='submit'>%s</button>\n";
    String BUTTON_FORMACTION
            = "<button type='submit' formaction='%s'>%s</button>";
    String END_BUTTON_ROW = "</td></tr>";

    //
    // Standard Button values
    /////////////////////////
    String SAVE = "save";
    String DELETE = "delete";
    String CANCEL = "cancel";
}
