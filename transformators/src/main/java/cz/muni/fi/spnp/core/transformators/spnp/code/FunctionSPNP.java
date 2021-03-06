package cz.muni.fi.spnp.core.transformators.spnp.code;

import cz.muni.fi.spnp.core.models.functions.*;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.FunctionDeclarationVisitorImpl;
import cz.muni.fi.spnp.core.transformators.spnp.visitors.FunctionDefinitionVisitorImpl;
/**
 *
 * @param <TReturnType> type of function's return value
 */
public class FunctionSPNP<TReturnType> extends Function {
    private final Class<TReturnType> returnType;
    
    public FunctionSPNP(String name, FunctionType type, String body, Class<TReturnType> returnType ) {
        super(name, type, body);
        
        if (returnType == null)
            throw new IllegalArgumentException("Return type is not specified.");
        
        this.returnType = returnType;
    }
    
    public Class<TReturnType> getReturnType() {
        return returnType;
    }
    
    public String getReturnTypeString() {
        if (returnType == double.class || returnType == Double.class) {
            return "double";
        } else if (returnType == int.class || returnType == Integer.class) {
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

    public void accept(FunctionDeclarationVisitorImpl functionDeclarationVisitor) {
        functionDeclarationVisitor.visit(this);
    }
    
    public void accept(FunctionDefinitionVisitorImpl functionDefinitionVisitor) {
        functionDefinitionVisitor.visit(this);
    }
    
}
