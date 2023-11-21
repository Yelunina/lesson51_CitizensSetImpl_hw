package ait.citizens.dao;

import ait.citizens.model.Person;

import java.time.LocalDate;
import java.util.*;

public class CitizensSetImpl implements Citizens {
    private TreeSet<Person> idList;
    private TreeSet<Person> lastNameList;
    private TreeSet<Person> ageList;
    private static Comparator<Person> lastNameComparator = (p1, p2) -> {
        int res = p1.getLastName().compareTo(p2.getLastName());
        return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
    };
    private static Comparator<Person> ageComparator = (p1, p2) -> {
        int res = Integer.compare(p1.getAge(), p2.getAge());
        return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
    };

    public CitizensSetImpl() {
        idList = new TreeSet<>();
        lastNameList = new TreeSet<>(lastNameList);
        ageList = new TreeSet<>(ageComparator);
    }

    public CitizensSetImpl(List<Person> citizens) {
        this();
        citizens.forEach(p -> add(p));
    }

    // O(log(n))
    @Override
    public boolean add(Person person) {
        if (person != null && idList.add(person) && lastNameList.add(person) && ageList.add(person)) {
            return true;
        } else {
            return false;
        }
    }

    // O(log(n))
    @Override
    public boolean remove(int id) {
        Person victim = find(id);
        if (victim == null) {
            return false;
        }
        idList.remove(victim);
        ageList.remove(victim);
        lastNameList.remove(victim);
        return true;
    }

    // O(log(n)
    @Override
    public Person find(int id) {
        Person pattern = new Person(id, null, null, null);
        Person person = idList.ceiling(pattern);
        return pattern.equals(person) ? person : null;
    }

    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        //TODO
        return null;
    }

    @Override
    public Iterable<Person> find(String lastName) {
        //TODO
        return null;
    }

    //O(1)
    @Override
    public Iterable<Person> getAllPersonsSortedByLastName() {
        return lastNameList;
    }

    //O(1)
    @Override
    public Iterable<Person> getAllPersonsSortedByAge() {
        return ageList;
    }

    //O(1)
    @Override
    public int size() {
        return idList.size();
    }

    //O(1)
    @Override
    public Iterable<Person> getAllPersonSortedById() {
        return idList;
    }
}
