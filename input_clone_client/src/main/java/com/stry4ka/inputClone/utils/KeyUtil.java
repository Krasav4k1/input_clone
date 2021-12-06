package com.stry4ka.inputClone.utils;

import java.util.Map;
import java.util.TreeMap;

public class KeyUtil {

    public static final Map<Integer, Integer> macMapper;
    static {
        // Key is C++ vkCode, value is Java vkCode
        macMapper = new TreeMap<>();
        macMapper.put(524, 17); // wind -> ctrl
        macMapper.put(17, 157); // ctrl -> command
    }
    
    public static int getMacVkCode(int vkCode) {
        return macMapper.getOrDefault(vkCode, vkCode);
    }
}