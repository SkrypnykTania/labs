package com.laba.task3;

import com.laba.task1.Faculty;
import com.laba.task1.Speciality;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class SpecialityCollection {
    /**
     * сортує список спеціальностей  по коду
     * @param specialities список спеціальностей
     * @return відсортований список
     */
    public static List<Speciality> sort(List<Speciality> specialities){
        List<Speciality> sorted = new ArrayList<>(specialities);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * сортує список спеціальностей  по коду
     * @param specialities список спеціальностей
     * @return відсортований список
     */
    public static  List<Speciality> sort8(List<Speciality> specialities){
        return specialities.stream().sorted().collect(Collectors.toList());
    }

    /**
     * сортує список спеціальностей по назві
     * @param specialities список спеціальностей
     * @return відсортований список
     */
    public static  List<Speciality> sortByName(List<Speciality> specialities){
        List<Speciality> sorted = new ArrayList<>(specialities);
        Comparator<Speciality> comparator = new Comparator<Speciality>() {
            @Override
            public int compare(Speciality o1, Speciality o2) {
                if (o1==null && o2==null) return 0;
                if (o1!=null && o2==null) return 1;
                if (o1==null && o2!=null) return -1;

                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(sorted,comparator);
        return sorted;

    }
    /**
     * сортує список спеціальностей по назві через Stream
     * @param specialities список спеціальностей
     * @return відсортований список
     */
    public static  List<Speciality> sortByName8(List<Speciality> specialities){

        return specialities.stream().sorted((o1,o2)->
                {
                    if (o1==null && o2==null) return 0;
                    if (o1!=null && o2==null) return 1;
                    if (o1==null && o2!=null) return -1;

                    return o1.getName().compareTo(o2.getName());
                }
        ).collect(Collectors.toList());

    }

    /**
     * групує спеціальностей груп по факультету
     * @param specialities список спеціальностей
     * @return Map<Faculty,List<Speciality>>
     */
    public static Map<Faculty,List<Speciality>> groupByGroup(List<Speciality> specialities){
        Map<Faculty,List<Speciality>> map = new TreeMap<>();
        for(Speciality speciality:specialities){
            List<Speciality> list = map.getOrDefault(speciality.getFaculty(),new ArrayList<>());
            list.add(speciality);
            map.put(speciality.getFaculty(),list);
        }
        return map;

    }

    /**
     * групує спеціальностей груп по факультету
     * @param specialities список спеціальностей
     * @return Map<Faculty,List<Speciality>>
     */
    public static Map<Faculty,List<Speciality>> groupByGroup8(List<Speciality> specialities){
        return specialities.stream().collect(groupingBy(Speciality::getFaculty));

    }
}
