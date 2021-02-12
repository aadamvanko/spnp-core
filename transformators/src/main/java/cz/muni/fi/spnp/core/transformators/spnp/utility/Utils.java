package cz.muni.fi.spnp.core.transformators.spnp.utility;

public class Utils {

    public static String tabify(String text) {
        String[] lines = text.split(System.lineSeparator());
        StringBuilder textWithTabs = new StringBuilder();
        for (String line : lines) {
            textWithTabs.append("\t").append(line).append(System.lineSeparator());
        }
        return textWithTabs.toString();
    }

    public static String newlines(int count) {
        return System.lineSeparator().repeat(count);
    }
}
