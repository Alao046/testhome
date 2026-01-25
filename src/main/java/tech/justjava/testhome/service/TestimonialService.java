package tech.justjava.testhome.service;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.justjava.testhome.model.Testimonial;
import tech.justjava.testhome.repository.TestimonialRepository;

import java.util.List;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialService(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    // READ: get all testimonials
    public List<Testimonial> getAllTestimonials() {
        return testimonialRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


    // CREATE: add new testimonial
    public void addTestimonial(Testimonial testimonial) {
        testimonialRepository.save(testimonial);
    }

    // UPDATE: edit existing testimonial
    public void updateTestimonial(Long id, Testimonial updatedTestimonial) {
        Testimonial existing = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        existing.setText(updatedTestimonial.getText());
        existing.setName(updatedTestimonial.getName());
        existing.setRole(updatedTestimonial.getRole());
        existing.setCompany(updatedTestimonial.getCompany());

        testimonialRepository.save(existing);
    }

    // DELETE: delete testimonial
    public void deleteTestimonial(Long id) {
        testimonialRepository.deleteById(id);
    }
}
