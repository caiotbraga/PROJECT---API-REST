package med.voll.api.domain.doctor;

import med.voll.api.domain.Enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

  Page<Doctor> findAllByActiveTrue(Pageable pagination);


  @Query("""
      select d from Doctor d
      where 
      d.active = true
      and
      d.specialty = :specialty
      and
      d.id not in(
        select sc.doctor.id from ScheduleConsultation sc
        where sc.consultation_date = :date
      )
      order by rand()
      limit 1
      """)
  Doctor getRandomDoctorBySpecialtyOnFreeDate(Specialty specialty, LocalDateTime date);
}
