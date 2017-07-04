package utils;

import Domains.SuitableDomains;

public class EnumIterator {

//    private static boolean isSuitable;

    public static boolean isDomainSuitable(String domain) {
        System.out.println("DOMAIN: " + domain); //del
        for (SuitableDomains sd : SuitableDomains.values()) {
            if (sd.toString().equals(domain)) {
                return true;
            }
        }
        return false;
    }

}
