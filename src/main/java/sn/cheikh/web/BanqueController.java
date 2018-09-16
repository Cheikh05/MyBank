package sn.cheikh.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sn.cheikh.entities.Compte;
import sn.cheikh.entities.Operation;
import sn.cheikh.metier.IBanque;

@Controller
public class BanqueController {
    @Autowired
    private IBanque metier;

    @RequestMapping( "/bank" )
    public String index() {
        return "banque";
    }

    @RequestMapping( "/consulterCompte" )
    public String consulterCompte( Model model, String codeCompte,
            @RequestParam( name = "page", defaultValue = "0" ) int pageCourante,
            @RequestParam( name = "size", defaultValue = "4" ) int s ) {
        model.addAttribute( "codeCompte", codeCompte );
        try {
            Optional<Compte> c = metier.consulterCompte( codeCompte );
            Compte cp = c.get();
            model.addAttribute( "compte", cp );
            Page<Operation> pageOperation = metier.listeOperations( codeCompte, pageCourante, s );
            int[] indexPages = new int[pageOperation.getTotalPages()];
            model.addAttribute( "pageOperation", pageOperation.getContent() );
            model.addAttribute( "indexPages", indexPages );
            model.addAttribute( "pageCourante", pageCourante );
            model.addAttribute( "size", s );
            model.addAttribute( "codeCompte", codeCompte );
            return "banque";
        } catch ( Exception e ) {
            model.addAttribute( "exception", e );
            return "banque";
        }

    }

    @RequestMapping( value = "/saveOperation", method = RequestMethod.POST )
    public String saveOpertaion( Model model,
            String codeCompte, String typeOperation,
            String codeCompte2, Double montant ) {
        try {
            if ( typeOperation.equals( "VERS" ) ) {
                metier.deposer( codeCompte, montant );
            } else if ( typeOperation.equals( "RET" ) ) {
                metier.retirer( codeCompte, montant );
            } else {
                metier.virement( codeCompte, codeCompte2, montant );
            }
        } catch ( Exception e ) {
            model.addAttribute( "error", e );
            return "redirect:/consulterCompte?codeCompte=" + codeCompte + "&error=" + e.getMessage();
        }

        return "redirect:/consulterCompte?codeCompte=" + codeCompte;
    }

}
