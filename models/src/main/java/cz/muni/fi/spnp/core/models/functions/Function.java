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
}
