package cz.muni.fi.spnp.core.transformators.spnp.elements;

public enum PolicyAffectedType {
    PreemptiveRepeatIdentical("PRI"),
    PreemptiveRepeatDifferent("PRD"),
    PreemptiveResume("PRS");

    private final String abbreviation;

    PolicyAffectedType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
