package mbdemoapp.services.graphiques;

import java.util.List;

import mbdemoapp.graphes.Domain.Graphe;

public interface IGrapheMetier {
	
	public Graphe graphesOperations(int id);
	
	public List<Graphe> graphesOperationsParPeriodeI(int id);

}
