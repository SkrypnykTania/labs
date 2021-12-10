package com.laba.task1;

import java.io.Serializable;
import java.util.*;

/**
 * клас Факультет
 */
public class Faculty  implements Serializable,Comparable<Faculty> {
    /**
     * код факультету
     */
    private String code;
    /**
     * назва факультету
     */
    String name;
    /**
     * декан
     */
    String dean;

    private List<Group> groups;


    /**
     * конструтор без параметрів
     */
    public Faculty() {
    }

    /**
     * конструктор з параметрами
     * @param code код
     * @param name назва
     * @param dean декан
     */
    public Faculty(String code, String name, String dean) {
        this.code = code;
        this.name = name;
        this.dean = dean;
    }

    /**
     * повертає код факультету
     * @return код факультету
     */
    public String getCode() {
        return code;
    }

    /**
     * встановлює код факультету
     * @param code код факультету
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * повертає назву факультету
     * @return назва  факультету
     */
    public String getName() {
        return name;
    }

    /**
     * встановлює назву факультету
     * @param name назва факультету
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * повертає назву декану
     * @return назву декану
     */
    public String getDean() {
        return dean;
    }

    /**
     * встановлює назву дакану
     * @param dean декан
     */
    public void setDean(String dean) {
        this.dean = dean;
    }


    /**
     * порівнює об`єкти. вони однакові, якщо мають однакові коди
     * @param o об`єкт з яким порівнюють
     * @return true якщо однакові, інакше falce
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(code, faculty.code);
    }

    /**
     * розраховує хеш код
     * @return хеш код
     */
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    /**
     * повертає строковий вираз
     * @return строковий вираз
     */
    @Override
    public String toString() {
        return "Faculty{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dean='" + dean + '\'' +

                '}';
    }

    /**
     * порівнює об`єкти по коду
     * @param o з яким порівнють
     * @return 0 - якщо рівні, 1-якщо більший, -1 якщо меньший
     */
    @Override
    public int compareTo(Faculty o) {
        if (o==null) return 1;
        return code.compareTo(o.getCode());
    }
}
