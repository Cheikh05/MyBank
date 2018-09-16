package sn.cheikh.metier;

import java.util.Optional;

import org.springframework.data.domain.Page;

import sn.cheikh.entities.Compte;
import sn.cheikh.entities.Operation;

public interface IBanque {
    public Optional<Compte> consulterCompte( String codeCompte );

    public void deposer( String codeCompte, double montant );

    public void retirer( String codeCompte, double montant );

    public void virement( String codeCompte1, String codeCompte2, double montant );

    public Page<Operation> listeOperations( String codeCompte, int page, int size );

}
