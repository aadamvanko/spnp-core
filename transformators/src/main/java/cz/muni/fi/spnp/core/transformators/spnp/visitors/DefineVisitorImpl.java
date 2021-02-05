package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.models.Define;
import cz.muni.fi.spnp.core.models.visitors.DefineVisitor;

public class DefineVisitorImpl extends Visitor implements DefineVisitor {
    @Override
    public void visit(Define define) {
        if (define.getNumberValue() != null) {
            stringBuilder.append(String.format("#define %s %s%n", define.getName(), define.getNumberValue()));
        }

        if (define.getStringValue() != null) {
            stringBuilder.append(String.format("#define %s %s%n", define.getName(), define.getStringValue()));
        }
    }
}
