package com.sharath.petsimulator.dto;

public class AdoptRequest {
    private String name;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }



    private Long   typeId;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
