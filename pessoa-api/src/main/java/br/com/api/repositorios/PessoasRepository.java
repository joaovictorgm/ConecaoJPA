package br.com.api.repositorios;

import br.com.api.entidades.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {
}
