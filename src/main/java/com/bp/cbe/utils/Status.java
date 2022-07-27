package com.bp.cbe.utils;

/**
 * Utility to map entities and data
 *
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public class Status {
    private Status() {
        throw new IllegalStateException("Utility class");
    }

    public static String getState(String state) {
        switch (state) {
            case "A":
                return "Archived";
            case "D":
                return "Disable";
            case "E":
                return "Enable";
            default:
                return "None";
        }
    }
}
