package com.ltc.companymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "departments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id ;

   @Column(name = "Shirketin_Departmentleri",nullable = false,length = 10)
   String name ;

   @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
   List<Employee> employees;



}
