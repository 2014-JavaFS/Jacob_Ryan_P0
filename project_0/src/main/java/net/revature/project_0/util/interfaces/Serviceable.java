package net.revature.project_0.util.interfaces;


import java.util.List;

public interface Serviceable<O> {
    List<O> lookup();
    O create(O newObject);
    O findByID(int number);
}
