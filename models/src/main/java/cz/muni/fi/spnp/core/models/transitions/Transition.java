package cz.muni.fi.spnp.core.models.transitions;

import com.google.common.base.Objects;
import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.functions.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Transition {

    private int id;
    private String name;
    private double priority;
    private Function<Integer> guardFunction;

    protected List<Arc> inputArcs;
    protected List<Arc> outputArcs;

    public Transition(int id,
                      String name,
                      double priority,
                      Function<Integer> guardFunction) {
        if (name == null)
            throw new IllegalArgumentException("Name is not defined.");

        this.id = id;
        this.name = name;
        this.priority = priority;
        this.guardFunction = guardFunction;

        this.inputArcs = new ArrayList<>();
        this.outputArcs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name is null.");

        this.name = name;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Function<Integer> getGuardFunction() {
        return guardFunction;
    }

    public void setGuardFunction(Function<Integer> guardFunction) {
        this.guardFunction = guardFunction;
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

    public List<Arc> getInputArcs() {
        return Collections.unmodifiableList(inputArcs);
    }

    public List<Arc> getOutputArcs() {
        return Collections.unmodifiableList(outputArcs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Transition that = (Transition) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
