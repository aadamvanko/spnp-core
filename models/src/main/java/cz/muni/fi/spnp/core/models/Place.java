package cz.muni.fi.spnp.core.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Place {

    private int id;
    private String name;
    private int numberOfTokens;

    protected Set<Arc> inputArcs;
    protected Set<Arc> outputArcs;

    public Place(int id, String name) {
        this(id, name, 0);
    }

    public Place(int id, String name, int numberOfTokens) {
        if (name == null)
            throw new IllegalArgumentException("Name must be set.");

        this.id = id;
        this.name = name;
        this.numberOfTokens = numberOfTokens;

        this.inputArcs = new HashSet<>();
        this.outputArcs = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public void setNumberOfTokens(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
    }

    public void addInputArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        inputArcs.add(arc);
    }

    public void addOutputArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        outputArcs.add(arc);
    }

    public void removeInputArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        inputArcs.remove(arc);
    }

    public void removeOutputArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        outputArcs.remove(arc);
    }

    public Set<Arc> getInputArcs() {
        return Collections.unmodifiableSet(inputArcs);
    }

    public Set<Arc> getOutputArcs() {
        return Collections.unmodifiableSet(outputArcs);
    }
}
