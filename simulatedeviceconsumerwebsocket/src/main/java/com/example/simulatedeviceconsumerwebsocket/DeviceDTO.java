package com.example.simulatedeviceconsumerwebsocket;


import java.util.UUID;

public class DeviceDTO{

    private UUID id;
    private String name;
    private String capacity;
    private String owner;

    public DeviceDTO() {
    }

    public DeviceDTO(UUID id, String name, String capacity, String owner) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity='" + capacity + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
