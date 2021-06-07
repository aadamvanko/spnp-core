# Petri Net model in Java

This library is designed to allow easy creation of the Petri nets in the object-oriented way. It provides the general model of the Petri nets and also the extended model representation for a package/tool **SPNP** (Stochastic Petri Net Package). General model can be found in **cz.muni.fi.spnp.core** and SPNP model representation can be found in **cz.muni.fi.spnp.core.transformators.spnp**.

In addition to these two models, this library contains also the transformator for the SPNP model representation, which generates C code for the given model.

### Problems/Bugs

All of the known problems we are aware of can be found in the issues. Do not hesitate to create new issue if you have found a bug (thank you).

### Plans for the future

Issues contain also the missing features from the SPNP which are currently not implemented. We are also working on an GUI editor, which will be also made publicly available.

### Usage

Core part of the library contains a general model for the Petri nets and is quite compact. This general model can be further extended for more feature rich nets like the one needed for the SPNP model representation. In order to convert the model into a textual representation implement interface **Transformator** which contains just a single method **transform**. You can take a look at the **SPNPTransformator** which implements this interface for the SPNP model representation and returns string containing the C code.

This example demonstrates how to use classes to create the model for the first example (example adapted from M.K. Molloy’s IEEE TC paper) found in SPNP Manual 6.0 and transform it to the C code (which is used to describe the model in the SPNP).

