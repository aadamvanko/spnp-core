package cz.muni.fi.spnp.core.models;

public class Commentary {

    private final String text;

    public Commentary(String text) {
        this.text = text;
    }

    public String getLineCommentary() {
        if (text == null || text.isBlank()) {
            return "";
        }

        return String.format("// %s%n", text.replace(System.lineSeparator(), ";"));
    }

    public String getMultiLineCommentary() {
        if (text == null || text.isBlank()) {
            return "";
        }

        return String.format("/*%n%s%n*/%n", text.replace("*/", "* /"));
    }
}
