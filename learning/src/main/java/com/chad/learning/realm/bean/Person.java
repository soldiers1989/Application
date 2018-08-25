package com.chad.learning.realm.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject {

    @PrimaryKey //@Primarykey表示该字段是主键
    private String id;
    private String name;
    private String age;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }
}
