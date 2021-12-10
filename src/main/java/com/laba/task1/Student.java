package com.laba.task1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.laba.task2.converter.CustomLocalDateDeserializer;
import com.laba.task2.converter.CustomLocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**
 * class Student
 */
@JsonPropertyOrder({"creditBook", "firstName","lastName","birthDay","group"})
public class Student  implements Serializable,Comparable<Student> {
    /**
     * номер залікової книжки
     */
    @JsonProperty("creditBook")
    private String creditBook;
    /**
     * ім'я
     */
    @JsonProperty("firstName")
    private String firstName;
    /**
     * прізвище
     */
    @JsonProperty("lastName")
    private String lastName;


    /**
     * дата нарождення
     *
     *  */

    @JsonProperty("birthDay")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate birthDay;

    /**
     * група
     */

    private Group group;


    /**
     * конструктор без параметрів
     */
    public Student() {
    }

    /**
     * конструктор з парметрами
     * @param creditBook номер залікової книжки
     * @param firstName імя
     * @param lastName прізвище
     * @param birthDay дата народження
     * @param group група
     */
    public Student(String creditBook, String firstName, String lastName, LocalDate birthDay, Group group) {
        this.creditBook = creditBook;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.group = group;
    }

    /**
     * повертає номер залікової книжки
     * @return номер залікової книжки
     */
    public String getCreditBook() {
        return creditBook;
    }

    /**
     * встановлює  номер залікової книжки
     * @param creditBook  номер залікової книжки
     */
    public void setCreditBook(String creditBook) {
        this.creditBook = creditBook;
    }

    /**
     * повертає імя
     * @return імя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * встановлює імя
     * @param firstName імя
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * повертає прізвище
     * @return прізвище
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * встановлює прізвище
     * @param lastName прізвище
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * повертає групу
     * @return група
     */
    public Group getGroup() {
        return group;
    }

    /**
     * встановлює групу
     * @param group група
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * повертає дату народження
     * @return дата народження
     */
    public LocalDate getBirthDay() {
        return birthDay;
    }

    /**
     *  встановлює дату народження
     * @param birthDay дата народження
     */
    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }


    /**
     * порівнює з іншим обєктом
     * @param o іший обєкт
     * @return true якщо номери залікових книжок рівні, інакше false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(creditBook, student.creditBook);
    }

    /**
     * розраховує хеш код
     * @return хеш код
     */
    @Override
    public int hashCode() {
        return Objects.hash(creditBook);
    }

    /**
     * повертає строковий вираз
     * @return строковий вираз
     */
    @Override
    public String toString() {
        return "Student{" +
                "creditBook='" + creditBook + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group=" + (group==null? "":group) +
                ", birthDay=" + (birthDay==null?"": birthDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))+
                '}';
    }
    /**
     * порівнює об`єкти по коду
     * @param o з яким порівнють
     * @return 0 - якщо рівні, 1-якщо більший, -1 якщо меньший
     */
    @Override
    public int compareTo(Student o) {
        if (o==null) return 1;
        if (lastName.equals(o.getLastName())){
            return firstName.compareTo(o.getFirstName());
        }
        return lastName.compareTo(o.getLastName());
    }
}
