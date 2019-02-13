package livrocaz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import livrocaz.model.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer>{

	
	@Query(value = "SELECT * FROM livre WHERE id_livre = ?1", nativeQuery = true)
	Livre findLivreById(int id);
	
	@Query(value = "SELECT * FROM livre WHERE livre.titre_livre= ?1", nativeQuery = true)
	Optional<Livre> findLivreByName(String nom);
		

}
