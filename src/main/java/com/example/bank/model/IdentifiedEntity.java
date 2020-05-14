package com.example.bank.model;

import java.io.Serializable;

public interface IdentifiedEntity extends Serializable {

    Long getId();

    void setId(Long id);
}