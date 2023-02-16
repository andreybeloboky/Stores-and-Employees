package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UncoverJSONService {
    public String[] uncoverJSON(String s) {
        String[] things = new String[5];
        Pattern pattern = Pattern.compile(":\\w{1,},?");
        Matcher matcher = pattern.matcher(s);
        int count = 0;
        while (matcher.find()) {
            if (count != things.length - 1) {
                things[count] = (s.substring(matcher.start() + 1, matcher.end() - 1));
                count++;
            } else {
                things[count] = (s.substring(matcher.start() + 1, matcher.end()));
            }
        }
        return things;
    }
}
