package com.kelompok4.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDto {
    @NotEmpty(message = "Nama barang tidak boleh kosong")
    private String name;

    @Size(min = 10, message = "Deksripsi barang tidak boleh kurang dari 10 huruf")
    @Size(max = 500, message = "Deksripsi barang tidak boleh lebih dari 2000 huruf")
    private String description;

    @Min(0)
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
