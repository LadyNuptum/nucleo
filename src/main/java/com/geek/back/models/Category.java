package com.geek.back.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2,max = 255, message = "Name must be between 2 and 255 characters")
    @Column(nullable = false,unique = true,length = 255)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
//    @EqualsAndHashCode.Exclude
    private Set<Product> products = new HashSet<>();




}
