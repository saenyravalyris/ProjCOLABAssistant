package br.univille.projcolabassistant.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.AssistiveAccessory;

@Repository
public interface AssistiveAccessoryRepository extends JpaRepository<AssistiveAccessory, Long>{
	
	@Query("Select aa from AssistiveAccessory aa where aa.category.name like %:categoryFilter%")
	public List<AssistiveAccessory> findByCategoryName(@Param("categoryFilter") String categoryFilter);

=======
import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.projcolabassistant.model.AssistiveAccessory;

public interface AssistiveAccessoryRepository extends JpaRepository<AssistiveAccessory, Long> {
	
>>>>>>> 8a7139f2ab64aa6f3748bb59a5b848e2bcc46382
}
