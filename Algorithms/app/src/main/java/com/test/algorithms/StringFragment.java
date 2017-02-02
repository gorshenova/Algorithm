package com.test.algorithms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StringFragment extends Fragment {

    public static StringFragment get(){
        StringFragment frag =  new StringFragment();
        return frag;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Methods return the frequent symbol in the string
        String s = "11122233356897";
        frequentSymbol_1(s);
        frequentSymbol_2(s);
    }

    private Character frequentSymbol_1(String s) {
        Map<Character, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = treeMap.get(c);
            if (val == null) {
                treeMap.put(c, 1);
            } else {
                treeMap.put(c, val + 1);
            }
        }
        Set<Map.Entry<Character, Integer>> mapEntries = treeMap.entrySet();
        List<Map.Entry<Character, Integer>> aList = new ArrayList<>(mapEntries);
        Collections.sort(aList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map.Entry<Character, Integer> mapEntry = aList.get(aList.size() - 1);
        Character c = mapEntry.getKey();
        int cFrequent = mapEntry.getValue();
        Log.d("mylogs", "frequentSymbol_1: " + c + ", frequent: " + cFrequent);
        return c;
    }


    /**
     * Method print the number of occurrences of each character
     * For example, The string equals "Elephant".
     * Then the program will give - e=2 l=1 p=1 h=1 a=1 n=1 t=1.
     *
     * @return frequent symbol
     */
    private Character frequentSymbol_2(String s) {
        int i, h;
        char[] toChar = s.toLowerCase().toCharArray(); //get string to char array
        int length = toChar.length;
        char[] charStore = new char[length]; // store characters found
        int[] charCountStore = new int[length]; // store count of characters found
        for (i = 0; i < length; i++) {
            charCountStore[i] = 0; //initialize count to 0
        }

        Log.d("mylogs", "Character frequency");
        for (i = 0; i < length; i++) {
            if (isInArray(charStore, toChar[i])) {
                for (h = 0; h < length; h++) {
                    if (Character.valueOf(charStore[h]).equals(toChar[i])) {
                        //increase count if found in the charStore array
                        charCountStore[h]++;
                    }
                }
            } else {
                charStore[i] = toChar[i];
                charCountStore[i]++;
            }
        }

        Log.d("mylogs", "Output character frequency");
        for (i = 0; i < length; i++) {
            String fr = charStore[i] + " = " + charCountStore[i];
            Log.d("mylogs", fr);
        }

      /* Output character frequency
       1 = 3
       \u0000 = 0
       \u0000 = 0
       2 = 3
       \u0000 = 0
       \u0000 = 0
       3 = 3
       \u0000 = 0
       \u0000 = 0
       5 = 1
       6 = 1
       8 = 1
       9 = 1
       7 = 1*/

        //found max count in array charStoreCount
        int maxIndex = 0;
        int maxValue = 0;
        for (i = 0; i < length; i++) {
            if (charCountStore[i] > maxValue) {
                maxValue = charCountStore[i];
                //save index
                maxIndex = i;
            }
        }

        //get frequent character
        char c = charStore[maxIndex];
        Log.d("mylogs", "frequentSymbol_2: " + c + ", frequent: " + maxValue);
        /*
        mylogs: frequentSymbol_2: 1, frequent: 3
        */
        return c;
    }

    public boolean isInArray(char[] a, char c) {
        //check if character in array, uf yes, return true
        boolean found = false;
        for (int i = 0; i < a.length; i++) {
            if (Character.valueOf(a[i]).equals(c)) {
                found = true;
                break;
            }
        }
        return found;
    }

}
