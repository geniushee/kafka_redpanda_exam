package com.example.redpanda_ex_24_06_13.global.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@MappedSuperclass
@Getter
@EqualsAndHashCode
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    public String getModuleName(){
        String simpleName = this.getClass().getSimpleName();
        return Character.toUpperCase(simpleName.charAt(0)) + simpleName.substring(1);
    }
}
