package pseudocode.compiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mohammed
 */
public enum Token {
    KEY_INT("int"),
    KEY_DOUBLE("double"),
    KEY_FLOAT("float"),
    KEY_BOOLEAN("Boolean"),
    KEY_CHAR("char"),
    KEY_STRING("String"),
    ASSIGNMENT("<-"),
    OUTPUT_OPERATOR("->"),
    SEPRATOR(","),
    DOT("\\."),
    COLON(":"),
    SEMI_COLON(";"),
    SINGLE_QUOTATION("\\'"),
    DOUBLE_QUOTATION("\""),
    OPENING_PARANTHESES("\\("),
    CLOSEING_PARANTHESES("\\)"),
    OPENING_BRACKET("\\["),
    CLOSEING_BRACKET("\\]"),
    OPENING_CURLY_BRACKET("\\{"),
    CLOSEING_CURLY_BRACKET("\\}"),
    PLUS("\\+"),
    MINUS("-"),
    DIV("/"),
    MUL("\\*"),
    LESSTHAN("<"),
    GREATERTHAN(">"),
    EQUAL("=="),
    NOTEQUAL("!="),
    LESSOREQUAL("<="),
    GREATEROREQUAL(">="),
    AND("and"),
    OR("or"),
    NOT("not"),
    KEY_IF("if"),
    KEY_ELSE("else"),
    KEY_ELSEIF("elseif"),
    KEY_SWITCH("switch"),
    KEY_CASE("case"),
    KEY_DEFAULT("default"),
    KEY_WHILE("while"),
    KEY_DO("do"),
    KEY_FOR("for"),
    KEY_BREAK("break"),
    KEY_CONTINUE("continue"),
    FUNCTION("fun"),
    RETURN("return"),
    BOOLEAN_TRUE("true"),
    BOOLEAN_FALSE("false"),
    KEY_USERINPUT("userinput"),
    KEY_OUTPUT("output"),
    STRING("\"[^\"]+\""),
    INTEGER("\\d+"),
    IDENTIFIER("\\w+"),
    KEY_TO("to");

    private final Pattern pattern;

    Token(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    int endOfMatch(String s) {
        Matcher m = pattern.matcher(s);

        if (m.find()) {
            return m.end();
        }
        return -1;
    }
}
