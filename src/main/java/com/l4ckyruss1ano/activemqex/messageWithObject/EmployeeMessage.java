package com.l4ckyruss1ano.activemqex.messageWithObject;

import java.io.Serializable;

public class EmployeeMessage implements Serializable {
    private String name;

    public EmployeeMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
