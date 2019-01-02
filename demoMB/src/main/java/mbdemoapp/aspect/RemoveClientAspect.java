package mbdemoapp.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mbdemoapp.admins.services.IAdministrateurService;
import mbdemoapp.util.logs.ILogger;

@Component
@Aspect
public class RemoveClientAspect {
	
	@Autowired
	@Qualifier("file")
	private ILogger logger;
	
	
    
    
    
    @Before("execution "
			+ "(* mbdemoapp.clients.services.IClientService.*(..))")
	public void logBefore() {
		logger.log(""
				+ "La methode"
				+ "est sur le point de "
				+ "s'executer");
	}
    
	@After("execution "
			+ "(* mbdemoapp.clients.services.IClientService.*(..))")
	public void logAfter() {
		logger.log(""
				+ "La méthode "
				+ "s'est terminé");
	}
	
	@AfterReturning(returning="result", value="execution "
			+ "(* mbdemoapp.clients.services.IClientService.*(..))")
		public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.log("L'execution de la méthode " +joinPoint.getSignature().getName());
		logger.log("Le client  passé en paramètre est:");		
		for (Object o : joinPoint.getArgs()) {
			logger.log("     - "+o.toString());
		}
		
	}
	
	
	@AfterThrowing(throwing="error", value="execution "
			+ "(* mbdemoapp.clients.services.IClientService.*(..))")
	public void logAfterReturning(JoinPoint joinPoint, Throwable error) {
		logger.log("L'execution de l'application s'est terminée brusquement");
		logger.log("L'execution s'est arrêtée à cause de : "+error.getMessage());
	}
	

}