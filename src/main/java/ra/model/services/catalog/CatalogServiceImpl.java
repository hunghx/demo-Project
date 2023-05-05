package ra.model.services.catalog;

import ra.model.config.ConnectionToDB;
import ra.model.entity.Catalog;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceImpl implements ICatalogService{
    @Override
    public List<Catalog> findAll() {
        List<Catalog> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_GetAllCatalog()}");
            ResultSet rs= callSt.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(rs.getString("id"));
                catalog.setName(rs.getString("name"));
                list.add(catalog);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionToDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean save(Catalog catalog) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_InsertNewCatalog(?,?)}");
            callSt.setString(1,catalog.getId());
            callSt.setString(2, catalog.getName());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public boolean update(Catalog catalog) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_UpdateCatalog(?,?)}");
            callSt.setString(1,catalog.getId());
            callSt.setString(2, catalog.getName());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DeleteCatalog(?)}");
            callSt.setString(1,id);
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public Catalog findById(String id) {
        Connection conn = null;
        Catalog catalog = new Catalog();
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindCatalogById(?)}");
            callSt.setString(1,id);
            ResultSet rs =callSt.executeQuery();
             while (rs.next()) {
                catalog.setId(rs.getString("id"));
                catalog.setName(rs.getString("name"));
            }
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            ConnectionToDB.closeConnection(conn);
        }
        return catalog;
    }

    @Override
    public List<Catalog> findByName(String name) {
        List<Catalog> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindByName(?)}");
            callSt.setString(1,name);
            ResultSet rs= callSt.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(rs.getString("id"));
                catalog.setName(rs.getString("name"));
                list.add(catalog);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionToDB.closeConnection(conn);
        }
        return list;
    }
}
