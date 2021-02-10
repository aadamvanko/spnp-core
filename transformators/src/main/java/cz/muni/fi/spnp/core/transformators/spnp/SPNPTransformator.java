package cz.muni.fi.spnp.core.transformators.spnp;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.models.arcs.Arc;
import cz.muni.fi.spnp.core.models.places.Place;
import cz.muni.fi.spnp.core.models.transitions.Transition;
import cz.muni.fi.spnp.core.transformators.Transformator;
import cz.muni.fi.spnp.core.transformators.spnp.code.Include;
import cz.muni.fi.spnp.core.transformators.spnp.code.SPNPCode;
import cz.muni.fi.spnp.core.transformators.spnp.options.Option;
import cz.muni.fi.spnp.core.transformators.spnp.options.SPNPOptions;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.*;

import java.util.List;
import java.util.stream.Collectors;

/*
/∗ This example adapted from M.K. Molloy’s IEEE TC paper ∗/
#include "user.h"

void options() {
  iopt(IOP SSMETHOD, VAL GASEI);
  iopt(IOP PR FULL MARK, VAL YES);
  iopt(IOP PR MARK ORDER, VAL CANONIC);
  iopt(IOP PR MC ORDER, VAL TOFROM);
  iopt(IOP PR MC, VAL YES);
  iopt(IOP PR PROB, VAL YES);
  iopt(IOP MC, VAL CTMC);
  iopt(IOP PR RSET, VAL YES);
  iopt(IOP PR RGRAPH, VAL YES);
  iopt(IOP ITERATIONS, 20000);
  fopt(FOP ABS RET M0, 0.0);
  fopt(FOP PRECISION, 0.00000001);
}
void net() {
  place("p0");
  init("p0", 1);
  place("p1");
  place("p2");
  place("p3");
  place("p4");
  rateval("t0", 1.0);
  rateval("t1", 3.0);
  rateval("t2", 7.0);
  rateval("t3", 9.0);
  rateval("t4", 5.0);
  iarc("t0", "p0");
  oarc("t0", "p1");
  oarc("t0", "p2");
  iarc("t1", "p1");
  oarc("t1", "p3");
  iarc("t2", "p2");
  oarc("t2", "p4");
  iarc("t3", "p3");
  oarc("t3", "p1");
  iarc("t4", "p3");
  iarc("t4", "p4");
  oarc("t4", "p0");
}
int assert() {
  if (mark("p3") > 5) return (RES ERROR);
  else return (RES NOERR);
}
void ac_init() {
  fprintf(stderr, "\nExample from Molloy's Thesis\n\n");
  pr net info(); /∗ information on the net structure ∗/
}
void ac_reach() {
  fprintf(stderr, "\nThe reachability graph has been generated\n\n");
  pr rg info(); /∗ information on the reachability graph ∗/
}
/∗ general marking dependent reward functions ∗/
double ef0() {
  return ((double) mark("p0"));
}
double ef1() {
  return ((double) mark("p1"));
}
double ef2() {
  return (rate("t2"));
}
double ef3() {
  return (rate("t3"));
}
double eff() {
  return (rate("t1")∗1.8 + (double) mark("p3")∗0.7);
}
void ac_final() {
  solve(INFINITY);
  pr mc info(); /∗ information about the Markov chain ∗/
  pr expected("mark(p0)", ef0);
  pr expected("mark(p1)", ef1);
  pr expected("rate(t2)", ef2);
  pr expected("rate(t3)", ef3);
  pr expected("rate(t1) * 1.8 + mark(p3) * 0.7", eff);
  pr std average(); /∗ default measures ∗/
}
 */

public class SPNPTransformator implements Transformator {

    private final SPNPCode spnpCode;
    private final SPNPOptions spnpOptions;

    public SPNPTransformator(SPNPCode spnpCode, SPNPOptions spnpOptions) {
        this.spnpCode = spnpCode;
        this.spnpOptions = spnpOptions;
    }

