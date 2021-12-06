package com.stry4ka.inputClone.utils;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

public class KeyUtil {

    public static final Map<Integer, Integer> mapper;
    static {
        // Key is C++ vkCode, value is Java vkCode
        mapper = new TreeMap<>();
        mapper.put(189, 45); // -
        mapper.put(187, 61); // =
        mapper.put(219, 91); // [
        mapper.put(221, 93); // ]
        mapper.put(220, 92); // \
        mapper.put(186, 59); // ;
        mapper.put(13, 10); // ENTER
        mapper.put(160, 16); // L SHIFT
        mapper.put(161, 16); // R SHIFT
        mapper.put(188, 44); // ,
        mapper.put(190, 46); // .
        mapper.put(191, 47); // /
        mapper.put(162, 17); // L CTRL
        mapper.put(163, 17); // R CTRL
        mapper.put(91, 524); // WINDOWS
        mapper.put(164, 18); // ALT
        mapper.put(165, 18); // ALT
        mapper.put(45, 155); // INSERT
        mapper.put(46, 127); // DEL
    }

    public static int getJavaKeyCode(int CPPKeyCode) {
        return mapper.getOrDefault(CPPKeyCode, CPPKeyCode);
    }
}
