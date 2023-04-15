package com.ASWLogistic.constant;

public final class GeneralConstants {
    private GeneralConstants() {

    }

    public static final String INDEX = "index";
    public static final String CREATE = "create";
    public static final String EDIT = "edit";




    //Logger
    public static String startLog(String methodName, String serviceName) {
        return "Start executing " + methodName + " method from " + serviceName;
    }

    public static String finishLog(String methodName, String serviceName) {
        return "Finish executing " + methodName + " method from " + serviceName;
    }

}