    @Override
    public String transform(PetriNet petriNet) {
        // TODO add header files
        String source = generateIncludes() +
                generateOptions() + System.lineSeparator() +
                generateNet(petriNet) +
                generateAssert() +
                generateAcInit() +
                generateAcReach() +
                generateAcFinal();
        return source;
    }

    private String generateIncludes() {
        IncludeVisitorImpl includeVisitorImpl = new IncludeVisitorImpl();
        List<Include> sortedIncludes = spnpCode.getIncludes().stream().sorted().collect(Collectors.toList());
        sortedIncludes.forEach(include -> include.accept(includeVisitorImpl));
        return includeVisitorImpl.getResult();
    }

    private String generateOptions() {
        OptionVisitor optionVisitor = new OptionVisitor();
        List<Option> sortedOptions = spnpOptions.getOptions().stream().sorted().collect(Collectors.toList());
        sortedOptions.forEach(option -> option.accept(optionVisitor));
        return String.format("void options() {%n%s}", tabify(optionVisitor.getResult()));
    }

    private String generateNet(PetriNet petriNet) {
        String netDefinition = "void net() {" + System.lineSeparator() +
                tabify(parameterVariablesDefinition()) +
                tabify(placesDefinition(petriNet)) +
                tabify(transitionsDefinition(petriNet)) +
                tabify(arcsDefinition(petriNet)) +
                tabify(parameterVariablesBindings()) +
                // TODO arcs, ...
                "}";
        return netDefinition;
    }

    private String placesDefinition(PetriNet petriNet) {
        PlaceVisitorImpl placeVisitorImpl = new PlaceVisitorImpl();
        List<Place> sortedPlaces = petriNet.getPlaces().stream().sorted().collect(Collectors.toList());
        sortedPlaces.forEach(place -> place.accept(placeVisitorImpl));
        return placeVisitorImpl.getResult();
    }

    private String transitionsDefinition(PetriNet petriNet) {
        TransitionVisitorImpl transitionVisitorImpl = new TransitionVisitorImpl();
        List<Transition> sortedTransitions = petriNet.getTransitions().stream().sorted().collect(Collectors.toList());
        sortedTransitions.forEach(transition -> transition.accept(transitionVisitorImpl));
        return transitionVisitorImpl.getResult();
    }

    private String arcsDefinition(PetriNet petriNet) {
        ArcVisitorImpl arcVisitorImpl = new ArcVisitorImpl();
        List<Arc> sortedArcs = petriNet.getArcs().stream().sorted().collect(Collectors.toList());
        sortedArcs.forEach(arc -> arc.accept(arcVisitorImpl));
        return arcVisitorImpl.getResult();
    }

    private String generateAssert() {
        return "";
    }

    private String generateAcInit() {
        return "";
    }

    private String generateAcReach() {
        return "";
    }

    private String generateAcFinal() {
        return "";
    }

    private String parameterVariablesDefinition() {
        var parameterVariables = spnpCode.getParameterVariables();
        if (parameterVariables.isEmpty())
            return "";

        StringBuilder definitions = new StringBuilder("/* ==== PARAMETER VARIABLES ==== */" + System.lineSeparator());
        parameterVariables.forEach(variable -> definitions.append(String.format("parm(\"%s\");%n", variable.getName())));

        definitions.append(System.lineSeparator());
        return definitions.toString();
    }

    private String parameterVariablesBindings() {
        var parameterVariables = spnpCode.getParameterVariables();
        if (parameterVariables.isEmpty())
            return "";

        StringBuilder definitions = new StringBuilder("/* ==== PARAMETER VARIABLES BINDINGS ==== */" + System.lineSeparator());
        parameterVariables.forEach(variable -> definitions.append(String.format("bind(\"%s\", %s);%n",
                variable.getName(), variable.getName())));

        definitions.append(System.lineSeparator());
        return definitions.toString();
    }

    private String tabify(String text) {
        String[] lines = text.split(System.lineSeparator());
        StringBuilder textWithTabs = new StringBuilder();
        for (String line : lines) {
            textWithTabs.append("\t").append(line).append(System.lineSeparator());
        }
        return textWithTabs.toString();
    }

}
