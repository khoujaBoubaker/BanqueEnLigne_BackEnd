package mbdemoapp.Fournisseurs.Comptes.CleNumComptes;

import org.springframework.stereotype.Service;

@Service
public class InumCompteImpl implements INumCompte {
	
	int Min=500;
	int Max=1000000000;
	
	
	int nombreAleatoire = Min + (int)(Math.random()*((Max-Min)+1))+(int)(Math.random()*((Max)+1))+(int)(Math.random()*((Min)+1));

	@Override
	public String NumCompte(String nom, String prenom, int id) {
		// TODO Auto-generated method stub
		String lib1=nom.subSequence(0,3).toString();
		String lib2=prenom.subSequence(0,3).toString();
		
		String s1="CCP";
		String s2="00000-".concat(""+id).concat(lib1).concat(lib2).concat(""+nombreAleatoire);
		String s3=s1+s2;
		return s3;
		
		
		
	}

}
