package google.com.univer.utils.text_utils;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Sergey on 21.09.2016.
 */
public class TextUtils {

    public static TreeMap<String, Integer> getWords(ArrayList<String> text, boolean isAscending) {
        HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
        ValueComparator bvc = new ValueComparator(wordsMap, isAscending);

        BreakIterator breakIterator = BreakIterator.getWordInstance();
        for(String textLine : text){
            breakIterator.setText(textLine);
            int lastIndex = breakIterator.first();
            while (BreakIterator.DONE != lastIndex) {
                int firstIndex = lastIndex;
                lastIndex = breakIterator.next();
                if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(textLine.charAt(firstIndex))) {
                    String newWorld = textLine.substring(firstIndex, lastIndex).toLowerCase();
                    if(newWorld.contains("0") || newWorld.contains("1") || newWorld.contains("2") ||
                            newWorld.contains("3") ||
                            newWorld.contains("4") ||
                            newWorld.contains("5") ||
                            newWorld.contains("6") ||
                            newWorld.contains("7") ||
                            newWorld.contains("8") ||
                            newWorld.contains("9")){
                        continue;
                    }
                    if(wordsMap.containsKey(newWorld)){
                        wordsMap.put(newWorld, wordsMap.get(newWorld) + 1);
                    }else{
                        wordsMap.put(newWorld, 1);
                    }
                }
            }
        }
        TreeMap<String, Integer> sortedMap = new TreeMap<>(bvc);
        sortedMap.putAll(wordsMap);
        return sortedMap;
    }

    public static HashMap<String, Integer> getMapWords(ArrayList<String> fullText){
        HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        for(String textLine : fullText){
            breakIterator.setText(textLine);
            int lastIndex = breakIterator.first();
            while (BreakIterator.DONE != lastIndex) {
                int firstIndex = lastIndex;
                lastIndex = breakIterator.next();
                if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(textLine.charAt(firstIndex))) {
                    String newWorld = textLine.substring(firstIndex, lastIndex).toLowerCase();
                    if(newWorld.contains("0") || newWorld.contains("1") || newWorld.contains("2") ||
                            newWorld.contains("3") ||
                            newWorld.contains("4") ||
                            newWorld.contains("5") ||
                            newWorld.contains("6") ||
                            newWorld.contains("7") ||
                            newWorld.contains("8") ||
                            newWorld.contains("9")){
                        continue;
                    }
                    if(wordsMap.containsKey(newWorld)){
                        wordsMap.put(newWorld, wordsMap.get(newWorld) + 1);
                    }else{
                        wordsMap.put(newWorld, 1);
                    }
                }
            }
        }
        return wordsMap;
    }

    public static Map<String, Integer> getMapWordsSortedByNumbers(Map<String, Integer> map, boolean isUpend){
        ValueComparator bvc = new ValueComparator(map, isUpend);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(bvc);
        sortedMap.putAll(map);
        return sortedMap;
    }

    public static Map<String, Integer> getMapWordsSortedByAlphavit(Map<String, Integer> map){
        return new TreeMap<>(map);
    }

    static class ValueComparator implements Comparator<String> {
        private Map<String, Integer> base;
        private boolean isAscending;

        public ValueComparator(Map<String, Integer> base, boolean isAscending) {
            this.base = base;
            this.isAscending = isAscending;
        }

        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return (isAscending ? -1 : 1);
            } else {
                return isAscending ? 1 : -1;
            }
        }
    }

}
