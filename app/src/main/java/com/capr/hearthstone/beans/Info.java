package com.capr.hearthstone.beans;
import java.util.ArrayList;

/**
 * Created by CRISTIAN on 10/08/2015.
 */
public class Info {

    private String patch;
    private ArrayList<String> classes;
    private ArrayList<String> sets;
    private ArrayList<String> types;
    private ArrayList<String> factions;
    private ArrayList<String> qualities;
    private ArrayList<String> races;
    private ArrayList<String> locales;

    public String getPatch() {
        return patch;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }

    public ArrayList<String> getSets() {
        return sets;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public ArrayList<String> getFactions() {
        return factions;
    }

    public ArrayList<String> getQualities() {
        return qualities;
    }

    public ArrayList<String> getRaces() {
        return races;
    }

    public ArrayList<String> getLocales() {
        return locales;
    }
}
