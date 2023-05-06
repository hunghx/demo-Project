package ra.model.services.product;

import ra.model.config.ConnectionToDB;
import ra.model.entity.Product;
import ra.model.services.catalog.CatalogServiceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    @Override
    public List<Product> findAll() {
        CatalogServiceImpl catalogService = new CatalogServiceImpl();
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_GetAllProduct()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCatalog(catalogService.findById(rs.getString("catalogId")));
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return products;
    }

    @Override
    public boolean save(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_InsertNewProduct(?,?,?,?)}");
            callSt.setString(1, product.getName());
            callSt.setDouble(2, product.getPrice());
            callSt.setInt(3, product.getQuantity());
            callSt.setString(4, product.getCatalog().getId());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
    }

    @Override
    public boolean update(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_UpdateProduct(?,?,?,?,?)}");
            callableStatement.setInt(1, product.getId());
            callableStatement.setString(2, product.getName());
            callableStatement.setDouble(3, product.getPrice());
            callableStatement.setInt(4, product.getQuantity());
            callableStatement.setString(5, product.getCatalog().getId());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_DeleteProduct(?)}");
            callSt.setInt(1, id);
            callSt.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
    }

    @Override
    public Product findById(Integer id) {
        CatalogServiceImpl catalogService = new CatalogServiceImpl();
        Product product = new Product();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_FindProductById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCatalog(catalogService.findById(rs.getString("catalogId")));
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return product;
    }

    public List<Product> searchByCatalog(int idCatalog) {
        CatalogServiceImpl catalogService = new CatalogServiceImpl();
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_FindByCatalog(?)}");
            callSt.setInt(1, idCatalog);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCatalog(catalogService.findById(rs.getString("catalogId")));
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
        return products;
    }

    public List<Product> findByName(String nameSearch) {
        CatalogServiceImpl catalogService = new CatalogServiceImpl();
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_FindByNameProduct(?)}");
            callSt.setString(1, nameSearch);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCatalog(catalogService.findById(rs.getString("catalogId")));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.closeConnection(connection);
        }
    }
}
