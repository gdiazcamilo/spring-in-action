package concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AudienceAround {
	
	@Pointcut("execution(** concert.Performance.perform(..))")
	public void performance() {}
	
	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint jp) {
		try {
			muteCellPhones();
			takeSeats();
			
			jp.proceed();
			
			applause();
		} catch (Throwable e) {
			demandRefund();
		}
	}
	
	public void muteCellPhones() {
		System.out.println("Mutting cell phones");
	}
	
	public void takeSeats() {
		System.out.println("Taking seats");
	}
	
	public void applause() {
		System.out.println("CLAP CLAP CLAP!!");
	}
	
	public void demandRefund() {
		System.out.println("Demanding Refund!");
	}
}
