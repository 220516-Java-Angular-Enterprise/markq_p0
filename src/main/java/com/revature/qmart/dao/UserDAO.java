package com.revature.qmart.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserDAO implements CrudDAO {
    String path = "src/main/resources/database/user.txt";

    @Override
    public void save(Object obj) {
        try {
            File file  = new File(path);

            // checked exception, have to handle immediately
            FileWriter fw = new FileWriter(file, true);
            fw.write(obj.toString());
            fw.close();
        }  catch (IOException e) {
            throw new RuntimeException("An error has occurred when writing to a file.");
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public Object getById(String id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
