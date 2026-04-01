package br.com.api.repositorios;

import br.com.api.entidades.Animais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimaisRepository extends JpaRepository <Animais, Long> {
}
