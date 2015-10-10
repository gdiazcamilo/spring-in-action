package spittr.data;

import org.springframework.stereotype.Repository;
import spittr.Spittle;
import spittr.web.DuplicateSpittleException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by elgut on 26/09/2015.
 */
@Repository
public class FakeSpittleRepository implements SpittleRepository {
    public List<Spittle> findSpittles(long max, int count) {
        Spittle s1 = new Spittle(new Date(), "Spittle 1");
        Spittle s2 = new Spittle(new Date(), "Spittle 2");

        List<Spittle> spittles = new ArrayList<Spittle>();
        spittles.add(s1);
        spittles.add(s2);

        return spittles;
    }

    public Spittle findById(Long id) {
        return new Spittle(new Date(), "Spittle " + id);
    }

    public void save(String message, Double latitude, Double longitude) {
        if("primer spittle".equals(message)) {
            throw new DuplicateSpittleException();
        }
    }
}
