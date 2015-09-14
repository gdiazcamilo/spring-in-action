package soundsystem;

import java.util.HashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TrackCounter {

	private HashMap<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();
	
	@Pointcut("execution(** soundsystem.CompactDisc.playTrack(int)) && args(trackNumber)")
	public void trackPlayed(int trackNumber){}

	@Around("trackPlayed(trackNumber)")
	public void watchPlayTrack(ProceedingJoinPoint jp, int trackNumber) {
		try {
			countTrack(trackNumber);
			jp.proceed();
		} catch (Throwable e) {
		}
	}
	
	//@AfterReturning("trackPlayed(trackNumber)")
	private void countTrack(int trackNumber) {
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, ++currentCount);
	}

	public int getPlayCount(int trackNumber) {
		return trackCounts.containsKey(trackNumber) 
				? trackCounts.get(trackNumber) : 0;
	}
	
}
