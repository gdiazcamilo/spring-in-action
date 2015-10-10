package spittr.data;

import java.util.List;

import spittr.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);
	Spittle findById(Long id);

	void save(String message, Double latitude, Double longitude);
}
