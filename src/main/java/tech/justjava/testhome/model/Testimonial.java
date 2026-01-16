package tech.justjava.testhome.model;

import jakarta.persistence.*;

@Entity
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private String name;
    private String role;
    private String company;

    // Constructors
    public Testimonial() {}

    public Testimonial(String text, String name, String role, String company) {
        this.text = text;
        this.name = name;
        this.role = role;
        this.company = company;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getText() { return text; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getCompany() { return company; }

    public void setText(String text) { this.text = text; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setCompany(String company) { this.company = company; }
}



