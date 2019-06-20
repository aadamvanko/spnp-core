package cz.muni.fi.spnp.core.models.functions;

public class Function<TReturnType> {

    private String name;
    private FunctionType functionType;
    private Class<TReturnType> returnType;
    private String body;

    /**
     * Creates function definition with specified body and return value.
     *
     * @param name          function name
     * @param type          type of the function
     * @param body          function body definition
     * @param returnType    type of function's return value
     */
    public Function(String name, FunctionType type, String body, Class<TReturnType> returnType) {
        if (name == null)
            throw new IllegalArgumentException("Function name is not specified.");
        if (type == null)
            throw new IllegalArgumentException("Function type is not defined");
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
