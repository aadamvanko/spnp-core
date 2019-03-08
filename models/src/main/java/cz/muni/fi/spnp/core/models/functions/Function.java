package cz.muni.fi.spnp.core.models.functions;

public class TransitionFunction {

    private String name;
    private Class returnType;
    private String body;

    /**
     * Creates function definition with no body and no return value.
     *
     * @param name  function name
     */
    public TransitionFunction(String name) {
        this(name, Void.class, "");
    }

    /**
     * Creates function definition with defined body and no return value.
     *
     * @param name  function name
     * @param body  function body
     */
    public TransitionFunction(String name, String body) {
        this(name, Void.class, body);
    }

    /**
     * Creates function definition with specified body and return value.
     *
     * @param name          function name
     * @param returnType    type of function's return value
     * @param body          function body definition
     */
    public TransitionFunction(String name, Class returnType, String body) {
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

    public Class getReturnType() {
        return returnType;
    }

    public String getBody() {
        return body;
    }
}
