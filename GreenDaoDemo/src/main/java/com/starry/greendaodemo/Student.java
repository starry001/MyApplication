package com.starry.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by starry on 2016/8/29.
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private long id;
    @Property(nameInDb = "sName")
    private String name;
    private int grade;

    @Generated(hash = 1423675768)
    public Student(long id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
