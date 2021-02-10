package cz.muni.fi.spnp.core.transformators.spnp.code;

import cz.muni.fi.spnp.core.models.functions.Function;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.variables.Variable;
import cz.muni.fi.spnp.core.models.variables.VariableType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SPNPCode {

    private final Set<Include> includes;
    private final Set<Define> defines;
    private final Set<Variable> variables;

    private Function<Integer> assertFunction;
    private Function<Void> acInitFunction;
    private Function<Void> acReachFunction;
    private Function<Void> acFinalFunction;

    public SPNPCode() {
        includes = new HashSet<>();
        defines = new HashSet<>();
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

    public Function<Void> getAcInitFunction() {
        return acInitFunction;
    }

    public void setAcInitFunction(Function<Void> acInitFunction) {
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

    public Function<Void> getAcReachFunction() {
        return acReachFunction;
    }

    public void setAcReachFunction(Function<Void> acReachFunction) {
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

    public Function<Void> getAcFinalFunction() {
        return acFinalFunction;
    }

    public void setAcFinalFunction(Function<Void> acFinalFunction) {
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

    public Function<Integer> getAssertFunction() {
        return assertFunction;
    }

    public void setAssertFunction(Function<Integer> assertFunction) {
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

    public Set<Variable> getVariables() {
        return variables;
    }

    public Set<Variable> getGlobalVariables() {
        return variables.stream().filter(v -> v.getType() == VariableType.Global).collect(Collectors.toSet());
    }

    public Set<Variable> getParameterVariables() {
        return variables.stream().filter(v -> v.getType() == VariableType.Parameter).collect(Collectors.toSet());
    }
}
