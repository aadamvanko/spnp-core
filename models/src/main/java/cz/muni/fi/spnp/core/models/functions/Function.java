package cz.muni.fi.spnp.core.models.functions;

public class Function<TReturnType> {

    private String name;
    private Class<TReturnType> returnType;
    private String body;

    /**
     * Creates function definition with specified body and return value.
     *
     * @param name          function name
     * @param body          function body definition
     * @param returnType    type of function's return value
     */
    public Function(String name, String body, Class<TReturnType> returnType) {
        if (name == null)
            throw new IllegalArgumentException("Function name is not specified.");
        if (returnType == null)
            throw new IllegalArgumentException("Return type is not specified.");
        if (body == null)
            throw new IllegalArgumentException("Function body is not specified.");

        this.name = name;
        this.returnType = returnType;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public Class<TReturnType> getReturnType() {
        return returnType;
    }

    public String getBody() {
        return body;
    }

    public String getDeclaration() {
        return String.format("%s %s();%n",
                             this.getReturnTypeString(),
                             this.getName());
    }

    public String getDefinition() {
        return String.format(
                "%s %s() {%n%s%n}%n",
                this.getReturnTypeString(),
                this.getName(),
                this.getBody());
    }


    private String getReturnTypeString() {
        if (returnType == double.class) {
            return "double";
        } else if (returnType == int.class) {
            return "int";
        } else if (returnType == Void.class) {
            return "void";
        } else if (returnType == String.class) {
            return "string";
        } else if (returnType == Character.class) {
            return "char";
        } else {
            throw new IllegalStateException("Unsupported function return type.");
        }
    }
}
