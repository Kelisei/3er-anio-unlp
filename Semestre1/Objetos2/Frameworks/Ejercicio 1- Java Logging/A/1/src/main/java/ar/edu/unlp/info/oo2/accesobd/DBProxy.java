package ar.edu.unlp.info.oo2.accesobd;

import java.util.Collection;
import java.util.List;
import java.util.logging.*;

public class DBProxy implements DatabaseAccess {
    private DatabaseAccess access;
    private String password;
    private boolean isLogged;

    public DBProxy(DatabaseAccess access, String password) {
        super();
        this.access = access;
        this.password = password;
        this.isLogged = false;
    }

    public void LogIn(String password) {
        if (this.password.equals(password)) {
            this.isLogged = true;
        } else {
            Logger.getLogger("id").log(Level.SEVERE, "access denied");
        }
    }

    public void LogOut() {
        if (this.isLogged) {
            this.isLogged = false;
        }
    }

    @Override
    public Collection<String> getSearchResults(String queryString) {
        if (this.isLogged) {
            Logger.getLogger("id").log(Level.INFO, "read of rows completed");
            return this.access.getSearchResults(queryString);
        } else {
            Logger.getLogger("id").log(Level.SEVERE, "access denied");
            throw new RuntimeException("access denied");
        }
    }

    @Override
    public int insertNewRow(List<String> rowData) {
        if (this.isLogged) {
            Logger.getLogger("id").log(Level.WARNING, "write of rows completed");
            return this.access.insertNewRow(rowData);
        } else {
            Logger.getLogger("id").log(Level.SEVERE, "access denied");
            throw new RuntimeException("access denied");
        }
    }

}
