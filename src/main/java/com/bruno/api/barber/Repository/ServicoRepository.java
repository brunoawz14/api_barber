package com.bruno.api.barber.Repository;

import com.bruno.api.barber.Entity.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servicos, Long> {
    Optional<Servicos> findByNome(String Nome);
}
