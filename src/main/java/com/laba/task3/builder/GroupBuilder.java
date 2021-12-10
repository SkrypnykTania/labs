package com.laba.task3.builder;

import com.laba.task1.Group;
import com.laba.task1.Speciality;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupBuilder {
    private String code;
    private int course;
    private Speciality speciality;

    public GroupBuilder() {

    }
    public GroupBuilder withCode(String code){
        this.code=code;
        return this;
    }

    public GroupBuilder withCourse(int course){
        this.course =course;
        return this;
    }
    public GroupBuilder withSpeciality(Speciality speciality){
        this.speciality=speciality;
        return this;
    }
    public Group build(){

        List<String> errors = new ArrayList<>();
        if (this.code==null|| this.code.isBlank()){
            errors.add("Код групи має   бути вказаним");
        }else {
            if (this.code.length()>10)  errors.add("Занадто довгий код");
            if (this.code.length()<2)  errors.add("Занадто короткий код");
        }


        if (this.course<1 || this.course>5){
            errors.add("Курс має бути в межах 1..5");
        }
        if (this.speciality==null){
            errors.add("Спеціальність має бути вказана");
        }
        if (!errors.isEmpty()){
            String msg = errors.stream().collect(Collectors.joining(", "));
            msg = "Помилка! "+msg;
            throw new IllegalArgumentException(msg);
        }
        return new Group(code,course,speciality);
    }
}
