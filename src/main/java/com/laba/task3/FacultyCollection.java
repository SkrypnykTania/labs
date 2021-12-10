package com.laba.task3;

import com.laba.task1.Faculty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyCollection {
    /**
     * сортує список факультетів по коду
     * @param faculties список факультетів
     * @return відсортований список
     */
     public static List<Faculty> sort(List<Faculty> faculties){
         List<Faculty> list = new ArrayList<>(faculties);
         Collections.sort(list);
         return list;
     }
    /**
     * сортує список факультетів по коду через Stream
     * @param faculties список факультетів
     * @return відсортований список
     */
    public static List<Faculty> sort8(List<Faculty> faculties){
           return faculties.stream().sorted().collect(Collectors.toList());
    }

    /**
     * сортує список факультетів по назві
     * @param faculties список факультетів
     * @return відсортований список
     */
    public static List<Faculty> sortByName(List<Faculty> faculties){
        List<Faculty> list = new ArrayList<>(faculties);
        Comparator<Faculty> comparator = new Comparator<Faculty>() {
            @Override
            public int compare(Faculty o1, Faculty o2) {
                {
                    if (o1==null && o2==null) return 0;
                    if (o1!=null && o2==null) return 1;
                    if (o1==null && o2!=null) return -1;

                    return o1.getName().compareTo(o2.getName());
                }
            }
        };
        Collections.sort(list,comparator);
        return list;
    }
    /**
     * сортує список факультетів по коду через Stream
     * @param faculties список факультетів
     * @return відсортований список
     */
    public static List<Faculty> sortByName8(List<Faculty> faculties){
        return faculties.stream().sorted(
                (f1,f2)->{
                    if (f1==null && f2==null) return 0;
                    if (f1!=null && f2==null) return 1;
                    if (f1==null && f2!=null) return -1;
                    return f1.getName().compareTo(f2.getName());
                }
        ).collect(Collectors.toList());
    }

    /**
     * повертає список деканів
     * @param faculties списко факультетів
     * @return список деканів
     */
    public static List<String> getAllDeans(List<Faculty> faculties){
        List<String> list = new ArrayList<>();
        for(Faculty faculty:faculties){
            if (!list.contains(faculty.getDean())) list.add(faculty.getDean());
        }
        return list;
    }
    /**
     * повертає список деканів
     * @param faculties списко факультетів
     * @return список деканів
     */
    public static List<String> getAllDeans8(List<Faculty> faculties){
        return faculties.stream().map(f->f.getDean()).distinct().collect(Collectors.toList());
    }
}
