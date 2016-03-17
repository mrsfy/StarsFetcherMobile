package com.mrsfy.starfetchermobile.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mrsfy on 13.02.2016.
 */
public class StudentIdChecker {
    public static boolean check(String id){
        Pattern pattern = Pattern.compile("\\d{8}");

        Matcher matcher = pattern.matcher(id);

        return matcher.find();
    }
}
