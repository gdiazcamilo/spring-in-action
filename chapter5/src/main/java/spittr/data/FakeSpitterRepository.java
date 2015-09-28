package spittr.data;

import org.springframework.stereotype.Repository;
import spittr.Spitter;

/**
 * Created by elgut on 26/09/2015.
 */
@Repository
public class FakeSpitterRepository implements SpitterRepository {

    public Spitter save(Spitter spittler) {

        return spittler;
    }

    public Spitter findByUsername(String username) {
        return new Spitter(11L, "Spitter firstname", "second", username, "");

    }
}
