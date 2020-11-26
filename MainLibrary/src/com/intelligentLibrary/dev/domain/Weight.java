package com.intelligentLibrary.dev.domain;


/**
 * Weight entity. @author MyEclipse Persistence Tools
 */

public class Weight  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Dept dept;
     private BookSort booksort;
     private Integer weight;


    // Constructors

    /** default constructor */
    public Weight() {
    }

    
    /** full constructor */
    public Weight(Dept dept, BookSort booksort, Integer weight) {
        this.dept = dept;
        this.booksort = booksort;
        this.weight = weight;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Dept getDept() {
        return this.dept;
    }
    
    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public BookSort getBooksort() {
        return this.booksort;
    }
    
    public void setBooksort(BookSort booksort) {
        this.booksort = booksort;
    }

    public Integer getWeight() {
        return this.weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
   








}