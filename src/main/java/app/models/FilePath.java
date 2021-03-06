/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rasio
 */
public class FilePath {
    private static FilePath instance;
    private FilePath(){};

    String rootDirectory;
    Map<Integer, List<String>> listOfFilePath = new HashMap<>();
    
    
    /**
     * @return 
     * implementation on singleton pattern
     */
    
    public static synchronized FilePath getInstance() {
        if (instance == null) {
            instance = new FilePath();
        }
        return instance;
    }
    /**
     * 
     * @param name
     * @param path
     * 
     * Method setFilPath()
     * untuk menyimpan namafile dan alamat directory
     */
    public void set(String name, String path){
        List<String> filePath = new ArrayList<>();
        filePath.add(name);
        filePath.add(path);
        listOfFilePath.put(listOfFilePath.size(), filePath);
    }

    public void set(String rootDirectory){
        this.rootDirectory = rootDirectory;
    }
    
    public Map<Integer, List<String>> get(){
        return listOfFilePath;
    }

    public String getRootDorectory (){return this.rootDirectory;}
    
    public void clear(){
        listOfFilePath.clear();
    }
}
