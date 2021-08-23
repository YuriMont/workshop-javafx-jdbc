
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);
            int linhas = st.executeUpdate();
            if(linhas>0){
                System.out.println("Excluido com sucesso!");
            }else{
                throw new DbException("Erro inesperado nenhuma linha foi alterada");
            }
        }catch(SQLException e){
            throw new DbException("Erro: "+e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ? ORDER BY Nome");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dep = instatieteDepartment(rs);
                return dep;
            }
            return null;
        }catch(SQLException e){
            throw new DbException("Erro: "+e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            
            List<Department>list = new ArrayList<>();
            Map<Integer, Department>map = new HashMap<>();
            while(rs.next()){
                Department dep = map.get(rs.getInt(1));
                if(dep == null){
                    dep = instatieteDepartment(rs);
                    map.put(rs.getInt(1), dep);
                }
                list.add(dep);
            }
            return list;
        }catch(SQLException e){
            throw new DbException("Erro: "+e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
    private Department instatieteDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();
        dep.setId(rs.getInt(1));
        dep.setName(rs.getString(2));
        return dep;
    }
    
}
