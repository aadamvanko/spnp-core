package cz.muni.fi.spnp.core.models.transitions;

import com.google.common.base.Objects;
import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.functions.Function;

import java.util.*;

public abstract class Transition {

    private int id;
    private String name;
    private int priority;
    private Function<Integer> guardFunction;

    protected Set<Arc> arcs;

    protected Transition(int id,
                         String name,
                         int priority,
                         Function<Integer> guardFunction) {
        if (name == null)
            throw new IllegalArgumentException("Name is not defined.");

        this.id = id;
        this.name = name;
        this.priority = priority;
        this.guardFunction = guardFunction;

        this.arcs = new HashSet<>();
    }

    /**
     * Gets the {@link String} representation of the transition and its parameters.
     *
     * @return  representation of the transition and its parameters
     */
    public abstract String getDefinition();

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

    public Function<Integer> getGuardFunction() {
        return guardFunction;
    }

    public void setGuardFunction(Function<Integer> guardFunction) {
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
}
