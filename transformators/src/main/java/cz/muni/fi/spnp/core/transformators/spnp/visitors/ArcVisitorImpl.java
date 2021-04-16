package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.arcs.InhibitorArc;
import cz.muni.fi.spnp.core.models.arcs.StandardArc;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.visitors.ArcVisitor;

public class ArcVisitorImpl extends Visitor implements ArcVisitor {
    @Override
    public void visit(InhibitorArc inhibitorArc) {
        String prefix = "";

        if (inhibitorArc.getPlace() instanceof FluidPlace) {
            prefix += "d";
        }

        if (inhibitorArc.getCalculateMultiplicityFunction() != null) {
            stringBuilder.append(inhibitorArc.getCommentary().getLineCommentary())
                    .append(prefix).append(String.format("vharc(\"%s\", \"%s\", %s);%s",
                    inhibitorArc.getTransition().getName(),
                    inhibitorArc.getPlace().getName(),
                    inhibitorArc.getCalculateMultiplicityFunction().getName(),
                    System.lineSeparator()));

        } else if (inhibitorArc.getMultiplicity() > 1) {
            stringBuilder.append(inhibitorArc.getCommentary().getLineCommentary())
                    .append(prefix).append(String.format("mharc(\"%s\", \"%s\", %d);%s",
                    inhibitorArc.getTransition().getName(),
                    inhibitorArc.getPlace().getName(),
                    inhibitorArc.getMultiplicity(),
                    System.lineSeparator()));

        } else {
            stringBuilder.append(inhibitorArc.getCommentary().getLineCommentary())
                    .append(prefix).append(String.format("harc(\"%s\", \"%s\");%s",
                    inhibitorArc.getTransition().getName(),
                    inhibitorArc.getPlace().getName(),
                    System.lineSeparator()));
        }
    }

    @Override
    public void visit(StandardArc standardArc) {
        switch (standardArc.getDirection()) {
            case Input:
                String prefix = "";
                if (standardArc.isFluid()) {
                    prefix = "f";
                } else if (standardArc.getPlace() instanceof FluidPlace) {
                    prefix = "d";
                }

                if (standardArc.getCalculateMultiplicityFunction() != null) {
                    stringBuilder.append(standardArc.getCommentary().getLineCommentary())
                            .append(prefix).append(String.format("viarc(\"%s\", \"%s\", %s);%s",
                            standardArc.getTransition().getName(),
                            standardArc.getPlace().getName(),
                            standardArc.getCalculateMultiplicityFunction().getName(),
                            System.lineSeparator()));
                } else if (standardArc.getMultiplicity() > 1) {
                    stringBuilder.append(standardArc.getCommentary().getLineCommentary())
                            .append(prefix).append(String.format("miarc(\"%s\", \"%s\", %d);%s",
                            standardArc.getTransition().getName(),
                            standardArc.getPlace().getName(),
                            standardArc.getMultiplicity(),
                            System.lineSeparator()));
                } else {
                    stringBuilder.append(standardArc.getCommentary().getLineCommentary())
                            .append(prefix).append(String.format("iarc(\"%s\", \"%s\");%s",
                            standardArc.getTransition().getName(),
                            standardArc.getPlace().getName(),
                            System.lineSeparator()));
                }
                break;

            case Output:
                prefix = "";
                if (standardArc.isFluid()) {
                    prefix = "f";
                } else if (standardArc.getPlace() instanceof FluidPlace) {
                    prefix = "d";
                }

                if (standardArc.getCalculateMultiplicityFunction() != null) {
                    stringBuilder.append(standardArc.getCommentary().getLineCommentary())
                            .append(prefix).append(String.format("voarc(\"%s\", \"%s\", %s);%s",
                            standardArc.getTransition().getName(),
                            standardArc.getPlace().getName(),
                            standardArc.getCalculateMultiplicityFunction().getName(),
                            System.lineSeparator()));
                } else if (standardArc.getMultiplicity() > 1) {
                    stringBuilder.append(standardArc.getCommentary().getLineCommentary())
                            .append(prefix).append(String.format("moarc(\"%s\", \"%s\", %d);%s",
                            standardArc.getTransition().getName(),
                            standardArc.getPlace().getName(),
                            standardArc.getMultiplicity(),
                            System.lineSeparator()));
                } else {
                    stringBuilder.append(standardArc.getCommentary().getLineCommentary())
                            .append(prefix).append(String.format("oarc(\"%s\", \"%s\");%s",
                            standardArc.getTransition().getName(),
                            standardArc.getPlace().getName(),
                            System.lineSeparator()));
                }
                break;

            default:
                throw new IllegalStateException("Unknown Arc direction.");
        }
    }
}
