package cz.muni.fi.spnp.core.tests;

import cz.muni.fi.spnp.core.models.PetriNet;
import cz.muni.fi.spnp.core.models.places.FluidPlace;
import cz.muni.fi.spnp.core.models.places.StandardPlace;
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
    
    private StandardPlaceTestWrapper createStandardPlaceWrapper(StandardPlace place){
        var placeWrapper = new StandardPlaceTestWrapper(place.getId(), place.getName());
        placeWrapper.setNumberOfTokens(place.getNumberOfTokens());
        place.getArcs().forEach(arc -> {
            placeWrapper.addArc(arc);
        });
        return placeWrapper;
    }
    
    private FluidPlaceTestWrapper createFluidPlaceWrapper(FluidPlace place){
        var placeWrapper = new FluidPlaceTestWrapper(place.getId(), place.getName());
        placeWrapper.setInitialValue(place.getInitialValue());
        place.getArcs().forEach(arc -> {
            placeWrapper.addArc(arc);
        });
        
        placeWrapper.setBoundValue(place.getBoundValue());
        place.getBreakValues().forEach(breakValue -> {
            placeWrapper.addBreakValue(breakValue);
        });
        return placeWrapper;
    }
    
    private boolean checkInputParameter(String line, HashSet<String> tmpInputParameters){
        boolean success = true;
        Matcher matcher = TestPatterns.inputParameterExtractRegex.matcher(line);
        if (matcher.find())
        {
            var inputParameter = matcher.group(1);
            if(!tmpInputParameters.remove(inputParameter)){
                System.out.println("Input parameter was not defined: " + inputParameter);
                success = false;
            }
        }
        return success;
    }

    private boolean checkOption(String line, HashSet<String> tmpOptionKeys){
        boolean success = true;
        Matcher matcher = TestPatterns.optionKeyExtractRegex.matcher(line);
        if (matcher.find())
        {
            var optionKey = matcher.group(1);
            if(!tmpOptionKeys.remove(optionKey)){
                System.out.println("Option with the following key was not defined: " + optionKey);
                success = false;
            }
        }
        return success;
    }
    
    private boolean runOptionsTest(String output){
        boolean success = true;
        
        System.out.println("Options test:");
        
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
                    success = checkInputParameter(line, tmpInputParameters);
                }
                else if(TestPatterns.optionsRegex.matcher(line).matches()){
                    success = checkOption(line, tmpOptionKeys);
                }
                else if(TestPatterns.functionClosedRegex.matcher(line).matches()){
                    optionsClosed = true;
                    break;
                }
                else if(!line.isBlank()){
                    System.out.println("The following line was not recognized as valid inside options() :" + line);
                    success = false;
                }
            }
            else if(TestPatterns.optionsFunctionStartedRegex.matcher(line).matches()){
                optionsStarted = true;
            }
        }

        if(!optionsStarted){
            System.out.println("void options() { not found");
            success = false;
        }
        if(!optionsClosed){
            System.out.println("void options() { function body not closed with a \"}\"");
            success = false;
        }
        if(!tmpOptionKeys.isEmpty()){
            System.out.println("Some defined option keys were not transformed into output: " + tmpOptionKeys);
            success = false;
        }
        if(!tmpInputParameters.isEmpty()){
            System.out.println("Some defined input parameters were not transformed into output: " + tmpInputParameters);
            success = false;
        }
        
        if(success)
            System.out.println("Options test result: Success");
        else
            System.out.println("Options test result: Fail");
        System.out.println();
        
        return success;
    }
    
    private boolean checkStandardPlaceDeclaration(String line, HashSet<StandardPlaceTestWrapper> tmpStandardPlaces){
        boolean success = true;
        Matcher matcher = TestPatterns.standardPlaceDeclarationExtractRegex.matcher(line);
        if (matcher.find())
        {
            var placeDeclarationName = matcher.group(1);
            boolean found = false;
            for(var place : tmpStandardPlaces){
                if(place.getName().equals(placeDeclarationName)){
                    place.setDeclared(true);
                    found = true;
                }
            }
            if(!found){
                System.out.println("Standard place was declared but should not be: " + placeDeclarationName);
                success = false;
            }
        }
        return success;
    }
    
    private boolean checkStandardPlaceInit(String line, HashSet<StandardPlaceTestWrapper> tmpStandardPlaces){
        boolean success = true;
        Matcher matcher = TestPatterns.standardPlaceInitNameExtractRegex.matcher(line);
        if (matcher.find())
        {
            var placeInitName = matcher.group(1);
            boolean found = false;
            for(var place : tmpStandardPlaces){
                if(place.getName().equals(placeInitName)){
                    place.setDefaultValueInited(true);
                    found = true;
                }
            }
            if(!found){
                System.out.println("Standard place default value was initialized but should not be: " + placeInitName);
                success = false;
            }
        }
        return success;
    }
    
    private boolean checkFluidPlaceDeclaration(String line, HashSet<FluidPlaceTestWrapper> tmpFluidPlaces){
        boolean success = true;
        Matcher matcher = TestPatterns.fluidPlaceDeclarationExtractRegex.matcher(line);
        if (matcher.find())
        {
            var placeDeclarationName = matcher.group(1);
            boolean found = false;
            for(var place : tmpFluidPlaces){
                if(place.getName().equals(placeDeclarationName)){
                    place.setDeclared(true);
                    found = true;
                }
            }
            if(!found){
                System.out.println("Fluid place was declared but should not be: " + placeDeclarationName);
                success = false;
            }
        }
        return success;
    }
    
    private boolean checkFluidPlaceInit(String line, HashSet<FluidPlaceTestWrapper> tmpFluidPlaces){
        boolean success = true;
        Matcher matcher = TestPatterns.fluidPlaceInitNameExtractRegex.matcher(line);
        if (matcher.find())
        {
            var placeInitName = matcher.group(1);
            boolean found = false;
            for(var place : tmpFluidPlaces){
                if(place.getName().equals(placeInitName)){
                    place.setDefaultValueInited(true);
                    found = true;
                }
            }
            if(!found){
                System.out.println("Fluid place default value was initialized but should not be: " + placeInitName);
                success = false;
            }
        }
        return success;
    }
    
    private boolean checkFluidPlaceBoundValue(String line, HashSet<FluidPlaceTestWrapper> tmpFluidPlaces){
        boolean success = true;
        Matcher matcher = TestPatterns.fluidPlaceBoundNameExtractRegex.matcher(line);
        if (matcher.find())
        {
            var placeBoundName = matcher.group(1);
            boolean found = false;
            for(var place : tmpFluidPlaces){
                if(place.getName().equals(placeBoundName)){
                    place.setBoundValueInited(true);
                    found = true;
                }
            }
            if(!found){
                System.out.println("Fluid place bound value was initialized but should not be: " + placeBoundName);
                success = false;
            }
        }
        return success;
    }
    
    private boolean checkFluidPlaceBreakValue(String line, HashSet<FluidPlaceTestWrapper> tmpFluidPlaces){
        boolean success = true;
        Matcher nameMatcher = TestPatterns.fluidPlaceBreakNameExtractRegex.matcher(line);
        Matcher valueMatcher = TestPatterns.fluidPlaceBreakValueExtractRegex.matcher(line);
        if (nameMatcher.find())
        {
            var placeBreakName = nameMatcher.group(1);
            if(valueMatcher.find()){
                var placeBreakValue = Double.parseDouble(valueMatcher.group(1));

                boolean found = false;
                for(var place : tmpFluidPlaces){
                    if(place.getName().equals(placeBreakName)){
                        var breakValues = place.getBreakValuesInited();
                        if(breakValues.get(placeBreakValue) != null){
                            place.setBreakValueInited(placeBreakValue, true);
                            found = true;
                        }
                    }
                }
                if(!found){
                    System.out.println("Fluid place break value was initialized but should not be: " + placeBreakName + " break value: " + placeBreakValue);
                    success = false;
                }
            }
        }
        return success;
    }
    
    private boolean runNetTest(String output){
        boolean success = true;
        System.out.println("Net test:");
        
        var lines = output.split(System.lineSeparator());
        
        var tmpStandardPlaces = new HashSet<StandardPlaceTestWrapper>();
        var tmpFluidPlaces = new HashSet<FluidPlaceTestWrapper>();
        petriNet.getPlaces().forEach(place ->{
            if(place instanceof StandardPlace){
                tmpStandardPlaces.add(this.createStandardPlaceWrapper((StandardPlace) place));
            }
            else if(place instanceof FluidPlace){
                tmpFluidPlaces.add(this.createFluidPlaceWrapper((FluidPlace) place));
            }
        });
        
        boolean netStarted = false;
        boolean netClosed = false;
        for(var line : lines){
            if(netStarted){
                if(TestPatterns.standardPlaceDeclarationRegex.matcher(line).matches()){
                    success = checkStandardPlaceDeclaration(line, tmpStandardPlaces);
                }
                else if(TestPatterns.standardPlaceInitRegex.matcher(line).matches()){
                    success = checkStandardPlaceInit(line, tmpStandardPlaces);
                }
                else if(TestPatterns.fluidPlaceDeclarationRegex.matcher(line).matches()){
                    success = checkFluidPlaceDeclaration(line, tmpFluidPlaces);
                }
                else if(TestPatterns.fluidPlaceInitRegex.matcher(line).matches()){
                    success = checkFluidPlaceInit(line, tmpFluidPlaces);
                }
                else if(TestPatterns.fluidPlaceBoundRegex.matcher(line).matches()){
                    success = checkFluidPlaceBoundValue(line, tmpFluidPlaces);
                }
                else if(TestPatterns.fluidPlaceBreakRegex.matcher(line).matches()){
                    success = checkFluidPlaceBreakValue(line, tmpFluidPlaces);
                }
                else if(TestPatterns.functionClosedRegex.matcher(line).matches()){
                    netClosed = true;
                    break;
                }
                else if(!line.isBlank()){
                    System.out.println("The following line was not recognized as valid inside net() :" + line);
                    success = false;
                }
            }
            else if(TestPatterns.netFunctionStartedRegex.matcher(line).matches()){
                netStarted = true;
            }
        }

        if(!netStarted){
            System.out.println("void net() { not found");
            success = false;
        }
        if(!netClosed){
            System.out.println("void net() { function body not closed with a \"}\"");
            success = false;
        }
        for(var place : tmpStandardPlaces){
            if(!place.getDeclared()){
                System.out.println("Standard place was not declared: " + place.getName());
                success = false;
            }
            if(!place.getDefaultValueInited() && place.getNumberOfTokens() > 0){
                System.out.println("Standard place default value was not initialized: " + place.getName());
                success = false;
            }
        }
        
        for(var place : tmpFluidPlaces){
            if(!place.getDeclared()){
                System.out.println("Fluid place was not declared: " + place.getName());
                success = false;
            }
            if(!place.getDefaultValueInited() && place.getInitialValue() > 0){
                System.out.println("Fluid place default value was not initialized: " + place.getName());
                success = false;
            }
            if(!place.getBoundValueInited() && place.getBoundValue() > 0){
                System.out.println("Fluid place bound value was not initialized: " + place.getName());
                success = false;
            }
            
            var breakValues = place.getBreakValuesInited();
            for(var breakValueKey : breakValues.keySet()){
                if((Boolean) breakValues.get(breakValueKey) == false){
                    System.out.println("Fluid place break value was not initialized: " + place.getName() + " break value: " + breakValueKey);
                    success = false;
                }
            }
        }
        
        if(success)
            System.out.println("Net test result: Success");
        else
            System.out.println("Net test result: Fail");
        System.out.println();
        
        return success;
    }
    
    public String getOutput(){
        return transformator.transform(petriNet);
    }
    
    public boolean runTest(){
        var output = this.getOutput();
        
        System.out.println("***** " + this.getClass().getSimpleName() + " *****");
        
        boolean result =    runOptionsTest(output) &
                            runNetTest(output);
        if(result)
            System.out.println("Total result: Success");
        else
            System.out.println("Total result: Fail");
        System.out.println();
        System.out.println();
        return result;
    }
    
    protected abstract SPNPCode createCode();
    protected abstract SPNPOptions createOptions();
    protected abstract PetriNet createPetriNet();
}
