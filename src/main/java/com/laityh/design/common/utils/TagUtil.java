package com.laityh.design.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagUtil {
    public static Set<String> tagObjectToTagSet(Object tagsObj) {
        Set<String> tags = new HashSet<>();
        if (tagsObj instanceof List<?>) {
            List<?> tmp = (List<?>) tagsObj;
            for (Object tag : tmp) {
                tags.add((String) tag);
            }
        }
        return tags;
    }
    public static List<String> tagObjectToTagList(Object tagsObj) {
        List<String> tags = new ArrayList<>();
        if (tagsObj instanceof List<?>) {
            List<?> tmp = (List<?>) tagsObj;
            for (Object tag : tmp) {
                tags.add((String) tag);
            }
        }
        return tags;
    }
}
