package org.example.streams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamReduceDemo {

    public static void main(String[] args) {

        List<String> props = List.of("profile=native", "debug=true", "logging=warn", "interval=500");
        props.stream()
                .map(prop -> {
                    String[] part = prop.split("=", 2);
                    // key and value
                    return Map.of(part[0], part[1]);
                })
                .reduce(new HashMap<String, String>(), (m, kv) -> {
//                    System.out.println(kv);
                    System.out.println(m);
                    m.putAll(kv);
                    return m;
                })
                .forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
