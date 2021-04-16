package cz.muni.fi.spnp.core.models.transitions;

import com.google.common.base.Objects;
import cz.muni.fi.spnp.core.models.ElementBase;
import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.visitors.TransitionVisitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Transition extends ElementBase implements Comparable<Transition> {

    private final int id;
    private String name;
    private int priority;
    private Function guardFunction;

    protected Set<Arc> arcs;

    protected Transition(int id,
                         String name,
                         int priority,
                         Function guardFunction) {
        if (name == null)
            throw new IllegalArgumentException("Name is not defined.");
        if (guardFunction != null && guardFunction.getFunctionType() != FunctionType.Guard)
            throw new IllegalArgumentException("Guard function has incompatible type.");

        this.id = id;
        this.name = name;
        this.priority = priority;
        this.guardFunction = guardFunction;

        this.arcs = new HashSet<>();
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 0)
            throw new IllegalArgumentException("Priority value cannot be negative.");

        this.priority = priority;
    }

    public Function getGuardFunction() {
        return guardFunction;
    }

    public void setGuardFunction(Function guardFunction) {
        if (guardFunction != null && guardFunction.getFunctionType() != FunctionType.Guard)
            throw new IllegalArgumentException("Guard function has incompatible type.");

        this.guardFunction = guardFunction;
    }

    public void addArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        this.arcs.add(arc);
    }

    public void removeArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null");

        this.arcs.remove(arc);
    }

    public Set<Arc> getArcs() {
        return Collections.unmodifiableSet(this.arcs);
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

    public abstract void accept(TransitionVisitor transitionVisitor);

    @Override
    public int compareTo(Transition other) {
        return name.compareTo(other.getName());
    }
}
