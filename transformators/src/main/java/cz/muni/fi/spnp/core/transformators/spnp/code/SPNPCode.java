package cz.muni.fi.spnp.core.transformators.spnp.code;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.transformators.spnp.variables.Variable;
import cz.muni.fi.spnp.core.transformators.spnp.variables.VariableType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SPNPCode {

    private final List<Include> includes;
    private final List<Define> defines;
    private final Set<Variable> variables;

    private FunctionSPNP<Integer> assertFunction;
    private FunctionSPNP<Void> acInitFunction;
    private FunctionSPNP<Void> acReachFunction;
    private FunctionSPNP<Void> acFinalFunction;

    public SPNPCode() {
        includes = new ArrayList<>();
        defines = new ArrayList<>();
        variables = new HashSet<>();

        createDefaultRequiredFunctions();
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

    public List<Include> getIncludes() {
        return includes;
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

    public List<Define> getDefines() {
        return defines;
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

    public FunctionSPNP<Void> getAcInitFunction() {
        return acInitFunction;
    }

    public void setAcInitFunction(FunctionSPNP<Void> acInitFunction) {
        if (acInitFunction == null)
            throw new IllegalArgumentException("ac_init() function is not set.");
        if (acInitFunction.getReturnType() != Void.class)
            throw new IllegalArgumentException("ac_init() function has invalid return type.");
        if (!acInitFunction.getName().equals("ac_init"))
            throw new IllegalArgumentException("ac_init() function has incorrect name.");

        this.acInitFunction = acInitFunction;
    }

    public FunctionSPNP<Void> getAcReachFunction() {
        return acReachFunction;
    }

    public void setAcReachFunction(FunctionSPNP<Void> acReachFunction) {
        if (acReachFunction == null)
            throw new IllegalArgumentException("ac_reach() function is not set.");
        if (acReachFunction.getReturnType() != Void.class)
            throw new IllegalArgumentException("ac_reach() function has invalid return type.");
        if (!acReachFunction.getName().equals("ac_reach"))
            throw new IllegalArgumentException("ac_reach() function has incorrect name.");

        this.acReachFunction = acReachFunction;
    }

    public FunctionSPNP<Void> getAcFinalFunction() {
        return acFinalFunction;
    }

    public void setAcFinalFunction(FunctionSPNP<Void> acFinalFunction) {
        if (acFinalFunction == null)
            throw new IllegalArgumentException("ac_final() function is not set.");
        if (acFinalFunction.getReturnType() != Void.class)
            throw new IllegalArgumentException("ac_final() function has invalid return type.");
        if (!acFinalFunction.getName().equals("ac_final"))
            throw new IllegalArgumentException("ac_final() function has incorrect name.");

        this.acFinalFunction = acFinalFunction;
    }

    public FunctionSPNP<Integer> getAssertFunction() {
        return assertFunction;
    }

    public void setAssertFunction(FunctionSPNP<Integer> assertFunction) {
        if (assertFunction == null)
            throw new IllegalArgumentException("Assert function is not set.");
        if (assertFunction.getReturnType() != Integer.class)
            throw new IllegalArgumentException("Assert function has invalid return type.");
        if (!assertFunction.getName().equals("assert"))
            throw new IllegalArgumentException("Assert function has incorrect name.");

        this.assertFunction = assertFunction;
    }

    private void createDefaultRequiredFunctions() {
        assertFunction = new FunctionSPNP<>("assert", FunctionType.Other, "", Integer.class);
        acInitFunction = new FunctionSPNP<>("ac_init", FunctionType.Other,
                "/* Information on the net structure */" + System.lineSeparator() + "pr_net_info();",
                Void.class);
        acReachFunction = new FunctionSPNP<>("ac_reach", FunctionType.Other,
                "/* Information on the reachability graph */" + System.lineSeparator() + "pr_rg_info();",
                Void.class);
        acFinalFunction = new FunctionSPNP<>("ac_final", FunctionType.Other, "", Void.class);
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public Set<Variable> getGlobalVariables() {
        return variables.stream().filter(v -> v.getType() == VariableType.Global).collect(Collectors.toSet());
    }

    public Set<Variable> getParameterVariables() {
        return variables.stream().filter(v -> v.getType() == VariableType.Parameter).collect(Collectors.toSet());
    }

    public Set<Function> getSPNPFunctions() {
        return Set.of(acInitFunction, acFinalFunction, acReachFunction, assertFunction);
    }

}
