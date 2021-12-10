package com.laba.task3.builder;


import com.laba.task1.Group;
import com.laba.task1.Student;




import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentBuilder {

    private String creditBook;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Group group;

    public StudentBuilder() {

    }
    public StudentBuilder withFirstName(String firstName){
        this.firstName=firstName;
        return this;
    }
    public StudentBuilder withLastName(String lastName){

        this.lastName=lastName;
        return this;
    }
    public StudentBuilder withCreditBook(String creditBook){
        this.creditBook=creditBook;
        return this;
    }
    public StudentBuilder withGroup(Group group){
        this.group=group;
        return this;
    }
    public StudentBuilder withBirthDay(LocalDate birthDay ){
        this.birthDay =birthDay;
        return this;

    }
    public Student build(){
        List<String> errors = new ArrayList<>();
        if (this.firstName==null|| this.firstName.isBlank()){
             errors.add("Ім`я має бути вказано");
        }else {
            if (this.firstName.length()>100)  errors.add("Занадто довге ім`я");
            if (this.firstName.length()<2)  errors.add("Занадто коротке ім`я");
        }

        if (this.lastName==null|| this.lastName.isBlank()){
            errors.add("Прізвище має бути вказано");
        } else {
            if (this.lastName.length()>100)  errors.add("Занадто довге прізвище");
            if (this.lastName.length()<2)  errors.add("Занадто коротке прізвище");
        }
        if (this.creditBook==null|| this.creditBook.isBlank()){
            errors.add("Номер залікової книжки має бути вказаним");
        }else {
            String creditBookPattern = "^\\d{6,12}$";
            if (!this.creditBook.matches(creditBookPattern)){
                errors.add("Не вірний формат номеру залікової книжки");
            }
        }

        if (this.birthDay==null){
            errors.add("Дата народження має бути вказана");

        }else {
            int current  = LocalDate.now().getYear();
            int year = this.birthDay.getYear();
            if ((current-year)<16)
                errors.add("Занадто молодий студент!");
        }
        if (this.group==null){
            errors.add("Група має бути вказана");
        }
        if (!errors.isEmpty()){
            String msg = errors.stream().collect(Collectors.joining(", "));
            msg = "Помилка! "+msg;
            throw new IllegalArgumentException(msg);
        }
        return new Student(creditBook,firstName,lastName,birthDay,group);
    }

}
