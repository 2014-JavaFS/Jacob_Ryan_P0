package net.revature.project_0.util.interfaces;


import java.util.List;

public interface Serviceable<O> {
    O lookup(List<O> databaseContents, int id);
    O create(O newObject);
    O findByID(int number);
}
