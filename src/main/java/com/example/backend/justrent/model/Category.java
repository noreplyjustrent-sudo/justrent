package com.example.backend.justrent.model;

import com.example.backend.justrent.services.Common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "categoryid", unique = true, nullable = false)
   private String id;
   private String shortCode="CAT-";
   private String name;
   private String description;


   public Category(String name, String description) {
       Common common = new Common();
       this.id = common.generateUserId(shortCode);
       this.name = name;
       this.description = description;
   }


    public Category() {
         Common common = new Common();
         this.id = common.generateUserId(shortCode);
    }


   

}
