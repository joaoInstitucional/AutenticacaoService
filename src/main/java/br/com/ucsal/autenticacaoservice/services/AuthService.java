package br.com.ucsal.autenticacaoservice.services;

import br.com.ucsal.autenticacaoservice.entities.Credential;
import br.com.ucsal.autenticacaoservice.repositories.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private CredentialRepository repository;

    public boolean authenticate(String email, String password) {
        Optional<Credential> opt = repository.findByEmail(email);
        if(opt.isEmpty()) return false;

        Credential cred = opt.get();
        // Exemplo com BCrypt
       return BCrypt.checkpw(password, cred.getPasswordHash());
    }

    /*public void register(String email, String rawPassword, Long userId) {
       String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        Credential cred = new Credential();
        cred.setEmail(email);
        cred.setPasswordHash(hashed);
        cred.setUserId(userId);
        repository.save(cred);
    }*/
}
