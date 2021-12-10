package com.laba.task1.builder;

import com.laba.task1.Group;
import com.laba.task1.Speciality;

/**
 *  class GroupBuilder
 */
public class GroupBuilder {
    private Group group;

    /**
     * створює  Group Builder
     */
    public GroupBuilder() {
        this.group = new Group();
    }

    /**
     * додає код
     * @param code код
     * @return GroupBuilder
     */
    public GroupBuilder withCode(String code){
        this.group.setCode(code);
        return this;
    }

    /**
     * додає курс
     * @param course курс
     * @return  GroupBuilder
     */
    public GroupBuilder withCourse(int course){
        this.group.setCourse(course);
        return this;
    }

    /**
     * додає спеціальність
     * @param speciality спеціальність
     * @return  GroupBuilder
     */
    public GroupBuilder withSpeciality(Speciality speciality){
        this.group.setSpeciality(speciality);
        return this;
    }

    /**
     * повертає екземпляр Group
     * @return Group
     */
    public Group build(){
        return this.group;
    }
}
