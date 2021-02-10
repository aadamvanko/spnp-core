package cz.muni.fi.spnp.core.models;

import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;

import java.util.HashSet;
import java.util.Set;

public class PetriNet {

    private final Set<Arc> arcs;
    private final Set<Place> places;
    private final Set<Transition> transitions;
    private final Set<Function> functions;

    public PetriNet() {
        arcs = new HashSet<>();
        places = new HashSet<>();
        transitions = new HashSet<>();
        functions = new HashSet<>();
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public Set<Arc> getArcs() {
        return arcs;
    }

    public void addArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null.");
        if (this.arcs.contains(arc))
            throw new IllegalArgumentException("Arc is already present in this Petri net.");

        this.arcs.add(arc);
    }

    public void removeArc(Arc arc) {
        if (arc == null)
            throw new IllegalArgumentException("Arc is null.");
        if (!this.arcs.contains(arc))
            throw new IllegalArgumentException("Arc is not present in this Petri net.");

        this.arcs.remove(arc);
    }

    public void addPlace(Place place) {
        if (place == null)
            throw new IllegalArgumentException("Place is null.");
        if (this.places.contains(place))
            throw new IllegalArgumentException("Place is already present in this Petri net.");

        this.places.add(place);
    }

    public void removePlace(Place place) {
        if (place == null)
            throw new IllegalArgumentException("Place is null.");
        if (!this.places.contains(place))
            throw new IllegalArgumentException("Place is not present in this Petri net.");

        this.places.remove(place);
    }

    public void addTransition(Transition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is null");
        if (this.transitions.contains(transition))
            throw new IllegalArgumentException("Transition is already present in this Petri net.");

        this.transitions.add(transition);
    }

    public void removeTransition(Transition transition) {
        if (transition == null)
            throw new IllegalArgumentException("Transition is null.");
        if (!this.transitions.contains(transition))
            throw new IllegalArgumentException("Transition is not present in this Petri net.");

        this.transitions.remove(transition);
    }

    public void addFunction(Function function) {
        if (function == null)
            throw new IllegalArgumentException("Function is null.");
        if (this.functions.contains(function))
            throw new IllegalArgumentException("Function is already present in this Petri net.");

        this.functions.add(function);
    }

