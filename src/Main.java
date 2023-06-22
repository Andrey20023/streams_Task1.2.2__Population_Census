import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
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
//Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        long numberMinors = persons.stream().filter(x -> x.getAge() < 18).count();//
       //System.out.println("Количество несовершеннолетних: " + numberMinors);

// Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).

        Collection<String> surnamesСonscripts = persons.stream()
                .filter(x -> Sex.MAN.equals(x.getSex()) && x.getAge() > 18 && x.getAge() < 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
               //System.out.println("Количество призывников: " + surnamesСonscripts.size());

/*Получить отсортированный по фамилии список потенциально работоспособных людей
с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).*/

        Collection<String> surnamesЕmployees = persons.stream()
                .filter(x -> Education.HIGHER.equals(x.getEducation()))
                .filter(x -> (Sex.MAN.equals(x.getSex()) && x.getAge() >= 18 && x.getAge() <= 65)
                        || (Sex.WOMAN.equals(x.getSex()) && x.getAge() >= 18 && x.getAge() <= 60))
                .map(x -> x.getFamily())
                .sorted(Comparator.comparing(x->x))
                .collect(Collectors.toList());
               //System.out.println("Количество работоспособных людей: " + surnamesЕmployees.size());
    }

}