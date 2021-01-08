package cz.muni.fi.spnp.core.tests;

import cz.muni.fi.spnp.core.transformators.spnp.options.OptionKey;
import java.util.regex.Pattern;


public class TestPatterns {
    public static Pattern functionClosedRegex = Pattern.compile("[ \\t]*}[ \\t]*");
    
    /* OPTIONS */
    public static Pattern optionsFunctionStartedRegex = Pattern.compile("^void options\\((void)?\\) *\\{$");
    
    public static Pattern inputParameterRegex = Pattern.compile("^[ \\t]*[^\\s]+ *= *(input|finput) *\\( *\".*\" *\\) *; *$");
    public static Pattern inputParameterExtractRegex = Pattern.compile("[ \\t]*(.*?) *=");
    
    public static Pattern optionsRegex = null;
    public static Pattern optionKeyExtractRegex = Pattern.compile("\\( *(.*?) *,");

    
    public static void init(){
        initOptionsRegex();
    }
    
    private static void initOptionsRegex(){
        var possibleKeys = OptionKey.values();
        var keysStringBuilder = new StringBuilder("(");
        for(int i = 0; i < possibleKeys.length; i++){
            keysStringBuilder.append(possibleKeys[i].toString());
            if(i < possibleKeys.length - 1)
                keysStringBuilder.append("|");
        }
        keysStringBuilder.append(")");
        
        optionsRegex = Pattern.compile(String.format("^[ \\t]*(iopt|fopt) *\\( *%s *, *[^\\s]+ *\\) *; *$", keysStringBuilder.toString()));
    }
}
