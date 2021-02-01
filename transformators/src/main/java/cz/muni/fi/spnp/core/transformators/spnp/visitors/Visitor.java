package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import java.math.BigDecimal;
import java.util.Locale;

public class Visitor {

    protected StringBuilder stringBuilder = new StringBuilder();

    public String getResult() {
        return stringBuilder.toString();
    }

    protected String formatDouble(Double d) {
        String localizedNumber = String.format(Locale.US, "%f", d);
        String withoutTrailingZeros = new BigDecimal(localizedNumber).stripTrailingZeros().toPlainString();
        if (!withoutTrailingZeros.contains(".")) {
            withoutTrailingZeros += ".0";
        }
        return withoutTrailingZeros;
    }

}
