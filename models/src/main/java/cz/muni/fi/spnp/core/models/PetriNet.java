package cz.muni.fi.spnp.core.models;

import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.models.variables.Variable;
import cz.muni.fi.spnp.core.models.variables.VariableType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PetriNet {

    private final Set<Include> includes;
    private final Set<Define> defines;
    private final Set<Variable> variables;
    private final Set<Arc> arcs;
    private final Set<Place> places;
    private final Set<Transition> transitions;
    private final Set<Function> functions;

    private Function assertFunction;
    private Function acInitFunction;
    private Function acReachFunction;
    private Function acFinalFunction;

    public PetriNet() {
        includes = new HashSet<>();
        defines = new HashSet<>();
        variables = new HashSet<>();
        arcs = new HashSet<>();
        places = new HashSet<>();
        transitions = new HashSet<>();
        functions = new HashSet<>();

        createDefaultRequiredFunctions();
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

    public void addInclude(Include include) {
        if (include == null)
            throw new IllegalArgumentException("Include is null.");
        if (this.includes.contains(include))
            throw new IllegalArgumentException("Include is already present in this Petri net.");

        this.includes.add(include);
    }

    public void removeInclude(Include include) {
        if (include == null)
            throw new IllegalArgumentException("Include is null.");
        if (!this.includes.contains(include))
            throw new IllegalArgumentException("Include is not present in this Petri net.");

        this.includes.remove(include);
    }

    public void addDefine(Define define) {
        if (define == null)
            throw new IllegalArgumentException("Define is null.");
        if (this.defines.contains(define))
            throw new IllegalArgumentException("Define is already present in this Petri net.");

        this.defines.add(define);
    }

    public void removeDefine(Define define) {
        if (define == null)
            throw new IllegalArgumentException("Define is null.");
        if (!this.defines.contains(define))
            throw new IllegalArgumentException("Define is not present in this Petri net.");

        this.defines.remove(define);
    }

    public void addVariable(Variable variable) {
        if (variable == null)
            throw new IllegalArgumentException("Variable is null.");
        if (this.variables.contains(variable))
            throw new IllegalArgumentException("Variable is already present in this Petri net.");

        this.variables.add(variable);
    }

    public void removeVariable(Variable variable) {
        if (variable == null)
            throw new IllegalArgumentException("Variable is null.");
        if (!this.variables.contains(variable))
            throw new IllegalArgumentException("Variable is not present in this Petri net.");

        this.variables.remove(variable);
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

    public Function getAssertFunction() {
        return assertFunction;
    }

    public void setAssertFunction(Function assertFunction) {
        if (assertFunction == null)
            throw new IllegalArgumentException("Assert function is not set.");
        if (assertFunction.getFunctionType() != FunctionType.SPNP)
            throw new IllegalArgumentException("Assert function has invalid type.");
        if (assertFunction.getReturnType() != Integer.class)
            throw new IllegalArgumentException("Assert function has invalid return type.");
        if (!assertFunction.getName().equals("assert"))
            throw new IllegalArgumentException("Assert function has incorrect name.");

        this.assertFunction = assertFunction;
    }

    public Function getAcInitFunction() {
        return acInitFunction;
    }

    public void setAcInitFunction(Function acInitFunction) {
        if (acInitFunction == null)
            throw new IllegalArgumentException("ac_init() function is not set.");
        if (acInitFunction.getFunctionType() != FunctionType.SPNP)
            throw new IllegalArgumentException("ac_init() function has invalid type.");
        if (acInitFunction.getReturnType() != Void.class)
            throw new IllegalArgumentException("ac_init() function has invalid return type.");
        if (!acInitFunction.getName().equals("ac_init"))
            throw new IllegalArgumentException("ac_init() function has incorrect name.");

        this.acInitFunction = acInitFunction;
    }

    public Function getAcReachFunction() {
        return acReachFunction;
    }

    public void setAcReachFunction(Function acReachFunction) {
        if (acReachFunction == null)
            throw new IllegalArgumentException("ac_reach() function is not set.");
        if (acReachFunction.getFunctionType() != FunctionType.SPNP)
            throw new IllegalArgumentException("ac_reach() function has invalid type.");
        if (acReachFunction.getReturnType() != Void.class)
            throw new IllegalArgumentException("ac_reach() function has invalid return type.");
        if (!acReachFunction.getName().equals("ac_reach"))
            throw new IllegalArgumentException("ac_reach() function has incorrect name.");

        this.acReachFunction = acReachFunction;
    }

    public Function getAcFinalFunction() {
        return acFinalFunction;
    }

    public void setAcFinalFunction(Function acFinalFunction) {
        if (acFinalFunction == null)
            throw new IllegalArgumentException("ac_final() function is not set.");
        if (acFinalFunction.getFunctionType() != FunctionType.SPNP)
            throw new IllegalArgumentException("ac_final() function has invalid type.");
        if (acFinalFunction.getReturnType() != Void.class)
            throw new IllegalArgumentException("ac_final() function has invalid return type.");
        if (!acFinalFunction.getName().equals("ac_final"))
            throw new IllegalArgumentException("ac_final() function has incorrect name.");

        this.acFinalFunction = acFinalFunction;
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

    public String getIncludesDefinition() {
        StringBuilder definition = new StringBuilder();

        this.includes.forEach(include -> definition.append(include.getDefinition()));

        if (definition.length() > 0)
            definition.append(System.lineSeparator());

        return definition.toString();
    }

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

    public String getUserFunctionsDeclarations(FunctionType functionsType) {
        if (functionsType == null)
            throw new IllegalArgumentException("Functions type is not defined.");

        return buildFunctionsDeclarations(functionsType);
    }

    public String getUserFunctionsDefinitions(FunctionType functionsType) {
        if (functionsType == null)
            throw new IllegalArgumentException("Functions type is not defined.");

        return buildFunctionsDefinitions(functionsType);
    }

    public String getRequiredFunctionsDefinitions() {
        return String.format("%s%n%s%n%s%n%s%n",
                             assertFunction.getDefinition(),
                             acInitFunction.getDefinition(),
                             acReachFunction.getDefinition(),
                             acFinalFunction.getDefinition());
    }

    private String getParameterVariablesDefinition() {
        // get parameter variables
        var parameterVariables = variables.stream()
                                          .filter(variable -> variable.getType() == VariableType.Parameter)
                                          .collect(Collectors.toSet());

        if (parameterVariables.isEmpty())
            return "";

        StringBuilder definitions = new StringBuilder("/* ==== PARAMETER VARIABLES ==== */" + System.lineSeparator());

        // create common string with all definitions
        parameterVariables.forEach(variable -> definitions.append(String.format("parm(\"%s\");%n", variable.getName())));

        definitions.append(System.lineSeparator());

        return definitions.toString();
    }

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

    private String getParameterVariablesBindings() {
        // get parameter variables
        var parameterVariables = variables.stream()
                .filter(variable -> variable.getType() == VariableType.Parameter)
                .collect(Collectors.toSet());

        if (parameterVariables.isEmpty())
            return "";

        StringBuilder definitions = new StringBuilder("/* ==== PARAMETER VARIABLES BINDINGS ==== */" + System.lineSeparator());

        // create common string with all definitions
        parameterVariables.forEach(variable -> definitions.append(String.format("bind(\"%s\", %s);%n",
                                                                            variable.getName(), variable.getName())));

        definitions.append(System.lineSeparator());

        return definitions.toString();
    }

    private String buildFunctionsDeclarations(FunctionType functionsType) {
        // get functions of specified type
        var filteredFunctions = functions.stream()
                                         .filter(function -> function.getFunctionType() == functionsType)
                                         .collect(Collectors.toSet());

        if (filteredFunctions.isEmpty())
            return "";

        StringBuilder declarations = new StringBuilder();

        // create common string from all filtered functions
        filteredFunctions.forEach(function -> declarations.append(function.getDeclaration()));

        return declarations.toString();
    }

    private String buildFunctionsDefinitions(FunctionType functionsType) {
        // get functions of specified type
        var filteredFunctions = functions.stream()
                                         .filter(function -> function.getFunctionType() == functionsType)
                                         .collect(Collectors.toSet());

        if (filteredFunctions.isEmpty())
            return "";

        StringBuilder definitions = new StringBuilder();

        // add section title
        definitions.append(getSectionTitleCommentFor(functionsType))
                   .append(System.lineSeparator()).append(System.lineSeparator());

        // create common string from all filtered functions
        filteredFunctions.forEach(function -> definitions.append(function.getDefinition()));

        return definitions.toString();
    }

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

    private void createDefaultRequiredFunctions() {
        assertFunction = new Function<>("assert", FunctionType.SPNP, "", Integer.class);
        acInitFunction = new Function<>("ac_init", FunctionType.SPNP,
                                        "/* Information on the net structure */" + System.lineSeparator() + "pr_net_info();",
                                        Void.class);
        acReachFunction = new Function<>("ac_reach", FunctionType.SPNP,
                                         "/* Information on the reachability graph */" + System.lineSeparator() + "pr_rg_info();",
                                         Void.class);
        acFinalFunction = new Function<>("ac_final", FunctionType.SPNP, "", Void.class);
    }
}
