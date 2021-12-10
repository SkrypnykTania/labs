package com.laba.task3;

import com.laba.task1.Group;
import com.laba.task1.Student;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class StudentCollection {
    /**
     * сортує список студентів
     * @param students список студентів
     * @return відсортований список
     */
    public static List<Student> sort(List<Student> students){
      List<Student> sorted = new ArrayList<>(students);
      Collections.sort(sorted);
      return sorted;
    }

    /**
     * сортує список студентів через Stream
     * @param students список студентів
     * @return відсортований список
     */
    public static  List<Student> sort8(List<Student> students){
        return students.stream().sorted().collect(Collectors.toList());
    }

    /**
     * сортує список студентів по заліковій книжці
     * @param students список студентів
     * @return відсортований список
     */
    public static  List<Student> sortByCreditBook(List<Student> students){
        List<Student> sorted = new ArrayList<>(students);
        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1==null && o2==null) return 0;
                if (o1!=null && o2==null) return 1;
                if (o1==null && o2!=null) return -1;
                return o1.getCreditBook().compareTo(o2.getCreditBook());
            }
        };
        Collections.sort(sorted,comparator);
        return sorted;

    }
    /**
     * сортує список студентів по заліковій книжці через Stream
     * @param students список студентів
     * @return відсортований список
     */
    public static  List<Student> sortByCreditBook8(List<Student> students){

        return students.stream().sorted((s1,s2)->
                {
                    if (s1==null && s2==null) return 0;
                    if (s1!=null && s2==null) return 1;
                    if (s1==null && s2!=null) return -1;
                 return s1.getCreditBook().compareTo(s2.getCreditBook());
                }
        ).collect(Collectors.toList());

    }

    /**
     * групує список студентів по групі
     * @param students список студентів
     * @return Map<Group,List<Student>>
     */
    public static Map<Group,List<Student>> groupByGroup(List<Student> students){
        Map<Group,List<Student>> map = new TreeMap<>();
        for(Student student:students){
            List<Student> list = map.getOrDefault(student.getGroup(),new ArrayList<>());
            list.add(student);
            map.put(student.getGroup(),list);
        }
        return map;

    }

    /**
     * групує список студентів по групі
     * @param students список студентів
     * @return Map<Group,List<Student>>
     */
    public static Map<Group,List<Student>> groupByGroup8(List<Student> students){
       return students.stream().collect(groupingBy(Student::getGroup));

    }
}
