package cz.muni.fi.spnp.core.models;

import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.arcs.ArcDirection;
import cz.muni.fi.spnp.core.models.arcs.InhibitorArc;
import cz.muni.fi.spnp.core.models.arcs.StandardArc;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.ImmediateTransition;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.transitions.distributions.TransitionDistributionType;
import cz.muni.fi.spnp.core.models.transitions.probabilities.FunctionalTransitionProbability;

import java.util.HashSet;
import java.util.Set;

public class PetriNet {

    private Set<Arc> arcs;
    private Set<Place> places;
    private Set<Transition> transitions;

    public PetriNet() {
        arcs = new HashSet<>();
        places = new HashSet<>();
        transitions = new HashSet<>();
    }

    public void addArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null.");
        if (arcs.contains(arc))
            throw new IllegalArgumentException("Arc is already present in this Petri net.");

        this.arcs.add(arc);
    }

    public void removeArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null.");
        if (!arcs.contains(arc))
            throw new IllegalArgumentException("Arc is not present in this Petri net.");

        this.arcs.remove(arc);
    }

    public void addPlace(Place place) {
        if (place == null)
            throw new IllegalArgumentException("Place is null.");
        if (!places.contains(place))
            throw new IllegalArgumentException("Place is already present in this Petri net.");

        this.places.add(place);
    }

    public void removePlace(Place place) {
        if (place == null)
            throw new IllegalArgumentException("Place is null.");
        if (!places.contains(place))
            throw new IllegalArgumentException("Place is not present in this Petri net.");

        this.places.remove(place);
    }

    public void addTransition(Transition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is null");
        if (!transitions.contains(transition))
            throw new IllegalArgumentException("Transition is already present in this Petri net.");

        this.transitions.add(transition);
    }

    public void removeTransition(Transition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is null.");
        if (!transitions.contains(transition))
            throw new IllegalArgumentException("Transition is not present in this Petri net.");

        this.transitions.remove(transition);
    }

    public String getDefinition() {
        return "void net() {"
                + System.lineSeparator()
                + getPlacesDefinition()         // add places
                + System.lineSeparator()
                + getTransitionsDefinition()    // add transitions
                + System.lineSeparator()
                + getArcsDefinition()           // add arcs
                + "}"
                + System.lineSeparator()
                + System.lineSeparator();
    }

    public String getGuardFunctionsDeclarations() {
        StringBuilder declarations = new StringBuilder();

        // get declarations from transitions
        for (var transition : this.transitions) {
            var function = transition.getGuardFunction();
            if (transition.getGuardFunction() != null)
                declarations.append(function.getDeclaration());
        }

        return declarations.toString();
    }

    public String getGuardFunctionsDefinitions() {
        StringBuilder definitions = new StringBuilder();

        // get declarations from transitions
        for (var transition : this.transitions) {
            var function = transition.getGuardFunction();
            if (transition.getGuardFunction() != null)
                definitions.append(function.getDefinition());
        }

        if (definitions.length() == 0)
            return "";

        definitions.insert(0, "/* Guard functions */" + System.lineSeparator());
        definitions.append(System.lineSeparator());

        return definitions.toString();
    }

    public String getCardinalityFunctionsDeclarations() {
        StringBuilder declarations = new StringBuilder();

        // get declarations from arcs
        for (var arc : this.arcs) {
            var function = arc.getCalculateMultiplicityFunction();
            if (function != null) {
                declarations.append(function.getDeclaration());
            }
        }

        return declarations.toString();
    }

    public String getCardinalityFunctionsDefinitions() {
        StringBuilder definitions = new StringBuilder();

        // get declarations from arcs
        for (var arc : this.arcs) {
            var function = arc.getCalculateMultiplicityFunction();
            if (function != null) {
                definitions.append(function.getDefinition());
            }
        }

        if (definitions.length() == 0)
            return "";

        definitions.insert(0, "/* Cardinality functions */" + System.lineSeparator());
        definitions.append(System.lineSeparator());

        return definitions.toString();
    }

    public String getProbabilityFunctionsDeclarations() {
        StringBuilder declarations = new StringBuilder();

        // get declarations from transitions
        for (var transition : this.transitions) {
            if (transition instanceof ImmediateTransition) {
                var immediateTransition = (ImmediateTransition) transition;

                // append functions from probability
                if (immediateTransition.getTransitionProbability() instanceof FunctionalTransitionProbability) {
                    var functionalProbability = (FunctionalTransitionProbability) immediateTransition.getTransitionProbability();

                    declarations.append(functionalProbability.getFunction().getDeclaration());
                }
            }
        }

        return declarations.toString();
    }

    public String getProbabilityFunctionsDefinitions() {
        StringBuilder definitions = new StringBuilder();

        // get definitions from transitions
        for (var transition : this.transitions) {
            if (transition instanceof ImmediateTransition) {
                var immediateTransition = (ImmediateTransition) transition;

                // append functions from probability
                if (immediateTransition.getTransitionProbability() instanceof FunctionalTransitionProbability) {
                    var functionalProbability = (FunctionalTransitionProbability) immediateTransition.getTransitionProbability();

                    definitions.append(functionalProbability.getFunction().getDefinition());
                }
            }
        }

        if (definitions.length() == 0)
            return "";

        definitions.insert(0, "/* Probability functions */" + System.lineSeparator());
        definitions.append(System.lineSeparator());

        return definitions.toString();
    }

    public String getDistributionFunctionsDeclarations() {
        StringBuilder declarations = new StringBuilder();

        // get declarations from transitions
        for (var transition : this.transitions) {
            if (transition instanceof TimedTransition) {
                var timedTransition = (TimedTransition) transition;

                // append functions from distribution
                if (timedTransition.getTransitionDistribution().getDistributionType() == TransitionDistributionType.Functional) {
                    declarations.append(timedTransition.getTransitionDistribution().getFunctionsDeclarations());
                }
            }
        }

        return declarations.toString();
    }

    public String getDistributionFunctionsDefinitions() {
        StringBuilder definitions = new StringBuilder();

        // get definitions from transitions
        for (var transition : this.transitions) {
            if (transition instanceof TimedTransition) {
                var timedTransition = (TimedTransition) transition;

                // append functions from distribution
                if (timedTransition.getTransitionDistribution().getDistributionType() == TransitionDistributionType.Functional) {
                    definitions.append(timedTransition.getTransitionDistribution().getFunctionsDefinitions());
                }
            }
        }

        if (definitions.length() == 0)
            return "";

        definitions.insert(0, "/* Distribution functions */" + System.lineSeparator());
        definitions.append(System.lineSeparator());

        return definitions.toString();
    }


    private String getPlacesDefinition() {
        StringBuilder definition = new StringBuilder();

        this.places.forEach(place -> definition.append(place.getDefinition()));

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* ==== PLACES ==== */" + System.lineSeparator());

        return definition.toString();
    }

    private String getTransitionsDefinition() {
        StringBuilder definition = new StringBuilder();

        definition.append(getImmediateTransitionsDefinition());
        definition.append(getTimedTransitionsDefinition());

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* ==== TRANSITIONS ==== */" + System.lineSeparator());

        return definition.toString();
    }

    private String getImmediateTransitionsDefinition() {
        StringBuilder definition = new StringBuilder();

        this.transitions.stream()
                        .filter(transition -> transition instanceof ImmediateTransition)
                        .forEach(transition -> definition.append(transition.getDefinition()));

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* Immediate transitions */" + System.lineSeparator());

        return definition.toString();
    }

    private String getTimedTransitionsDefinition() {
        StringBuilder definition = new StringBuilder();

        this.transitions.stream()
                        .filter(transition -> transition instanceof TimedTransition)
                        .forEach(transition -> definition.append(transition.getDefinition()));

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* Timed transitions */" + System.lineSeparator());

        return definition.toString();
    }

    private String getArcsDefinition() {
        StringBuilder definition = new StringBuilder();

        definition.append(getInputArcsDefinition());
        definition.append(getOutputArcsDefinition());
        definition.append(getInhibitorArcsDefinition());

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* ==== ARCS ==== */" + System.lineSeparator());

        return definition.toString();
    }

    private String getInputArcsDefinition() {
        StringBuilder definition = new StringBuilder();

        this.arcs.stream()
                 .filter(arc -> arc instanceof StandardArc)
                 .filter(arc -> ((StandardArc) arc).getDirection() == ArcDirection.Input)
                 .forEach(arc -> definition.append(arc.getDefinition()));

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* Input arcs */" + System.lineSeparator());

        return definition.toString();
    }

    private String getOutputArcsDefinition() {
        StringBuilder definition = new StringBuilder();

        this.arcs.stream()
                 .filter(arc -> arc instanceof StandardArc)
                 .filter(arc -> ((StandardArc) arc).getDirection() == ArcDirection.Output)
                 .forEach(arc -> definition.append(arc.getDefinition()));

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* Output arcs */" + System.lineSeparator());

        return definition.toString();
    }

    private String getInhibitorArcsDefinition() {
        StringBuilder definition = new StringBuilder();

        this.arcs.stream()
                 .filter(arc -> arc instanceof InhibitorArc)
                 .forEach(arc -> definition.append(arc.getDefinition()));

        if (definition.length() == 0)
            return "";

        definition.insert(0, "/* Inhibitor arcs */" + System.lineSeparator());

        return definition.toString();
    }
}
