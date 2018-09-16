package sn.cheikh.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( "V" )
public class Depot extends Operation {

    public Depot() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Depot( Date dateOperation, double montant, Compte compte ) {
        super( dateOperation, montant, compte );
        // TODO Auto-generated constructor stub
    }

}
