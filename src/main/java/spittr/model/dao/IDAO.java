package spittr.model.dao;

/**
 * Created by norman on 25/03/17.
 */

public interface IDAO<E> {

    E findById(long id);

    E merge(E persistentE);

    void refresh(E persistentE);

    void remove(E persistentE);

    void persist(E persistentE);

}