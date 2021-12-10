package com.laba.task1;



import java.io.Serializable;
import java.util.*;

/**
 * клас Спеціальність
 */
public class Speciality  implements Serializable,Comparable<Speciality> {
    /**
     * код
     */
    private String code;
    /**
     * назва
     */
    private String name;
    /**
     * опис
     */
    private String info;

    /**
     * факультет
     */
    private Faculty faculty;

    /**
     * конструтор без параметрів
     */
    public Speciality() {
    }

    /**
     *
     * @param code код
     * @param name назва
     * @param info опис
     * @param faculty факультет
     */
    public Speciality(String code, String name, String info, Faculty faculty) {
        this.code = code;
        this.name = name;
        this.info = info;
        this.faculty = faculty;
    }

    /**
     * повертає код спеціальності
     * @return код спеціальності
     */
    public String getCode() {
        return code;
    }

    /**
     * встановлює код спеціальності
     * @param code код спеціальності
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * повертає назву спеціальності
     * @return назва  спеціальності
     */
    public String getName() {
        return name;
    }
    /**
     * встановлює назву спеціальності
     * @param name назва спеціальності
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * повертає опис спеціальності
     * @return опис  спеціальності
     */
    public String getInfo() {
        return info;
    }

    /**
     * встановлює опис спеціальності
     * @param info опис спеціальності
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * повертає факульктет
     * @return факультет
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * встановлює факультет
     * @param faculty факультет
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    /**
     * порівнює об`єкти. вони однакові, якщо мають однакові коди
     * @param o об`єкт з яким порівнюють
     * @return true якщо однакові, інакше falce
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speciality)) return false;
        Speciality that = (Speciality) o;
        return Objects.equals(code, that.code);
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
        return "Speciality{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", faculty=" + faculty +
                '}';
    }

    /**
     * порівнює об`єкти по коду
     * @param o з яким порівнють
     * @return 0 - якщо рівні, 1-якщо більший, -1 якщо меньший
     */

    @Override
    public int compareTo(Speciality o) {
        if (o==null) return 1;
        return code.compareTo(o.getCode());
    }
}