```
import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.models.arcs.ArcDirection;
import cz.muni.fi.spnp.core.models.arcs.StandardArc;
import cz.muni.fi.spnp.core.models.functions.FunctionType;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
import cz.muni.fi.spnp.core.models.transitions.TimedTransition;
import cz.muni.fi.spnp.core.transformators.spnp.code.Define;
import cz.muni.fi.spnp.core.transformators.spnp.code.FunctionSPNP;
import cz.muni.fi.spnp.core.transformators.spnp.code.Include;
import cz.muni.fi.spnp.core.transformators.spnp.code.SPNPCode;
import cz.muni.fi.spnp.core.transformators.spnp.distributions.ExponentialTransitionDistribution;
import cz.muni.fi.spnp.core.transformators.spnp.options.*;
import cz.muni.fi.spnp.core.transformators.spnp.parameters.InputParameter;
import cz.muni.fi.spnp.core.transformators.spnp.variables.DoubleVariable;
import cz.muni.fi.spnp.core.transformators.spnp.variables.IntegerVariable;
import cz.muni.fi.spnp.core.transformators.spnp.variables.VariableType;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // Molloy Example from SPNP manual

        Set<InputParameter> inputParameters = Set.of();

        Set<Option> options = Set.of(
                new ConstantTypeOption(OptionKey.IOP_SSMETHOD, ConstantValue.VAL_GASEI),
                new ConstantTypeOption(OptionKey.IOP_PR_FULL_MARK, ConstantValue.VAL_YES),
                new ConstantTypeOption(OptionKey.IOP_PR_MARK_ORDER, ConstantValue.VAL_CANONIC),
                new ConstantTypeOption(OptionKey.IOP_PR_MC_ORDER, ConstantValue.VAL_TOFROM),
                new ConstantTypeOption(OptionKey.IOP_PR_MC, ConstantValue.VAL_YES),
                new ConstantTypeOption(OptionKey.IOP_PR_PROB, ConstantValue.VAL_YES),
                new ConstantTypeOption(OptionKey.IOP_MC, ConstantValue.VAL_CTMC),
                new ConstantTypeOption(OptionKey.IOP_PR_RSET, ConstantValue.VAL_YES),
                new ConstantTypeOption(OptionKey.IOP_PR_RGRAPH, ConstantValue.VAL_YES),
                new IntegerTypeOption(OptionKey.IOP_ITERATIONS, 20000),
                new DoubleTypeOption(OptionKey.FOP_ABS_RET_M0, 0.0)
        );

        SPNPOptions spnpOptions = new SPNPOptions(inputParameters, options);

        SPNPCode spnpCode = new SPNPCode();

        spnpCode.addInclude(new Include("\"user.h\""));
        /*
        // usage of includes
        spnpCode.addInclude(new Include("<math.h>")); // standard header
        spnpCode.addInclude(new Include("\"../myLocalHeaders/header.h\"")); // local header
        */

        /*
        // usage of defines
        spnpCode.addDefine(new Define("DEFINED_INTEGER", "12345")); // integer value
        spnpCode.addDefine(new Define("DEFINED_FLOAT", "1.2345")); // floating point value
        spnpCode.addDefine(new Define("DEFINED_STRING", "\"abcdef\"")); // string value
        */

        /*
        // usage of variables
        spnpCode.addVariable(new IntegerVariable("myIntVariable", VariableType.Global, 123)); // global variable
        spnpCode.addVariable(new DoubleVariable("myDoubleVariable", VariableType.Parameter, 1.23)); // parameter variable
        */

        SPNPTransformator spnpTransformator = new SPNPTransformator(spnpCode, spnpOptions);

        PetriNet petriNet = new PetriNet();
        var p0 = new StandardPlace(0, "p0", 1);
        petriNet.addPlace(p0);
        var p1 = new StandardPlace(1, "p1");
        petriNet.addPlace(p1);
        var p2 = new StandardPlace(2, "p2");
        petriNet.addPlace(p2);
        var p3 = new StandardPlace(3, "p3");
        petriNet.addPlace(p3);
        var p4 = new StandardPlace(4, "p4");
        petriNet.addPlace(p4);

        var t0 = new TimedTransition(0, "t0", new ExponentialTransitionDistribution(1.0));
        petriNet.addTransition(t0);
        var t1 = new TimedTransition(1, "t1", new ExponentialTransitionDistribution(3.0));
        petriNet.addTransition(t1);
        var t2 = new TimedTransition(2, "t2", new ExponentialTransitionDistribution(7.0));
        petriNet.addTransition(t2);
        var t3 = new TimedTransition(3, "t3", new ExponentialTransitionDistribution(9.0));
        petriNet.addTransition(t3);
        var t4 = new TimedTransition(4, "t4", new ExponentialTransitionDistribution(5.0));
        petriNet.addTransition(t4);

        petriNet.addArc(new StandardArc(0, ArcDirection.Input, p0, t0));
        petriNet.addArc(new StandardArc(1, ArcDirection.Output, p1, t0));
        petriNet.addArc(new StandardArc(2, ArcDirection.Output, p2, t0));
        petriNet.addArc(new StandardArc(3, ArcDirection.Input, p1, t1));
        petriNet.addArc(new StandardArc(4, ArcDirection.Output, p3, t1));
        petriNet.addArc(new StandardArc(5, ArcDirection.Input, p2, t2));
        petriNet.addArc(new StandardArc(6, ArcDirection.Output, p4, t2));
        petriNet.addArc(new StandardArc(7, ArcDirection.Input, p3, t3));
        petriNet.addArc(new StandardArc(8, ArcDirection.Output, p1, t3));
        petriNet.addArc(new StandardArc(9, ArcDirection.Input, p3, t4));
        petriNet.addArc(new StandardArc(10, ArcDirection.Input, p4, t4));
        petriNet.addArc(new StandardArc(11, ArcDirection.Output, p0, t4));

        var assertBody =
                "if (mark(\"p3\") > 5)" + System.lineSeparator() +
                "   return(RES_ERROR);" + System.lineSeparator() +
                "else" + System.lineSeparator() +
                "   return(RES_NOERR);";
        spnpCode.setAssertFunction(new FunctionSPNP<>("assert", FunctionType.Other, assertBody, Integer.class));

        var acInitBody =
                "fprintf(stderr,\"\\nExample from Molloy's Thesis\\n\\n\");" + System.lineSeparator() +
                "pr_net_info(); /* information on the net structure */";
        spnpCode.setAcInitFunction(new FunctionSPNP<>("ac_init", FunctionType.Other, acInitBody, Void.class));

        var acReachBody =
                "fprintf(stderr,\"\\nThe reachability graph has been generated\\n\\n\");" + System.lineSeparator() +
                "pr_rg_info(); /* information on the reachability graph */";
        spnpCode.setAcReachFunction(new FunctionSPNP<>("ac_reach", FunctionType.Other, acReachBody, Void.class));

        var acFinalBody =
                "solve(INFINITY);" + System.lineSeparator() +
                "pr_mc_info(); /* information about the Markov chain */" + System.lineSeparator() +
                "pr_expected(\"mark(p0)\",ef0);" + System.lineSeparator() +
                "pr_expected(\"mark(p1)\",ef1);" + System.lineSeparator() +
                "pr_expected(\"rate(t2)\",ef2);" + System.lineSeparator() +
                "pr_expected(\"rate(t3)\",ef3);" + System.lineSeparator() +
                "pr_expected(\"rate(t1) * 1.8 + mark(p3) * 0.7\",eff);" + System.lineSeparator() +
                "pr_std_average(); /* default measures */";
        spnpCode.setAcFinalFunction(new FunctionSPNP<>("ac_final", FunctionType.Other, acFinalBody, Void.class));

        petriNet.addFunction(new FunctionSPNP<>("ef0", FunctionType.Generic, "return((double)mark(\"p0\"));", Double.class));
        petriNet.addFunction(new FunctionSPNP<>("ef1", FunctionType.Generic, "return((double)mark(\"p1\"));", Double.class));
        petriNet.addFunction(new FunctionSPNP<>("ef2", FunctionType.Generic, "return(rate(\"t2\"));", Double.class));
        petriNet.addFunction(new FunctionSPNP<>("ef3", FunctionType.Generic, "return(rate(\"t3\"));", Double.class));
        petriNet.addFunction(new FunctionSPNP<>("eff", FunctionType.Generic, "return(rate(\"t1\") * 1.8 + (double)mark(\"p3\") * 0.7);", Double.class));

        var generatedCode = spnpTransformator.transform(petriNet);
        System.out.println(generatedCode);
    }
}
```
