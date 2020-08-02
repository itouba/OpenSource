package com.itouba.obwork.entitry;

import java.io.Serializable;

public class Team implements Serializable {

    private static final long serialVersionUID = 8928604853823251967L;

    private int id;
    private String name;
    private int parentId;
    private String fullId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getFullId() {
        return fullId;
    }

    public void setFullId(String fullId) {
        this.fullId = fullId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", fullId='" + fullId + '\'' +
                '}';
    }
}
