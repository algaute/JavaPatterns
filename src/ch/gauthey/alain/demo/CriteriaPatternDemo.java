package ch.gauthey.alain.demo;

import java.util.ArrayList;
import java.util.List;

import ch.gauthey.alain.patterns.structural.filter.AndCriteria;
import ch.gauthey.alain.patterns.structural.filter.Criteria;
import ch.gauthey.alain.patterns.structural.filter.CriteriaFemale;
import ch.gauthey.alain.patterns.structural.filter.CriteriaMale;
import ch.gauthey.alain.patterns.structural.filter.CriteriaSingle;
import ch.gauthey.alain.patterns.structural.filter.OrCriteria;
import ch.gauthey.alain.patterns.structural.filter.Person;

public class CriteriaPatternDemo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);
        Criteria singleAndFemale = new AndCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));

        System.out.println("\nSingle And Females: ");
        printPersons(singleAndFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons) {

        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName() + ", Gender : " + person.getGender()
                    + ", Marital Status : " + person.getMaritalStatus() + " ]");
        }
    }

}
