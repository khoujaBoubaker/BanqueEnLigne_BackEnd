package mbdemoapp.aspect;





import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mbdemoapp.util.logs.ILogger;


@Component
@Aspect
public class CompteAspect {
	
	
	@Autowired
	@Qualifier("file")
    private ILogger logger;	
	
	@Before("execution "
			+ "(* mbdemoapp.comptesCourant.Services.CompteCourantsService.*(..))")
		public void BeforeGestionClients(JoinPoint joinpoint) {
		
		logger.log("######### excecution de la methode"+joinpoint.getSignature());
		logger.log(joinpoint.getSignature().getName());
		for(Object o:joinpoint.getArgs())
			logger.log("-"+o.toString());
		
	}
	

	
	@After("execution "
			+ "(* mbdemoapp.comptesCourant.Services.CompteCourantsService.*(..))")
	public void logAfter() {
		logger.log(""
				+ "La methode d'ajout "
				+ "s'est terminé");
	}
	
	@AfterReturning(returning="result", value="execution "
			+ "(* mbdemoapp.comptes.services.ICompteService."
			+ "addCompte(..))")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.log("L'execution de la méthode " +joinPoint.getThis());
		logger.log("Le compte  passé en paramètre est:");		
		for (Object o : joinPoint.getArgs()) {
			logger.log("     - "+o.toString());
		}
		logger.log(!(result==null)? "l'ajout est fait avec succés":"l'ajout de client a echoué");
	}
	
	
	
	
	
	@AfterReturning(returning="result", value="execution "
			+ "(* mbdemoapp.comptesCourant.Services.CompteCourantsService.*(..))")
	public void logAfterReturningDelete(JoinPoint joinPoint, Object result) {
		logger.log("L'execution de la méthode " +joinPoint.getSignature().getName());
		logger.log("Le compte  passé en paramètre est:");		
		for (Object o : joinPoint.getArgs()) {
			logger.log("     - "+o.toString());
		}
		
	}
	

}
