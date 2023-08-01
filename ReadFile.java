package DEV120_2_1_Tekiev;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class ReadFile {

    private Map<String, Integer> map = new TreeMap<>();
    
    public void read (File file) throws IOException {

        FileReader fileReader = new FileReader(file);

        int c;

        String s = "";

        while((c=fileReader.read())!=-1){

            s+= ((char) c);
        }

        fileReader.close();


        String[] strings = s.replace("\r\n", " ").split(" ");


        for(String a : strings) {

            a = a.toLowerCase().replaceAll("[,.!?():;«»—a-zA-Z]", "");

            if(map.containsKey(a)) {
                map.put(a, map.get(a)+1);
            }
            else{ map.put(a, 1); }
        }

        map.remove("");


        FileWriter fw1 = new FileWriter("D:\\report-by-alph.txt", true);

        fw1.append(print(map));

        fw1.flush();

        FileWriter fw2 = new FileWriter("D:\\report-by-alph-rev.txt", true);

        fw2.append(print(sortByAlphaRev(map)));

        fw2.flush();

        FileWriter fw3 = new FileWriter("D:\\report-by-freq.txt", true);

        fw3.append(print(sortByFreq(map)));

        fw3.flush();


    }

    public ArrayList<String> returnVocabulary () { // метод возвращаящий словарь


        final ArrayList<String> vocabulary = new ArrayList<>(map.keySet());

        return vocabulary;

    }


    public String print (Map<String, Integer> map) {         // вывод элелементов


        ArrayList<String> listOfKeys = new ArrayList<>(map.keySet());


        ArrayList<Integer> listOfValues = new ArrayList<>(map.values());

        double count = 0;

        for (Integer list: listOfValues){

            count += list;
        }

        DecimalFormat decimalFormat = new DecimalFormat( "#.#####" );

        String s = "";

        for (int i = 0; i < listOfKeys.size(); i++) {

            String result = decimalFormat.format(listOfValues.get(i)/count);

            s += "Слово: \"" + listOfKeys.get(i) + "\" " + " встречается с абсолютной частотой: " + listOfValues.get(i) + " и относительной частотой: " + result + "\n";

        }

        return s;
    }

    public Map<String, Integer> sortByAlphaRev (Map<String, Integer> map) {           // метод сортирующий по принципу обратного словаря

        Comparator<String> valueComparator = new Comparator<>() {

            public int compare(String k1, String k2) {

                int comp =  (new StringBuilder(k1).reverse().toString()).compareTo(new StringBuilder(k2).reverse().toString());
                if (comp == 0)
                    return 1;
                else
                    return comp;
            }
        };

        Map<String, Integer> sorted = new TreeMap<>(valueComparator);

        sorted.putAll(map);

        return sorted;

    }

    public Map<String, Integer> sortByFreq (Map<String, Integer> map) {   // метод сортирующий по убыванию частоты

        Comparator<String> valueComparator = new Comparator<>() {

            public int compare(String k1, String k2) {
                int comp = map.get(k2).compareTo(map.get(k1));
                if (comp == 0)
                    return 1;
                else
                    return comp;
            }
        };

        Map<String, Integer> sorted = new TreeMap<>(valueComparator);

        sorted.putAll(map);

        return sorted;

    }

}

