package ar.edu.unlp.info.oo2.accesobd;

import java.util.Collection;
import java.util.List;

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

    public void LogIn(String password){
        if (this.password.equals(password)){
            this.isLogged = true;
        } else {
            throw new RuntimeException("Wrong password");
        }
    }

    public void LogOut(){
        if (this.isLogged){
            this.isLogged = false;
        } else {
            throw new RuntimeException("Not logged in");
        }
    }

    @Override
    public Collection<String> getSearchResults(String queryString) {
        if (this.isLogged){
            return this.access.getSearchResults(queryString);
        } else {
            throw new RuntimeException("Not logged in");
        }
    }

    @Override
    public int insertNewRow(List<String> rowData) {
        if (this.isLogged){
            return this.access.insertNewRow(rowData);
        } else {
            throw new RuntimeException("Not logged in");
        }
    }


}
