package sn.cheikh.metier;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.cheikh.doa.CompteRepository;
import sn.cheikh.doa.OperationRepository;
import sn.cheikh.entities.Compte;
import sn.cheikh.entities.CompteCourant;
import sn.cheikh.entities.Depot;
import sn.cheikh.entities.Operation;
import sn.cheikh.entities.Retrait;

@Service
@Transactional
public class IBanqueImpl implements IBanque {
    @Autowired
    private CompteRepository    compteRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    // Consulter un compte
    public Optional<Compte> consulterCompte( String codeCompte ) {
        Optional<Compte> c = compteRepository.findById( codeCompte );
        // tester si l'objet est null
        if ( !c.isPresent() )
            throw new RuntimeException( "Compte instrouvable" );
        return c;
    }

    @Override
    // Deposer dans un compte
    public void deposer( String codeCompte, double montant ) {
        Optional<Compte> c = consulterCompte( codeCompte );
        Compte cp = c.get();
        Depot depot = new Depot( new Date(), montant, cp );

        operationRepository.save( depot );
        // update solde
        cp.setSolde( cp.getSolde() + montant );
        compteRepository.save( cp );
    }

    @Override

    public void retirer( String codeCompte, double montant ) {
        double sommeInitial = 0;
        Optional<Compte> c = consulterCompte( codeCompte );
        // Récuperation du compte
        Compte cp = c.get();
        if ( cp instanceof CompteCourant )
            sommeInitial = ( (CompteCourant) cp ).getDecouverte();
        if ( cp.getSolde() + sommeInitial < montant )
            throw new RuntimeException( "Solde insuffisant" );

        Retrait r = new Retrait( new Date(), montant, cp );
        operationRepository.save( r );
        cp.setSolde( cp.getSolde() - sommeInitial );
        // update solde
        compteRepository.save( cp );

    }

    @Override
    public void virement( String codeCompte1, String codeCompte2, double montant ) {
        if ( codeCompte1.equals( codeCompte2 ) )
            throw new RuntimeException( "Impossible de faire un virement sur le meme compte" );
        retirer( codeCompte1, montant );
        deposer( codeCompte2, montant );

    }

    @Override
    public Page<Operation> listeOperations( String codeCompte, int page, int size ) {
        // TODO Auto-generated method stub
        return operationRepository.pageOperations( codeCompte, new PageRequest( page, size ) );
    }

}
