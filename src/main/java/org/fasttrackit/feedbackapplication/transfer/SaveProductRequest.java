package org.fasttrackit.feedbackapplication.transfer;

import javax.validation.constraints.NotNull;

public class SaveProductRequest {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SaveProductRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
