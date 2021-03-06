package ch22.ex22_15.logic.lexer;

import ch22.ex22_15.logic.lexer.attribute.Type;

public class Token {

    private Type type;
    private String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Token [type=" + type + ", text=" + text + "]";
    }

}
