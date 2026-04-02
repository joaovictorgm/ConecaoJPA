package br.com.api.repositorios;

import br.com.api.entidades.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimaisRepository extends JpaRepository <Animal, Long> {
}
