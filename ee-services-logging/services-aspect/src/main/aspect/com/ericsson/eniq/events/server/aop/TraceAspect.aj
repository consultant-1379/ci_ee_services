/**
 * 
 */
package com.ericsson.eniq.events.server.aop;

import java.util.logging.Level;

import org.aspectj.lang.ProceedingJoinPoint;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * @author eeipca
 *
 */
public aspect TraceAspect {
	// put stuff which can be ommitted from all traces here 
    protected pointcut omittedClasses() : 
    	!cflow(within(com.ericsson.eniq.events.server.logging.*)) &&
    	!execution (* com.ericsson.eniq.events.server.utils.JSONUtils.escapeValueForJSON(..)) &&
    	!cflow(within(TraceAspect)) &&
        !target (java.lang.Enum) &&
        !execution (* toString()) &&
        !execution (* hashCode()) &&
        !execution (* equals(..)) &&
        !execution (* has*()) &&
        !execution (* is*()) &&
        !execution (* isEmpty(..)) &&
        !execution (* asser*()) &&
        !execution (* *.__*Generated__.*());

	/**
	 * Pointcuts for API methods i.e. those with (@Path) or (@Path @GET @Produces)
	 */
	pointcut publicPaths():
				execution(@Path * com.ericsson.eniq.events.server.resources.*.*(..)) || 
				execution(@Path @GET @Produces * com.ericsson.eniq.events.server.resources.*.*(..)
			) && omittedClasses();
	
	/**
	 * Pointcut for public methods in services (and not API methods)
	 */
	pointcut publicServicesMethods() : 
        (execution (!private !protected * com.ericsson.eniq.events.server.*.*.*(..)) && omittedClasses() && !publicPaths());
	
	/**
	 * Point cut for all public, package private, protected and private methods.
	 */
	pointcut allMethods() : execution(* com.ericsson.eniq.events.server.*.*.*(..)) && omittedClasses() && !publicServicesMethods();
	
	/**
	 * Trace ENTER for API methods
	 * Trace Level INFO
	 */
    before() : publicPaths() {
    	traceEnter(Level.INFO, 
    			thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
					thisJoinPointStaticPart.getSignature().getName(),
					thisJoinPoint.getThis(),
					thisJoinPoint.getArgs());
    }

  
	
	/**
	 * Trace ENTER for all methods
	 * Trace Level FINER
	 */
	before() : allMethods() && !publicServicesMethods() {
		traceEnter(Level.FINER, 
				thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
				thisJoinPointStaticPart.getSignature().getName(),
				thisJoinPoint.getArgs());
	}
	
	/**
	 * Trace EXIT for API methods
	 * Trace Level INFO
	 * 
	 * Not using String as Path may return instance of BaseResource or JSON stirng.
	 */
	after() returning(Object returnResults) : publicPaths()  {
		traceExit(Level.INFO,
				thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
				thisJoinPointStaticPart.getSignature().getName(), 
				returnResults);
	}

  
	
	/**
	 * Trace EXIT for all methods
	 * Trace Level FINER
	 */
	after() returning(Object returningResult) : allMethods() && !publicServicesMethods() {
		traceExit(Level.FINER,
				thisJoinPointStaticPart.getSignature().getDeclaringTypeName(),
				thisJoinPointStaticPart.getSignature().getName(), 
				returningResult);
	}
	
	/**
	 * Trace enter method
	 * @param level The level to trace at
	 * @param className The class the method is being called on
	 * @param methodName The method being called
	 * @param args Method arguements, if any
	 */
	private void traceEnter(final Level level, final String className, final String methodName, final Object... args){
		if(ServicesLogger.isLevelActive(level)) {
			ServicesLogger.enter(level, className, methodName, args);
		}
	}
	
	/**
	 * Trace Exit method
	 * @param level The level to trace at
	 * @param className The class the method was called on
	 * @param methodName The method thas just finished
	 * @param returningResults Any return results (void methods return null)
	 */
	private void traceExit(final Level level, final String className, final String methodName, final Object returningResults){
		if(ServicesLogger.isLevelActive(level)) {
			ServicesLogger.exit(level, className, methodName, returningResults);
		}
	}
}
