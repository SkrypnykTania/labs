package com.laba.task1.builder;

import com.laba.task1.Faculty;
import com.laba.task1.Speciality;

/**
 * class SpecialityBuilder
 */
public class SpecialityBuilder {
    private Speciality speciality;

    /**
     * створює SpecialityBuilder
     */
    public SpecialityBuilder() {
        this.speciality = new Speciality();
    }

    /**
     * додає код
     * @param code код
     * @return SpecialityBuilder
     */
    public SpecialityBuilder withCode(String code){
        this.speciality.setCode(code);
        return this;
    }

    /**
     * додає назву
     * @param name  назва
     * @return SpecialityBuilder
     */
    public SpecialityBuilder withName(String name){
        this.speciality.setName(name);
        return this;
    }

    /**
     * додає факультет
     * @param faculty факультет
     * @return SpecialityBuilder
     */
    public SpecialityBuilder withFaculty(Faculty faculty){
        this.speciality.setFaculty(faculty);
        return this;
    }

    /**
     * додає опис
     * @param info опис
     * @return SpecialityBuilder
     */
    public SpecialityBuilder withInfo(String info){
        this.speciality.setInfo(info);
        return this;
    }

    /**
     * повертає екземпляр
     * @return Speciality
     */
    public Speciality build(){
        return this.speciality;
    }
}
