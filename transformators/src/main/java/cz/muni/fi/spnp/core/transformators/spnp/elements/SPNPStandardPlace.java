package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.places.StandardPlace;

public class SPNPStandardPlace extends StandardPlace {

    private final String numberOfTokensExpression;

    public SPNPStandardPlace(int id, String name) {
        this(id, name, "0");
    }

    public SPNPStandardPlace(int id, String name, String numberOfTokensExpression) {
        super(id, name);

        this.numberOfTokensExpression = numberOfTokensExpression;
    }

    public String getNumberOfTokensExpression() {
        return numberOfTokensExpression;
    }
}
