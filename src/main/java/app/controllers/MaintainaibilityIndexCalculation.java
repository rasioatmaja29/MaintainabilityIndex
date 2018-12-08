package app.controllers;

import app.models.MaintainabilityIndexResult;
import app.models.MethodProperty;
import app.models.OperandAndOperator;
import app.models.PredicateNode;
import javafx.concurrent.Task;

import java.text.DecimalFormat;

public class MaintainaibilityIndexCalculation extends Task<Void> {

    MethodProperty methodProperty;
    HalsteadMetricsCalculation halsteadMetricsCalculation;
    CyclomaticComplexityCalculations cyclomaticComplexityCalculations;
    MaintainabilityIndexResult maintainabilityIndexResult;

    public MaintainaibilityIndexCalculation (){
        methodProperty = MethodProperty.getInstance();
        halsteadMetricsCalculation = HalsteadMetricsCalculation.getInstance();
        cyclomaticComplexityCalculations = CyclomaticComplexityCalculations.getInstance();
        maintainabilityIndexResult = MaintainabilityIndexResult.getInstance();
    }

    public MaintainaibilityIndexCalculation(MethodProperty mp, HalsteadMetricsCalculation hmc,
            CyclomaticComplexityCalculations ccc){
        methodProperty = mp;
        halsteadMetricsCalculation = hmc;
        cyclomaticComplexityCalculations = ccc;
        maintainabilityIndexResult = MaintainabilityIndexResult.getInstance();
    }

    @Override
    protected Void call() {
        updateMessage("Start caculating... ");
        System.out.println("call method is running");
        methodProperty.get().entrySet().forEach(method ->{
            int methodKey = method.getKey();
            System.out.println(methodKey);

            updateMessage("calculating method: " + method.getValue().get(6));

            double halsteadValume = halsteadMetricsCalculation.calculate(methodKey);
            //System.out.println("HV: " + halsteadValume);

            int cyclomaticComplexity = cyclomaticComplexityCalculations.calclulate(methodKey);
            //System.out.println("CC: " + cyclomaticComplexity);

            int loc = Integer.valueOf(method.getValue().get(2));
            //System.out.println("LOC: " + loc);

            //double perCM = (loc != 0) ? Integer.valueOf(method.getValue().get(3)) / Integer.valueOf(method.getValue().get(2)) : 0;
            double comment = Double.valueOf(methodProperty.get().get(methodKey).get(3));
            double perCM = (loc != 0) ? 100 * comment / loc : 0;
            perCM = Double.parseDouble(new DecimalFormat("0.##").format(perCM));

            double maintainabilityIndex = (loc != 0 && halsteadValume != 0) ?
                                          171 - (5.2 * Math.log(halsteadValume)) - (0.23 * cyclomaticComplexity) - (16.2 * Math.log(loc)) + (50 * Math.sin(Math.toRadians(Math.sqrt(2.46 * perCM))))
                                          : 0;
            //System.out.println("MI: " + maintainabilityIndex);

            maintainabilityIndexResult.setMethodPropertyKey(methodKey);
            maintainabilityIndexResult.set(maintainabilityIndex);

        });

        updateMessage("Calculation is complete ");
        return null;
    }

    public void callTest(){
        methodProperty.get().entrySet().forEach(method ->{
            int methodKey = method.getKey();
            //System.out.println(methodKey);

            double halsteadValume = halsteadMetricsCalculation.calculate(methodKey);
            //System.out.println("HV: " + halsteadValume);

            int cyclomaticComplexity = cyclomaticComplexityCalculations.calclulate(methodKey);
            //System.out.println("CC: " + cyclomaticComplexity);

            int loc = Integer.valueOf(method.getValue().get(2));
            //System.out.println("LOC: " + loc);

            //double perCM = (loc != 0) ? Integer.valueOf(method.getValue().get(3)) / Integer.valueOf(method.getValue().get(2)) : 0;
            double comment = Double.valueOf(methodProperty.get().get(methodKey).get(3));
            double perCM = (loc != 0) ? 100 * comment / loc : 0;
            perCM = Double.parseDouble(new DecimalFormat("0.##").format(perCM));

            double maintainabilityIndex = (loc != 0 && halsteadValume != 0) ?
                    171 - (5.2 * Math.log(halsteadValume)) - (0.23 * cyclomaticComplexity) - (16.2 * Math.log(loc)) + (50 * Math.sin(Math.toRadians(Math.sqrt(2.46 * perCM))))
                    : 0;
            //System.out.println("MI: " + maintainabilityIndex);

            maintainabilityIndexResult.setMethodPropertyKey(methodKey);
            maintainabilityIndexResult.set(maintainabilityIndex);

        });
    }
}
