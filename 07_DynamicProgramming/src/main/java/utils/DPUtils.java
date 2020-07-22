/*
 * Copyright (c) 2018 VMware, Inc. All Rights Reserved.
 */

package utils;

public class DPUtils {
    //O(n) time
    public static boolean isPalindrome(String s, int start, int end) {
        if(start < 0 || end >= s.length()) {
            return false;
        }

        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
