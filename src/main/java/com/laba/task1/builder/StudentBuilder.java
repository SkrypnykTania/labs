package com.laba.task1.builder;

import com.laba.task1.Group;
import com.laba.task1.Student;

import java.time.LocalDate;

/**
 * class StudentBuilder
 */
public class StudentBuilder {
    private Student student;

    /**
     * створює  StudentBuilder
     */
    public StudentBuilder() {
        this.student = new Student();
    }

    /**
     * додає імя
     * @param firstName імя
     * @return StudentBuilder
     */
    public StudentBuilder withFirstName(String firstName){
        this.student.setFirstName(firstName);
        return this;
    }

    /**
     * додає прізвище
     * @param lastName прізвище
     * @return StudentBuilder
     */
    public StudentBuilder withLastName(String lastName){
        this.student.setLastName(lastName);return this;
    }

    /**
     * додає номер залікової книжки
     * @param creditBook номер залікової книжки
     * @return StudentBuilder
     */
    public StudentBuilder withCreditBook(String creditBook){
        this.student.setCreditBook(creditBook);
        return this;
    }

    /**
     *  додає групу
     * @param group  група
     * @return StudentBuilder
     */
    public StudentBuilder withGroup(Group group){
        this.student.setGroup(group);return this;
    }

    /**
     * додає дату народження
     * @param date дата народження
     * @return StudentBuilder
     */
    public StudentBuilder withBirthDay(LocalDate date ){
        this.student.setBirthDay(date);return this;

    }
    public Student build(){
        return this.student;
    }

}
