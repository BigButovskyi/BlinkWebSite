package com.blink;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class JustForSimpleTesting {
    public static void main(String[] args) {
        List<Integer> treeSet = new ArrayList<>();
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);

        TreeSet<Integer> a = new TreeSet<>(treeSet);
        for (int i : a) {
            System.out.println(i);
        }
    }
}