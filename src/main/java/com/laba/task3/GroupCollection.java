package com.laba.task3;

import com.laba.task1.Group;
import com.laba.task1.Speciality;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class GroupCollection {

    /**
     * сортує список груп по коду
     * @param groups список груп
     * @return відсортований список
     */
    public static List<Group> sort(List<Group> groups){
        List<Group> sorted = new ArrayList<>(groups);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * сортує список груп по коду через Stream
     * @param groups список груп
     * @return відсортований список
     */
    public static  List<Group> sort8(List<Group> groups){
        return groups.stream().sorted().collect(Collectors.toList());
    }

    /**
     * сортує список груп по курсу
     * @param groups список груп
     * @return відсортований список
     */
    public static  List<Group> sortByCourse(List<Group> groups){
        List<Group> sorted = new ArrayList<>(groups);
        Comparator<Group> comparator = new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                if (o1==null && o2==null) return 0;
                if (o1!=null && o2==null) return 1;
                if (o1==null && o2!=null) return -1;
                if (Integer.compare(o1.getCourse(), o2.getCourse())==0){
                    return o1.getCode().compareTo(o2.getCode());
                }
                return Integer.compare(o1.getCourse(), o2.getCourse());
            }
        };
        Collections.sort(sorted,comparator);
        return sorted;

    }
    /**
     * сортує сортує список груп по курсу через Stream
     * @param groups список груп
     * @return відсортований список
     */
    public static  List<Group> sortByCourse8(List<Group> groups){

        return groups.stream().sorted((s1,s2)->
                {
                    if (s1==null && s2==null) return 0;
                    if (s1!=null && s2==null) return 1;
                    if (s1==null && s2!=null) return -1;
                    if (Integer.compare(s1.getCourse(),s2.getCourse())==0) {
                        return s1.getCode().compareTo(s2.getCode());
                    }
                    return Integer.compare(s1.getCourse(),s2.getCourse());
                }
        ).collect(Collectors.toList());

    }

    /**
     * групує список груп по спеціальності
     * @param groups список груп
     * @return Map<Group,List<Student>>
     */
    public static Map<Speciality,List<Group>> groupByGroup(List<Group> groups){
        Map<Speciality,List<Group>> map = new TreeMap<>();
        for(Group group:groups){
            List<Group> list = map.getOrDefault(group.getSpeciality(),new ArrayList<>());
            list.add(group);
            map.put(group.getSpeciality(),list);
        }
        return map;

    }

    /**
     * групує список груп по спеціальності
     * @param groups список груп
     * @return Map<Group,List<Student>>
     */
    public static Map<Speciality,List<Group>> groupByGroup8(List<Group> groups){
        return groups.stream().collect(groupingBy(Group::getSpeciality));

    }

}
