import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Toy implements Comparable<Toy> {
    String name;
    int price;
    String ageRange;
    String additionalInfo;

    public Toy(String name, int price, String ageRange, String additionalInfo) {
        this.name = name;
        this.price = price;
        this.ageRange = ageRange;
        this.additionalInfo = additionalInfo;
    }

    @Override
    public int compareTo(Toy other) {
        return Integer.compare(other.price, this.price);
    }

    @Override
    public String toString() {
        return name + " - " + price + " коп.";
    }
}

public class ToysAnalyzer {
    public static void main(String[] args) {
        List<Toy> toys = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("toys.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int price = Integer.parseInt(parts[1].trim());
                String ageRange = parts[2].trim();
                String additionalInfo = parts[3].trim();

                toys.add(new Toy(name, price, ageRange, additionalInfo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Сортуємо іграшки за вартістю
        Collections.sort(toys);

        // Знаходимо найбільш коштовні іграшки
        List<Toy> expensiveToys = new ArrayList<>();
        int maxPrice = toys.get(0).price;
        for (Toy toy : toys) {
            if (maxPrice - toy.price <= 1000) {  // 1000 коп. = 10 грн.
                expensiveToys.add(toy);
            } else {
                break;
            }
        }

        // Виводимо результат
        System.out.println("Найбільш коштовні іграшки:");
        for (Toy toy : expensiveToys) {
            System.out.println(toy);
        }
    }
}

