package cz.muni.fi.spnp.core.transformators.spnp.visitors;

import cz.muni.fi.spnp.core.transformators.spnp.options.ConstantTypeOption;
import cz.muni.fi.spnp.core.transformators.spnp.options.DoubleTypeOption;
import cz.muni.fi.spnp.core.transformators.spnp.options.IntegerTypeOption;
import cz.muni.fi.spnp.core.transformators.spnp.options.Option;

public class OptionVisitor extends Visitor {

    public void visit(ConstantTypeOption constantTypeOption) {
        stringBuilder.append(String.format("iopt(%s, %s);%n", constantTypeOption.getKey().toString(), constantTypeOption.getValue().toString()));
    }

    public void visit(DoubleTypeOption doubleTypeOption) {
        stringBuilder.append(String.format("fopt(%s, %f);%n", doubleTypeOption.getKey().toString(), doubleTypeOption.getValue()));
    }

    public void visit(IntegerTypeOption integerTypeOption) {
        stringBuilder.append(String.format("iopt(%s, %d);%n", integerTypeOption.getKey().toString(), integerTypeOption.getValue()));
    }

    public void visit(Option option) {
        System.out.println(option.getClass());
        throw new IllegalStateException("Option class is abstract!");
    }
}
