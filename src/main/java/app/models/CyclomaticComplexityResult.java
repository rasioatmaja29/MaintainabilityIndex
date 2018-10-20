package app.models;

import java.util.HashMap;
import java.util.Map;

public class CyclomaticComplexityResult {
    private static CyclomaticComplexityResult instance;
    private int methodPropertyKey;
    Map<Integer, Integer> listOfCyclomaticComplexity = new HashMap<>();

    private CyclomaticComplexityResult(){};

    public static synchronized CyclomaticComplexityResult getInstance() {
        if (instance == null) {
            instance = new CyclomaticComplexityResult();
        }
        return instance;
    }

    public void setMethodPropertyKey(int key){
        this.methodPropertyKey = key;
    }

    public void set(int cyclomaticComplexity){
        listOfCyclomaticComplexity.put(this.methodPropertyKey, cyclomaticComplexity);
    }
}