package spittr.data;

import spittr.Spitter;

/**
 * Created by elgut on 26/09/2015.
 */
public interface SpitterRepository {
    Spitter save(Spitter spittler);
    Spitter findByUsername(String username);
}
