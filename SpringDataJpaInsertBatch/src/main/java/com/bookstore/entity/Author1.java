package com.bookstore.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Author1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hilo_sequence_generator")
//    @GenericGenerator(
//            name = "hilo_sequence_generator",
//            strategy = "sequence",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hilo_seqeunce"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "50"),
//                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo")
//            }
//    )
    private Long id;

    private String name;
    private String genre;
    private int age;

//    @Version
//    private Short version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public Short getVersion() {
//        return version;
//    }
}
