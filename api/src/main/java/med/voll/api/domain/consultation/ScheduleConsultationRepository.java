package med.voll.api.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleConsultationRepository extends JpaRepository<ScheduleConsultation, Long> {
}
