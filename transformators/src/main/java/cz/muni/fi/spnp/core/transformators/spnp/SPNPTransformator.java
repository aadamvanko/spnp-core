package cz.muni.fi.spnp.core.transformators.spnp;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.transformators.Transformator;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.code.SPNPCode;
import cz.muni.fi.spnp.core.transformators.spnp.options.SPNPOptions;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.*;

import java.util.stream.Collectors;

import static cz.muni.fi.spnp.core.transformators.spnp.utility.Utils.newlines;
import static cz.muni.fi.spnp.core.transformators.spnp.utility.Utils.tabify;

public class SPNPTransformator implements Transformator {

    private final SPNPCode spnpCode;
    private final SPNPOptions spnpOptions;

    public SPNPTransformator(SPNPCode spnpCode, SPNPOptions spnpOptions) {
        this.spnpCode = spnpCode;
        this.spnpOptions = spnpOptions;
    }

    @Override
    public String transform(PetriNet petriNet) {
        String source = generateIncludes() + newlines(1) +
                generateDefines() + newlines(1) +
                generateGlobalVariables() + newlines(1) +
                generateGlobalFunctions(petriNet) + newlines(1) +
                generateOptions() + newlines(2) +
                generateNet(petriNet) + newlines(2) +
                generateAssert() + newlines(1) +
                generateAcInit() + newlines(1) +
                generateAcReach() + newlines(1) +
                generateAcFinal();
        return source;
    }

    private String generateIncludes() {
        var includeVisitorImpl = new IncludeVisitorImpl();
        spnpCode.getIncludes().forEach(include -> include.accept(includeVisitorImpl));
        return String.format("/* Includes */%n%s", includeVisitorImpl.getResult());
    }

    private String generateDefines() {
        var defineVisitorImpl = new DefineVisitorImpl();
        spnpCode.getDefines().forEach(define -> define.accept(defineVisitorImpl));
        return String.format("/* Defines */%n%s", defineVisitorImpl.getResult());
    }

    private String generateGlobalVariables() {
        return String.format("/* Global variables for general use */%n%s%n", globalVariablesDefinition()) +
                String.format("/* Global variables for SPNP parameters */%n%s%n", SPNPParametersVariablesDefinition()) +
                String.format("/* Global variables for input parameters */%n%s", inputParametersDeclaration());
    }

    private String globalVariablesDefinition() {
        var variableVisitorImpl = new VariableVisitorImpl();
        var sortedVariables = spnpCode.getGlobalVariables().stream().sorted().collect(Collectors.toList());
        sortedVariables.forEach(variable -> variable.accept(variableVisitorImpl));
        return variableVisitorImpl.getResult();
    }

    private String SPNPParametersVariablesDefinition() {
        var variableVisitorImpl = new VariableVisitorImpl();
        var sortedVariables = spnpCode.getParameterVariables().stream().sorted().collect(Collectors.toList());
        sortedVariables.forEach(variable -> variable.accept(variableVisitorImpl));
        return variableVisitorImpl.getResult();
    }

    private String inputParametersDeclaration() {
        var inputParamaterDeclarationVisitor = new InputParamaterDeclarationVisitorImpl();
        var sortedInputParameters = spnpOptions.getInputParameters().stream().sorted().collect(Collectors.toList());
        sortedInputParameters.forEach(inputParameter -> inputParameter.accept(inputParamaterDeclarationVisitor));
        return inputParamaterDeclarationVisitor.getResult();
    }

    private String generateGlobalFunctions(PetriNet petriNet) {
        return String.format("/* Global functions declarations */%n%s%n", globalFunctionsDeclarations(petriNet)) +
                String.format("/* Global functions definitions */%n%s", globalFunctionsDefinitions(petriNet));
    }

    private String globalFunctionsDeclarations(PetriNet petriNet) {
        var functionDeclarationVisitorImpl = new FunctionDeclarationVisitorImpl();
        var sortedFunctions = petriNet.getFunctions().values().stream().sorted().collect(Collectors.toList());
        sortedFunctions.forEach(function -> function.accept(functionDeclarationVisitorImpl));
        return functionDeclarationVisitorImpl.getResult();
    }

    private String globalFunctionsDefinitions(PetriNet petriNet) {
        var functionDefinitionVisitorImpl = new FunctionDefinitionVisitorImpl();
        var sortedFunctions = petriNet.getFunctions().values().stream().sorted().collect(Collectors.toList());
        sortedFunctions.forEach(function -> function.accept(functionDefinitionVisitorImpl));
        return functionDefinitionVisitorImpl.getResult();
    }

