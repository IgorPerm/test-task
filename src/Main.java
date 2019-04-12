import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private final static Map<String, Integer> map = new HashMap<>();
    private static int N = 10;

    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите имя файла: ");
            String filename = reader.readLine();
            reader.close();
            FileReader fileReader = new FileReader(filename);

//читаем файл и соxраняем в StringBuilder, затем разбиваем на отдельные слова и в нижнем регистре заносим в массив
            StringBuilder sb = new StringBuilder();
            while (fileReader.ready()) {
                sb.append((char) fileReader.read());
            }
            fileReader.close();
            String t = sb.toString().replaceAll("(?U)[\\p{Punct}|\\s|\\d)]+", " ").trim().toLowerCase();
            String[] s = t.split(" ");

//считаем кол-во совпадений каждого элемента и заносим в словарь
            int count = 0;
            for (String s1 : s) {
                for (String s2 : s) {
                    if (s1.equals(s2)) {
                        count++;
                    }
                }
                map.putIfAbsent(s1, count);
                count = 0;
            }

//передаем map в новый список и сортируем список по значениям map, сортируем по убыванию
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
            entryList.sort(Map.Entry.comparingByValue());
            Collections.reverse(entryList);

//выводим в консоль 10 самыx упоминаемыx слов
            for (Map.Entry<String, Integer> pair : entryList) {
                System.out.println(pair.getKey() + " " + pair.getValue());
                N--;
                if (N == 0) break;
            }
        } catch (Exception e){e.printStackTrace();}
    }
}