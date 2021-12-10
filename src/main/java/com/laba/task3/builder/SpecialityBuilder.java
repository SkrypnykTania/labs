package com.laba.task3.builder;

import com.laba.task1.Faculty;
import com.laba.task1.Speciality;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialityBuilder {

    private String code;
    private String name;
    private String info;

    private Faculty faculty;

    public SpecialityBuilder() {

    }
    public SpecialityBuilder withCode(String code){
        this.code=code;
        return this;
    }
    public SpecialityBuilder withName(String name){
        this.name=name;
        return this;
    }
    public SpecialityBuilder withFaculty(Faculty faculty){
        this.faculty=faculty;
        return this;

    }
    public SpecialityBuilder withInfo(String info){
        this.info=info;
        return this;
    }
    public Speciality build(){
        List<String> errors = new ArrayList<>();
        if (this.code==null|| this.code.isBlank()){
            errors.add("Код спеціальності  має   бути вказаним");
        }else {
            if (this.code.length()>10)  errors.add("Занадто довгий код");
            if (this.code.length()<2)  errors.add("Занадто короткий код");
        }


        if (this.name==null|| this.name.isBlank()){
            errors.add("Назва має бути вказана");
        }else {
            if (this.name.length()>100)  errors.add("Занадто довга назва");
            if (this.name.length()<2)  errors.add("Занадто коротка назва");
        }
        if (this.faculty==null){
            errors.add("Факультет має бути вказаним");
        }
        if (!errors.isEmpty()){
            String msg = errors.stream().collect(Collectors.joining(", "));
            msg = "Помилка! "+msg;
            throw new IllegalArgumentException(msg);
        }
        return new Speciality(code,name,info,faculty);
    }
}
