package com.laba.task3.builder;

import com.laba.task1.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyBuilder {
    private String code;
    private String name;
    private String dean;

    public FacultyBuilder() {

    }
    public FacultyBuilder withCode(String code){
        this.code=code;
        return this;
    }
    public FacultyBuilder withName(String name){
        this.name = name;
        return this;
    }
    public FacultyBuilder withDean(String dean){
        this.dean = dean;
        return this;
    }
    public Faculty build(){
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
        if (this.dean==null|| this.dean.isBlank()){
            errors.add("Декан має бути вказаний");
        }else {
            if (this.name.length()>100)  errors.add("Занадто довга назва декана");
            if (this.name.length()<2)  errors.add("Занадто коротка назва декана");
        }

        if (!errors.isEmpty()){
            String msg = errors.stream().collect(Collectors.joining(", "));
            msg = "Помилка! "+msg;
            throw new IllegalArgumentException(msg);
        }
        return new Faculty(code,name,dean);
    }
}