    private String generateOptions() {
        var optionVisitor = new OptionVisitor();
        var sortedOptions = spnpOptions.getOptions().stream().sorted().collect(Collectors.toList());
        sortedOptions.forEach(option -> option.accept(optionVisitor));
        String optionsDefinition = String.format("/* Options */%n%s", optionVisitor.getResult());
        return String.format("void options() {%n%s%n%s}", tabify(optionsDefinition), tabify(inputParametersInitialization()));
    }

    private String inputParametersInitialization() {
        var inputParameterInitializationVisitor = new InputParameterInitializationVisitorImpl();
        var sortedInputParameters = spnpOptions.getInputParameters().stream().sorted().collect(Collectors.toList());
        sortedInputParameters.forEach(inputParameter -> inputParameter.accept(inputParameterInitializationVisitor));
        return String.format("/* Input parameters initialization */%n%s", inputParameterInitializationVisitor.getResult());
    }

    private String generateNet(PetriNet petriNet) {
        String netDefinition = "void net() {" + System.lineSeparator() +
                tabify(SPNPParametersCreation()) + newlines(1) +
                tabify(placesDefinition(petriNet)) + newlines(1) +
                tabify(transitionsDefinition(petriNet)) + newlines(1) +
                tabify(arcsDefinition(petriNet)) + newlines(1) +
                tabify(SPNPParametersBindings()) + newlines(1) +
                tabify(haltingConditions(petriNet)) +
                "}";
        return netDefinition;
    }

    private String placesDefinition(PetriNet petriNet) {
        var placeVisitorImpl = new PlaceVisitorImpl();
        var sortedPlaces = petriNet.getPlaces().stream().sorted().collect(Collectors.toList());
        sortedPlaces.forEach(place -> place.accept(placeVisitorImpl));
        return String.format("/* Places */%n%s", placeVisitorImpl.getResult());
    }

    private String transitionsDefinition(PetriNet petriNet) {
        var transitionVisitorImpl = new TransitionVisitorImpl();
        var sortedTransitions = petriNet.getTransitions().stream().sorted().collect(Collectors.toList());
        sortedTransitions.forEach(transition -> transition.accept(transitionVisitorImpl));
        return String.format("/* Transitions */%n%s", transitionVisitorImpl.getResult());
    }

    private String arcsDefinition(PetriNet petriNet) {
        var arcVisitorImpl = new ArcVisitorImpl();
        var sortedArcs = petriNet.getArcs().stream().sorted().collect(Collectors.toList());
        sortedArcs.forEach(arc -> arc.accept(arcVisitorImpl));
        return String.format("/* Arcs */%n%s", arcVisitorImpl.getResult());
    }

    private String generateAssert() {
        return generateFunction(spnpCode.getAssertFunction());
    }

    private String generateAcInit() {
        return generateFunction(spnpCode.getAcInitFunction());
    }

    private String generateAcReach() {
        return generateFunction(spnpCode.getAcReachFunction());
    }

    private String generateAcFinal() {
        return generateFunction(spnpCode.getAcFinalFunction());
    }

    private String generateFunction(FunctionSPNP<?> function) {
        var functionDefinitionVisitorImpl = new FunctionDefinitionVisitorImpl();
        function.accept(functionDefinitionVisitorImpl);
        return functionDefinitionVisitorImpl.getResult();
    }

    private String SPNPParametersCreation() {
        StringBuilder definitions = new StringBuilder();
        spnpCode.getParameterVariables()
                .forEach(variable -> definitions.append(String.format("parm(\"%s\");%n", variable.getName())));
        return String.format("/* SPNP parameters creation */%n%s", definitions);
    }

    private String SPNPParametersBindings() {
        StringBuilder definitions = new StringBuilder();
        spnpCode.getParameterVariables()
                .forEach(variable -> definitions.append(String.format("bind(\"%s\", %s);%n", variable.getName(), variable.getName())));
        return String.format("/* SPNP parameters bindings */%n%s", definitions);
    }

    private String haltingConditions(PetriNet petriNet) {
        StringBuilder conditions = new StringBuilder();
        petriNet.getFunctions().values().forEach(function -> {
            if(function.getFunctionType() == FunctionType.Halting){
                conditions.append(String.format("halting_condition(%s);%n", function.getName()));
            }
        });
        return String.format("/* Halting conditions */%n%s", conditions);
    }

}
