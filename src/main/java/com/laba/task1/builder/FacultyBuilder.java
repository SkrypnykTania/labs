package com.laba.task1.builder;

import com.laba.task1.Faculty;

/**
 *  клас FacultyBuilder
 */
public class FacultyBuilder {
    private Faculty faculty;

    /**
     * створує Faculty Builder
     */
    public FacultyBuilder() {
        this.faculty = new Faculty();
    }

    /**
     * додає код  факультету
     * @param code код факультету
     * @return FacultyBuilder
     */
    public FacultyBuilder withCode(String code){
        this.faculty.setCode(code);
        return this;
    }

    /**
     *  додає назву факультету
     * @param name  назва факультету
     * @return FacultyBuilder
     */
    public FacultyBuilder withName(String name){
        this.faculty.setName(name);
        return this;
    }

    /**
     * додає декана факультету
     * @param dean декан факультету
     * @return FacultyBuilder
     */
    public FacultyBuilder withDean(String dean){
        this.faculty.setDean(dean);
        return this;
    }

    /**
     * повертає екземпляр Faculty
     * @return Faculty
     */
    public Faculty build(){
        return this.faculty;
    }
}
