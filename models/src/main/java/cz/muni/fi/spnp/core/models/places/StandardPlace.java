package cz.muni.fi.spnp.core.models.places;

import cz.muni.fi.spnp.core.models.visitors.PlaceVisitor;

public class StandardPlace extends Place {

    private int numberOfTokens;

    public StandardPlace(int id, String name) {
        this(id, name, 0);
    }

    public StandardPlace(int id, String name, int numberOfTokens) {
        super(id, name);

        this.numberOfTokens = numberOfTokens;
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public void setNumberOfTokens(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
    }

    @Override
    public void accept(PlaceVisitor placeVisitor) {
        placeVisitor.visit(this);
    }
}
