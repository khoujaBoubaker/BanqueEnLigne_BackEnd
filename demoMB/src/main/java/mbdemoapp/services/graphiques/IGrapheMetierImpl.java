package mbdemoapp.services.graphiques;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Operation;
import mbdemoapp.domain.Retraitt;
import mbdemoapp.domain.Versement;
import mbdemoapp.graphes.Domain.Graphe;
import mbdemoapp.repo.ChiffreAffairesRepo;

@Service
public class IGrapheMetierImpl implements IGrapheMetier {
	
	private static final DateFormat dateformat=new SimpleDateFormat("yyyy/MM/dd");
	
	@Autowired
	private ChiffreAffairesRepo chiffreAffaireRepo;

	@Override
	public Graphe graphesOperations(int id) {
		Date currentDate=new Date();
		List<Graphe> listeGraphes=new ArrayList<Graphe>();
		double total=0;
		double totalretrait=0;
		double totalversements=0;
		
		// TODO Auto-generated method stub
		Calendar c=Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE,-id);
		Date currentDatePlusI=c.getTime();
		for(Operation o : this.chiffreAffaireRepo.listeOperationsPourGraphique(currentDatePlusI)) {
			total=total+o.montant;
			if(o instanceof Retraitt) {
				System.out.println(o);
				totalretrait=totalretrait+o.montant;
			}else {
				if(o instanceof Versement) {
					System.out.println(o);
					totalversements=totalversements+o.montant;
				}
			}
		}
		
		Graphe graphe=new Graphe();
		graphe.setDate(currentDatePlusI);
		graphe.setMontantRetraits(totalretrait);
		graphe.setMontantVersements(totalversements);
		graphe.setMontantToutesOperations(total);
		System.out.println("afficher graphe :" +graphe);
		
	
		return graphe;
	}

	@Override
	public List<Graphe> graphesOperationsParPeriodeI(int id) {
		// TODO Auto-generated method stub
	    List<Graphe> graphesPeriode=new ArrayList<Graphe>();
	    
	    for(int i=id;i>0;i--) {
	    	Graphe graphe=this.graphesOperations(i);
	    	graphesPeriode.add(graphe);
	    	
	    }
	    
	    return graphesPeriode;
	    
	    
		
		
		
		
	}
	
	
	
	

}
