package cz.muni.fi.spnp.core.models.functions;

import cz.muni.fi.spnp.core.models.visitors.FunctionDeclarationVisitor;
import cz.muni.fi.spnp.core.models.visitors.FunctionDefinitionVisitor;

import java.util.Objects;

public class Function<TReturnType> {

    private final String name;
    private final FunctionType functionType;
    private final Class<TReturnType> returnType;
    private final String body;

    /**
     * Creates function definition with specified body and return value.
     *
     * @param name       function name
     * @param type       type of the function
     * @param body       function body definition
     * @param returnType type of function's return value
     */
    public Function(String name, FunctionType type, String body, Class<TReturnType> returnType) {
        if (name == null)
            throw new IllegalArgumentException("Function name is not specified.");
        if (type == null)
            throw new IllegalArgumentException("Function type is not defined.");
        if (returnType == null)
            throw new IllegalArgumentException("Return type is not specified.");
        if (body == null)
            throw new IllegalArgumentException("Function body is not specified.");

        this.name = name;
        this.functionType = type;
        this.returnType = returnType;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public FunctionType getFunctionType() {
        return functionType;
    }

    public Class<TReturnType> getReturnType() {
        return returnType;
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

        Function<?> function = (Function<?>) o;

        return name.equals(function.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }


    public String getReturnTypeString() {
        if (returnType == double.class) {
            return "double";
        } else if (returnType == int.class) {
            return "int";
        } else if (returnType == Void.class) {
            return "void";
        } else if (returnType == String.class) {
            return "char *";
        } else if (returnType == Character.class) {
            return "char";
        } else {
            throw new IllegalStateException("Unsupported function return type.");
        }
    }

    public void accept(FunctionDefinitionVisitor functionDefinitionVisitor) {
        functionDefinitionVisitor.visit(this);
    }

    public void accept(FunctionDeclarationVisitor functionDeclarationVisitor) {
        functionDeclarationVisitor.visit(this);
    }
}
