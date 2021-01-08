package cz.muni.fi.spnp.core.tests;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPCode;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPOptions;
import cz.muni.fi.spnp.core.transformators.spnp.SPNPTransformator;
import java.util.HashSet;
import java.util.regex.Matcher;


public abstract class TransformatorTest {
    private final SPNPTransformator transformator;
    private final SPNPCode code;
    private final SPNPOptions options;
    private final PetriNet petriNet;
    
    public TransformatorTest() {
        this.code = createCode();
        this.options = createOptions();
        this.petriNet = createPetriNet();
        
        this.transformator = new SPNPTransformator(code, options);
    }
    
    private boolean runOptionsTest(String output){
        System.out.println("*** " + this.getClass().getSimpleName() + " ***");
        
        var lines = output.split(System.lineSeparator());
        
        var tmpOptionKeys = new HashSet<String>();
        options.getOptions().forEach(option -> {
            tmpOptionKeys.add(option.getKey().toString());
        });
        
        var tmpInputParameters = new HashSet<String>();
        options.getInputParameters().forEach(parameter -> {
            tmpInputParameters.add(parameter.getParameterName());
        });
        
        boolean optionsStarted = false;
        boolean optionsClosed = false;
        for(var line : lines){
            if(optionsStarted){
                if(TestPatterns.inputParameterRegex.matcher(line).matches()){
                    Matcher matcher = TestPatterns.inputParameterExtractRegex.matcher(line);
                    if (matcher.find())
                    {
                        var inputParameter = matcher.group(1);
                        if(!tmpInputParameters.remove(inputParameter)){
                            System.out.println("Input parameter was not defined: " + inputParameter);
                        }
                    }
                }
                else if(TestPatterns.optionsRegex.matcher(line).matches()){
                    Matcher matcher = TestPatterns.optionKeyExtractRegex.matcher(line);
                    if (matcher.find())
                    {
                        var optionKey = matcher.group(1);
                        if(!tmpOptionKeys.remove(optionKey)){
                            System.out.println("Option with the following key was not defined: " + optionKey);
                        }
                    }
                }
                else if(TestPatterns.functionClosedRegex.matcher(line).matches()){
                    optionsClosed = true;
                    break;
                }
                else{
                    System.out.println("The following line was not recognized as valid inside options() :" + line);
                }
            }
            else if(TestPatterns.optionsFunctionStartedRegex.matcher(line).matches()){
                optionsStarted = true;
            }
        }
        
        boolean retVal = true;
        
        if(!optionsStarted){
            System.out.println("void options() { not found");
            retVal = false;
        }
        if(!optionsClosed){
            System.out.println("void options() { function body not closed with a \"}\"");
            retVal = false;
        }
        if(!tmpOptionKeys.isEmpty()){
            System.out.println("Some defined option keys were not transformed into output: " + tmpOptionKeys);
            retVal = false;
        }
        if(!tmpInputParameters.isEmpty()){
            System.out.println("Some defined input parameters were not transformed into output: " + tmpInputParameters);
            retVal = false;
        }
        
        if(retVal)
            System.out.println("RESULT: Success");
        else
            System.out.println("RESULT: Fail");
        System.out.println();
        
        return retVal;
    }
    
    public boolean runTest(){
        var output = transformator.transform(petriNet);
        
        return runOptionsTest(output);
    }
    
    protected abstract SPNPCode createCode();
    protected abstract SPNPOptions createOptions();
    protected abstract PetriNet createPetriNet();
}
