package globalsolution.citizentech.trilhamagica.repository;

import globalsolution.citizentech.trilhamagica.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long> {
    List<ProfessorModel> findByEmail(String email);
}