package cz.muni.fi.spnp.core.models.functions;

import cz.muni.fi.spnp.core.models.Commentary;
import cz.muni.fi.spnp.core.models.visitors.FunctionDeclarationVisitor;
import cz.muni.fi.spnp.core.models.visitors.FunctionDefinitionVisitor;

import java.util.Objects;

public class Function implements Comparable<Function> {

    private final String name;
    private final FunctionType functionType;
    private final String body;
    private Commentary commentary;

    /**
     * Creates function definition with specified body and return value.
     *
     * @param name       function name
     * @param type       type of the function
     * @param body       function body definition
     */
    public Function(String name, FunctionType type, String body) {
        if (name == null)
            throw new IllegalArgumentException("Function name is not specified.");
        if (type == null)
            throw new IllegalArgumentException("Function type is not defined.");
        if (body == null)
            throw new IllegalArgumentException("Function body is not specified.");

        this.name = name;
        this.functionType = type;
        this.body = body;
        this.commentary = new Commentary("");
    }

    public String getName() {
        return name;
    }

    public FunctionType getFunctionType() {
        return functionType;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Function function = (Function) o;

        return name.equals(function.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
    
    public void accept(FunctionDefinitionVisitor functionDefinitionVisitor) {
        functionDefinitionVisitor.visit(this);
    }

    public void accept(FunctionDeclarationVisitor functionDeclarationVisitor) {
        functionDeclarationVisitor.visit(this);
    }

    @Override
    public int compareTo(Function other) {
        return name.compareTo(other.name);
    }

    public Commentary getCommentary() {
        return commentary;
    }

    public void setCommentary(String text) {
        this.commentary = new Commentary(text);
    }

    public void setCommentary(Commentary commentary) {
        this.commentary = commentary;
    }
}
