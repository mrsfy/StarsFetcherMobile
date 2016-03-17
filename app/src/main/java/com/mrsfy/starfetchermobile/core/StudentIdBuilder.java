package com.mrsfy.starfetchermobile.core;

import com.mrsfy.starfetchermobile.core.enums.StudentIdBuildTypes;

import java.math.BigInteger;

/**
 * Created by mrsfy on 13.02.2016.
 */
public class StudentIdBuilder {
    private StudentIdBuildTypes type;
    private int currentId;
    private int minYear;
    private int maxYear;

    public StudentIdBuilder(){

    }

    public StudentIdBuilder setType(StudentIdBuildTypes type){
        this.type = type;
        return this;
    }

    public StudentIdBuilder setCurrentId(String currentId){
        this.currentId = Integer.parseInt(currentId);
        return this;
    }
    public StudentIdBuilder setMinYear(int minYear){
        this.minYear = minYear;
        return this;
    }
    public StudentIdBuilder setMaxYear(int maxYear){
        this.maxYear = maxYear;
        return this;
    }

    public String build(){
        int id = currentId;
        switch (type){
            case INCREASE:
                id++;
                break;
            case DECREASE:
                id--;
                break;
            case RANDOM:

                break;
        }

        return Integer.toString(id);
    }
}