    public void removeFunction(Function function) {
        if (function == null)
            throw new IllegalArgumentException("Function is null.");
        if (!this.functions.contains(function))
            throw new IllegalArgumentException("Function is not present in this Petri net.");

        this.functions.remove(function);
    }

//
//    public String getDefinition() {
//        return String.format(
//                "void net() {%n%s%s%n%s%n%s%n%s}%n%n",
//                getParameterVariablesDefinition(),
//                getPlacesDefinition(),
//                getTransitionsDefinition(),
//                getArcsDefinition(),
//                getParameterVariablesBindings());
//    }

//    public String getIncludesDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.includes.forEach(include -> definition.append(include.getDefinition()));
//
//        if (definition.length() > 0)
//            definition.append(System.lineSeparator());
//
//        return definition.toString();
//    }

//    public String getDefinesDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.defines.forEach(define -> definition.append(define.getDefinition()));
//
//        if (definition.length() > 0)
//            definition.append(System.lineSeparator());
//
//        return definition.toString();
//    }

//    public String getVariablesDefinition() {
//        StringBuilder definitions = new StringBuilder();
//
//        // add global variables
//        variables.forEach(variable -> definitions.append(variable.getDefinition()));
//
//        // add input parameters
//        inputParameters.forEach(parameter -> definitions.append(parameter.getDeclaration()));
//
//        if (definitions.length() > 0) {
//            definitions.insert(0, "/*  Variables  */" + System.lineSeparator());
//            definitions.append(System.lineSeparator());
//        }
//
//        return definitions.toString();
//    }
//
//    public String getOptionsDefinition() {
//        StringBuilder definitions = new StringBuilder("void options() {%n");
//
//        // add options
//        this.options.forEach(option -> definitions.append(option.getDefinition()));
//
//        definitions.append("}");
//        definitions.append(System.lineSeparator());
//
//        return definitions.toString();
//    }

//    public String getUserFunctionsDeclarations(FunctionType functionsType) {
//        if (functionsType == null)
//            throw new IllegalArgumentException("Functions type is not defined.");
//
//        return buildFunctionsDeclarations(functionsType);
//    }
//
//    public String getUserFunctionsDefinitions(FunctionType functionsType) {
//        if (functionsType == null)
//            throw new IllegalArgumentException("Functions type is not defined.");
//
//        return buildFunctionsDefinitions(functionsType);
//    }

//    public String getRequiredFunctionsDefinitions() {
//        return String.format("%s%n%s%n%s%n%s%n",
//                             assertFunction.getDefinition(),
//                             acInitFunction.getDefinition(),
//                             acReachFunction.getDefinition(),
//                             acFinalFunction.getDefinition());
//    }

//    private String getPlacesDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.places.forEach(place -> definition.append(place.getDefinition()));
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* ==== PLACES ==== */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String getTransitionsDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        definition.append(getImmediateTransitionsDefinition());
//        definition.append(getTimedTransitionsDefinition());
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* ==== TRANSITIONS ==== */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String getImmediateTransitionsDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.transitions.stream()
//                        .filter(transition -> transition instanceof ImmediateTransition)
//                        .forEach(transition -> definition.append(transition.getDefinition()));
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* Immediate transitions */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String getTimedTransitionsDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.transitions.stream()
//                        .filter(transition -> transition instanceof TimedTransition)
//                        .forEach(transition -> definition.append(transition.getDefinition()));
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* Timed transitions */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String getArcsDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        definition.append(getInputArcsDefinition());
//        definition.append(getOutputArcsDefinition());
//        definition.append(getInhibitorArcsDefinition());
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* ==== ARCS ==== */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String getInputArcsDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.arcs.stream()
//                 .filter(arc -> arc instanceof StandardArc)
//                 .filter(arc -> ((StandardArc) arc).getDirection() == ArcDirection.Input)
//                 .forEach(arc -> definition.append(arc.getDefinition()));
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* Input arcs */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String getOutputArcsDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.arcs.stream()
//                 .filter(arc -> arc instanceof StandardArc)
//                 .filter(arc -> ((StandardArc) arc).getDirection() == ArcDirection.Output)
//                 .forEach(arc -> definition.append(arc.getDefinition()));
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* Output arcs */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String getInhibitorArcsDefinition() {
//        StringBuilder definition = new StringBuilder();
//
//        this.arcs.stream()
//                 .filter(arc -> arc instanceof InhibitorArc)
//                 .forEach(arc -> definition.append(arc.getDefinition()));
//
//        if (definition.length() == 0)
//            return "";
//
//        definition.insert(0, "/* Inhibitor arcs */" + System.lineSeparator());
//
//        return definition.toString();
//    }

//    private String buildFunctionsDeclarations(FunctionType functionsType) {
//        // get functions of specified type
//        var filteredFunctions = functions.stream()
//                                         .filter(function -> function.getFunctionType() == functionsType)
//                                         .collect(Collectors.toSet());
//
//        if (filteredFunctions.isEmpty())
//            return "";
//
//        StringBuilder declarations = new StringBuilder();
//
//        // create common string from all filtered functions
//        filteredFunctions.forEach(function -> declarations.append(function.getDeclaration()));
//
//        return declarations.toString();
//    }

//    private String buildFunctionsDefinitions(FunctionType functionsType) {
//        // get functions of specified type
//        var filteredFunctions = functions.stream()
//                                         .filter(function -> function.getFunctionType() == functionsType)
//                                         .collect(Collectors.toSet());
//
//        if (filteredFunctions.isEmpty())
//            return "";
//
//        StringBuilder definitions = new StringBuilder();
//
//        // add section title
//        definitions.append(getSectionTitleCommentFor(functionsType))
//                   .append(System.lineSeparator()).append(System.lineSeparator());
//
//        // create common string from all filtered functions
//        filteredFunctions.forEach(function -> definitions.append(function.getDefinition()));
//
//        return definitions.toString();
//    }

    private String getSectionTitleCommentFor(FunctionType functionsType) {
        switch (functionsType) {
            case Generic:
                return "/*  C functions  */";
            case Guard:
                return "/*  Guard functions  */";
            case Reward:
                return "/*  Reward functions  */";
            case ArcCardinality:
                return "/*  Cardinality functions  */";
            case Probability:
                return "/*  Probability functions  */";
            case Distribution:
                return "/*  Distribution functions  */";
            case Halting:
                return "/*  Halting functions  */";
            case SPNP:
                return "/*  SPNP functions  */";
            default:
                throw new IllegalArgumentException("Function type is unknown or undefined.");
        }
    }
}
