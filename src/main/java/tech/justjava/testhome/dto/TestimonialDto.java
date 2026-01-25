package tech.justjava.testhome.dto;


public class TestimonialDto {

    private Long id;
    private String text;
    private String name;
    private String role;
    private String company;

    public TestimonialDto() {}

    public TestimonialDto(Long id, String text, String name, String role, String company) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.role = role;
        this.company = company;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
}
