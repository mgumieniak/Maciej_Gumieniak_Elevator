package Data;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;


/**
 *  Interface DataBaseInterface provides below method to
 *  handle with storing data.
 *
 *  The elements in data base are separated by unique id.
 */
public interface DataBaseInterface<T> {

    /**
     * Add new object in data base, which unique id.
     *
     * @param id - unique int value enable separated objects in
     *           data base.
     */
    void add(int id);

    /**
     * Find specific object in data base.
     *
     * @param id - unique int value enable separated objects in
     *           data base.
     * @return - object in data base connected with id
     */
    T findObjectById(int id);

    /**
     * @return Collection with all objects which was added in data base
     */
    Collection<T> showAll();

    /**
     * @return ConcurrentHashMap which contain objects.
     */
    ConcurrentHashMap<Integer, T> getMap();
}
