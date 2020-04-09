package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;


/**
 * Created by LAVentura on 07.04.20.
 */


public class MostClickedCategory {


    public static void main(String[] args) throws IOException {

        List<String> items = Files.readAllLines(new File("data/input1.txt").toPath(), Charset.defaultCharset());
        // arquivo um convertido em lista dos itens

        String filePath = "data/input2.txt"; // arquivo dois convertido em um hashmap
        HashMap<String, String> itemsAndCategory = new HashMap<>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s*:\\s*", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                itemsAndCategory.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }

        List<String> categories = new ArrayList<>(); // lista só com as categorias dos itens

        for (int i = 0; i < items.size(); i++) {
            System.out.println("item " + items.get(i));
            System.out.println("hashmap " + itemsAndCategory.get(items.get(i)));
            categories.add(itemsAndCategory.get(items.get(i)));

        }

        HashMap<String, Integer> clicks = new HashMap<>(); // hashmap categoria : clique

        for (int i = 0; i < categories.size(); i++) {

            if (clicks.containsKey(categories.get(i))) {// adicionando a categoria
                clicks.put(categories.get(i), clicks.get(categories.get(i)) + 1);
                //clicks.replace(categories.get(i),clicks.get(categories.get(i)),clicks.get(categories.get(i)+1));

            } else {

                clicks.put(categories.get(i), 1);
            }

            // clicks.putIfAbsent(categories.get(i), 1); // adicionando a categoria se nao existir


        }

        System.out.println(Arrays.asList(clicks));

        Object[] a = clicks.entrySet().toArray();
        Arrays.sort(a, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        for (Object e : a) {
            System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
                    + ((Map.Entry<String, Integer>) e).getValue());
        }

    }

    public MostClickedCategory() throws IOException {
    }
}