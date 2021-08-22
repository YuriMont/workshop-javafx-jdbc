
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
       
    
    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO Department VALUES (DEFAULT, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());
            
            int linhas = st.executeUpdate();
            if(linhas>0){
                ResultSet rs = st.getGeneratedKeys();
                System.out.println("Inserido com sucesso!");
                if(rs.next()){
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else{
                throw new DbException("Erro inesperado nenhuma linha foi alterada");
            }
        }catch(SQLException e){
            throw new DbException("Erro: "+e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE department SET Nome = ? WHERE Id = ?",
            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());
            st.setInt(1, department.getId());
            
            int linhas = st.executeUpdate();
            if(linhas>0){
                System.out.println("Modificado com sucesso!");
            }
            else{
                throw new DbException("Erro inesperado nenhuma linha foi alterada");
            }
        }catch(SQLException e){
            throw new DbException("Erro: "+e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Department instatieteDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();
        dep.setId(rs.getInt(1));
        dep.setName(rs.getString(2));
        return dep;
    }
    
}
