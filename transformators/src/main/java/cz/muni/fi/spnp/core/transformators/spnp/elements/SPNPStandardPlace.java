package cz.muni.fi.spnp.core.transformators.spnp.elements;

import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.visitors.PlaceVisitor;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.PlaceVisitorSPNP;

public class SPNPStandardPlace extends StandardPlace {

    private String numberOfTokensExpression;

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

    public void setNumberOfTokensExpression(String numberOfTokensExpression) {
        this.numberOfTokensExpression = numberOfTokensExpression;
    }

    @Override
    public void accept(PlaceVisitor placeVisitor) {
        if (placeVisitor instanceof PlaceVisitorSPNP) {
            ((PlaceVisitorSPNP) placeVisitor).visit(this);
        } else {
            super.accept(placeVisitor);
        }
    }
}
