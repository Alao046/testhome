package tech.justjava.testhome.service;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.justjava.testhome.dto.TestimonialDto;
import tech.justjava.testhome.model.Testimonial;
import tech.justjava.testhome.repository.TestimonialRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialService(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    // ================= READ =================
    public List<TestimonialDto> getAllTestimonials() {
        return testimonialRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    public void addTestimonial(TestimonialDto dto) {
        testimonialRepository.save(toEntity(dto));
    }

    // ================= UPDATE =================
    public void updateTestimonial(Long id, TestimonialDto dto) {
        Testimonial existing = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        existing.setText(dto.getText());
        existing.setName(dto.getName());
        existing.setRole(dto.getRole());
        existing.setCompany(dto.getCompany());

        testimonialRepository.save(existing);
    }

    // ================= DELETE =================
    public void deleteTestimonial(Long id) {
        testimonialRepository.deleteById(id);
    }

    // ================= MAPPERS =================
    private TestimonialDto toDto(Testimonial entity) {
        return new TestimonialDto(
                entity.getId(),
                entity.getText(),
                entity.getName(),
                entity.getRole(),
                entity.getCompany()
        );
    }

    private Testimonial toEntity(TestimonialDto dto) {
        return new Testimonial(
                dto.getText(),
                dto.getName(),
                dto.getRole(),
                dto.getCompany()
        );
    }
}



