package br.com.ucsal.autenticacaoservice.repositories;

import br.com.ucsal.autenticacaoservice.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Optional<Credential> findByEmail(String email);
}
