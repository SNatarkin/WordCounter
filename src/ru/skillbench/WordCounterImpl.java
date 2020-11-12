package ru.skillbench;

import java.io.PrintStream;
import java.util.*;

public class WordCounterImpl implements WordCounter {
    private String text;
    private final Map<String, Long> map = new HashMap<>();

    @Override
    public void setText(String text) {
        this.text = text;

    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        try {
            text = text.toLowerCase();
            String[] tmp = text.split("<.+[^<]>|[ \n\r\t]+");
            for (String s : tmp) {
                if (!s.equals(""))
                    if (map.containsKey(s)) {
                        map.put(s, map.get(s) + 1);
                    } else {
                        map.put(s, 1L);
                    }


            }
            return map;
        } catch (NullPointerException e) {
            throw new IllegalStateException();
        }
    }


    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        try {
            List<Map.Entry<String, Long>> sortedList = sort(map, (o1, o2) -> {
                if (o1.getValue().equals(o2.getValue()))
                    return 0;
                if (o1.getValue() < o2.getValue())
                    return 1;
                else return -1;

            });
            return sortedList;
        } catch (NullPointerException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        List<Map.Entry<K, V>> sorted = new ArrayList<>(map.entrySet());
        sorted.sort(comparator);
        return sorted;
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        for (int i = 0; i < entries.size(); i++) {
            ps.println(entries.get(i).getKey() + " " + entries.get(i).getValue());
        }
    }
}
