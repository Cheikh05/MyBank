package sn.cheikh.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.cheikh.entities.Compte;

public interface CompteRepository
        extends JpaRepository<Compte, String> {

}
