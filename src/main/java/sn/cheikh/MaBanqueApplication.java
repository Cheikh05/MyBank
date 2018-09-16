package sn.cheikh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sn.cheikh.doa.ClientRepository;
import sn.cheikh.doa.CompteRepository;
import sn.cheikh.doa.OperationRepository;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {
    // Utilisation de Spring Data
    // injecter l'objet
    @Autowired
    private ClientRepository    clientR;
    @Autowired
    private CompteRepository    compteR;
    @Autowired
    private OperationRepository operationR;

    public static void main( String[] args ) {
        SpringApplication.run( MaBanqueApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {

        /*
         * Client c1 = clientR.save( new Client( "Kane", "kheush05@gmail.com" )
         * ); Compte cp1 = compteR.save( new CompteCourant( "c1", new Date(),
         * 450000, c1, 12000 ) ); Client c2 = clientR.save( new Client( "Barry",
         * "barry.aissatou@gmail.com" ) ); Compte cp2 = compteR.save( new
         * CompteEpargne( "c2", new Date(), 275500, c2, 5.5 ) ); Operation op1 =
         * operationR.save( new Retrait( new Date(), 12000, cp1 ) ); Operation
         * op2 = operationR.save( new Depot( new Date(), 3111, cp1 ) );
         * Operation op3 = operationR.save( new Retrait( new Date(), 10000, cp1
         * ) ); Operation op4 = operationR.save( new Depot( new Date(), 5000,
         * cp1 ) );
         * 
         * Operation op5 = operationR.save( new Retrait( new Date(), 12000, cp2
         * ) ); Operation op6 = operationR.save( new Depot( new Date(), 3111,
         * cp2 ) ); Operation op7 = operationR.save( new Retrait( new Date(),
         * 10000, cp2 ) ); Operation op8 = operationR.save( new Depot( new
         * Date(), 5000, cp2 ) );
         */
    }
}