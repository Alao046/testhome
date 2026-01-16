package tech.justjava.testhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.justjava.testhome.model.Testimonial;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
}


