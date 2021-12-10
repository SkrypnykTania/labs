package com.laba.task1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.*;

/**
 * class Group
 * група студентів
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "Group")
public class Group implements Serializable,Comparable<Group> {
    /**
     * код групи
     */
    String code;

    /**
     * курс
     */
    int course;


    /**
     * спеціальність
     */
    Speciality speciality;

    /**
     * конструктор без параметрів
     */
    public Group() {
    }

    /**
     * конструктор з параметрами
     * @param code код
     * @param course курс
     * @param speciality  спеціальність
     */
    public Group(String code, int course, Speciality speciality) {
        this.code = code;
        this.course = course;
        this.speciality = speciality;
    }

    /**
     * повертає курс
     * @return курс
     */
    public int getCourse() {
        return course;
    }

    /**
     * встановлює курс
     * @param course курс
     */
    public void setCourse(int course) {
        this.course = course;
    }

    /**
     * повертає код
     * @return код
     */
    public String getCode() {
        return code;
    }

    /**
     * встановлює код
     * @param code код
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * повертає спеціальність
     * @return спеціалність
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    /**
     * встановлює спеціальність
     * @param speciality спеціальність
     */
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    /**
     * порівнює з іншим обєктом
     * @param o іший обєкт
     * @return true якщо коди рівні, інакше false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return Objects.equals(code, group.code);
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
        return "Group{" +
                "code='" + code + '\'' +
                ", course=" + course +
                ", speciality=" + speciality +
                '}';
    }
    /**
     * порівнює об`єкти по коду
     * @param o з яким порівнють
     * @return 0 - якщо рівні, 1-якщо більший, -1 якщо меньший
     */
    @Override
    public int compareTo(Group o) {
        if (o==null) return 1;
        return code.compareTo(o.getCode());
    }
}
