package ro.deloittedigital.bootcampbackend.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.deloittedigital.bootcampbackend.entity.model.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}
