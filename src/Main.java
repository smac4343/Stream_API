import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                names.get(new Random().nextInt(names.size())),
                families.get(new Random().nextInt(families.size())),
                new Random().nextInt(100),
                Sex.values()[new Random().nextInt(Sex.values().length)],
                Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countOfMinors = persons.stream()
            .filter(p -> p.getAge() < 18)
            .count();

        List<String> conscripts = persons.stream()
            .filter(p -> p.getAge() >= 18 && p.getAge() <= 27 && p.getSex() == Sex.MAN)
            .map(Person::getFamily)
            .collect(Collectors.toList());

        List<Person> ableBodiedPersons = persons.stream()
            .filter(p -> p.getEducation() == Education.HIGHER)
            .filter(p -> p.getAge() >= 18)
            .filter(p -> (p.getSex() == Sex.WOMAN && p.getAge() <= 60) || (p.getSex() == Sex.MAN && p.getAge() <= 65))
            .sorted(Comparator.comparing(Person::getFamily))
            .collect(Collectors.toList());
    }
}