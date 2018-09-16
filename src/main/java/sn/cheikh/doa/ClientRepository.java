package sn.cheikh.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.cheikh.entities.Client;

public interface ClientRepository
        extends JpaRepository<Client, Long> {

}
