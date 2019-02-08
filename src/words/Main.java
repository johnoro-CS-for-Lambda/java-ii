package words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
  private static void countWords() {
    String declaration = "";
    try {
      InputStream stream = Main.class.getResourceAsStream("/declaration.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      declaration = br.readLine();
      br.close();
    } catch (IOException exception) {
      System.out.println("Error: ");
      System.out.println(exception);
    }
    
    HashMap<String, Integer> wordHashMap = new HashMap<>();

    declaration = declaration.replaceAll("[^[\\-\\p{Alnum}\\s]]", "");

    System.out.println();
    System.out.println(declaration);
    System.out.println();

    String[] words = declaration.split("\\s");

    for (String word : words) {
      if (wordHashMap.containsKey(word)) {
        wordHashMap.put(word, wordHashMap.get(word) + 1);
      } else {
        wordHashMap.put(word, 1);
      }
    }

    ArrayList<HashMap.Entry<String, Integer>> sortedWords = new ArrayList<>();
    sortedWords.addAll(wordHashMap.entrySet());

    Collections.sort(sortedWords, new Comparator<>() {
      @Override
      public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2) {
        return o2.getValue() - o1.getValue();
      }
    });

    int i = 0;
    for (HashMap.Entry<String, Integer> word : sortedWords) {
      if (++i > 50) {
        break;
      }
      System.out.println("Word #" + i + ": " + word.getKey() +
                        "\nCount: " + word.getValue());
      System.out.println();
    }
  }

  public static void main(String[] args) {
    countWords();
  }
}
