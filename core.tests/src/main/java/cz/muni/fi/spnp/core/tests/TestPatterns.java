package cz.muni.fi.spnp.core.tests;

import cz.muni.fi.spnp.core.transformators.spnp.options.OptionKey;
import java.util.regex.Pattern;


public class TestPatterns {
    /* COMMON */
    private static final String STRING_EXTRACTION_REGEX = "(.*?)";
    
    public static Pattern functionClosedRegex = Pattern.compile("[ \\t]*}[ \\t]*");
    
    /* OPTIONS */
    public static Pattern optionsFunctionStartedRegex = Pattern.compile("^void options\\((void)?\\) *\\{$");
    
    public static Pattern inputParameterRegex = Pattern.compile("^[ \\t]*[^\\s]+ *= *(input|finput) *\\( *\".*\" *\\) *; *$");
    public static Pattern inputParameterExtractRegex = Pattern.compile("[ \\t]*" + STRING_EXTRACTION_REGEX + " *=");
    
    public static Pattern optionsRegex = null;
    public static Pattern optionKeyExtractRegex = Pattern.compile("\\( *" + STRING_EXTRACTION_REGEX + " *,");

    /* NET */
    public static Pattern netFunctionStartedRegex = Pattern.compile("^void net\\((void)?\\) *\\{$");
    
    private static final String PLACE_NAME_REGEX = "[a-zA-Z]+[a-zA-Z0-9_]*";
    public static Pattern standardPlaceDeclarationRegex = Pattern.compile("^[ \\t]*place *\\( *\"" + PLACE_NAME_REGEX + "\" *\\) *; *$");
    public static Pattern standardPlaceDeclarationExtractRegex = Pattern.compile("^[ \\t]*place *\\( *\"" + STRING_EXTRACTION_REGEX + "\" *\\) *; *$");
    public static Pattern standardPlaceInitRegex = Pattern.compile("^[ \\t]*init\\( *\"" + PLACE_NAME_REGEX + "\" *, *.+ *\\) *; *$");
    public static Pattern standardPlaceInitNameExtractRegex = Pattern.compile("^[ \\t]*init *\\( *\"" + STRING_EXTRACTION_REGEX + "\" *, *.+ *\\) *; *$");

    public static Pattern fluidPlaceDeclarationRegex = Pattern.compile("^[ \\t]*fplace *\\( *\"" + PLACE_NAME_REGEX + "\" *\\) *; *$");
    public static Pattern fluidPlaceDeclarationExtractRegex = Pattern.compile("^[ \\t]*fplace *\\( *\"" + STRING_EXTRACTION_REGEX + "\" *\\) *; *$");
    public static Pattern fluidPlaceInitRegex = Pattern.compile("^[ \\t]*finit\\( *\"" + PLACE_NAME_REGEX + "\" *, *.+ *\\) *; *$");
    public static Pattern fluidPlaceInitNameExtractRegex = Pattern.compile("^[ \\t]*finit *\\( *\"" + STRING_EXTRACTION_REGEX + "\" *, *.+ *\\) *; *$");
    public static Pattern fluidPlaceBoundRegex = Pattern.compile("^[ \\t]*fbound *\\( *\"" + PLACE_NAME_REGEX + "\" *, *.+ *\\) *; *$");
    public static Pattern fluidPlaceBoundNameExtractRegex = Pattern.compile("^[ \\t]*fbound *\\( *\"" + STRING_EXTRACTION_REGEX + "\" *, *.+ *\\) *; *$");
    public static Pattern fluidPlaceBreakRegex = Pattern.compile("^[ \\t]*fbreak *\\( *\"" + PLACE_NAME_REGEX + "\" *, *.+ *\\) *; *$");
    public static Pattern fluidPlaceBreakNameExtractRegex = Pattern.compile("^[ \\t]*fbreak *\\( *\"" + STRING_EXTRACTION_REGEX + "\" *, *.+ *\\) *; *$");
    public static Pattern fluidPlaceBreakValueExtractRegex = Pattern.compile("^[ \\t]*fbreak *\\( *\"" + PLACE_NAME_REGEX + "\" *, *" + STRING_EXTRACTION_REGEX + " *\\) *; *$");
    
    
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
