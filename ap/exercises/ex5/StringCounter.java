package ap.exercises.ex5;

import java.util.ArrayList;
import java.util.List;

public class StringCounter {

    private final List<String> items;
    private final List<Integer> counts;

    public StringCounter() {
        items = new ArrayList<>();
        counts = new ArrayList<>();
    }

    public void add(String s) {
        int index = items.indexOf(s);
        if (index == -1) {
            items.add(s);
            counts.add(1);
        } else {
            counts.set(index, counts.get(index) + 1);
        }
    }

    public int getCount(String s) {
        int index = items.indexOf(s);
        return index == -1 ? 0 : counts.get(index);
    }

    public List<String> getTop(int k) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            entries.add(new Entry(items.get(i), counts.get(i)));
        }

        entries.sort((a, b) -> Integer.compare(b.count, a.count));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, entries.size()); i++) {
            result.add(entries.get(i).value);
        }
        return result;
    }

    public void printTop(int k) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            entries.add(new Entry(items.get(i), counts.get(i)));
        }

        entries.sort((a, b) -> Integer.compare(b.count, a.count));

        for (int i = 0; i < Math.min(k, entries.size()); i++) {
            Entry e = entries.get(i);
            System.out.println(e.value + " -> " + e.count);
        }
    }

    private static class Entry {
        String value;
        int count;

        Entry(String value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
