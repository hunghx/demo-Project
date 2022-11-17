package ra;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("GetAll")) {
            getAllProduct(request,response);
        } else if (action.equals("GetById")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            //Lay thong tin san pham theo productId
            Connection conn = null;
            CallableStatement callSt = null;
            Product pro = null;
            try {
                conn = ConnectionDB.openConnection();
                callSt = conn.prepareCall("{call getProductById(?)}");
                callSt.setInt(1,productId);
                ResultSet rs = callSt.executeQuery();
                pro = new Product();
                if (rs.next()){
                    pro.setProductId(rs.getInt("ProductId"));
                    pro.setProductName(rs.getString("ProductName"));
                    pro.setProductImage(rs.getString("ProductImage"));
                    pro.setProductStatus(rs.getBoolean("ProductStatus"));
                }
                CallableStatement callSt2 = conn.prepareCall("{call getSubImagesById(?)}");
                callSt2.setInt(1,productId);
                ResultSet rs2 = callSt2.executeQuery();
                while (rs2.next()){
                    pro.getListImage().add(rs2.getString("ImageLink"));
                }
                callSt2.close();

            }catch (SQLException ex){
                ex.printStackTrace();
            }finally {
                ConnectionDB.closeConnection(conn,callSt);
            }
            //set pro vao request vao chuyen sang productDetail.jsp
            request.setAttribute("pro",pro);
            request.getRequestDispatcher("views/productDetail.jsp").forward(request,response);
        }
    }

    public void getAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listPro = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getAllProduct()}");
            ResultSet rs = callSt.executeQuery();
            listPro = new ArrayList<>();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductId(rs.getInt("ProductId"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setProductImage(rs.getString("ProductImage"));
                pro.setProductStatus(rs.getBoolean("ProductStatus"));
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        request.setAttribute("listPro", listPro);
        request.getRequestDispatcher("views/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("Create")) {
            Product proNew = new Product();
            proNew.setProductName(request.getParameter("productName"));
            proNew.setProductStatus(Boolean.parseBoolean(request.getParameter("status")));
            //Lay du lieu anh tu request day vao db va folder
            //db: chua link den anh
            //folder: chua anh upload
            //B1: Tao thu muc chua anh upload
            String pathFolderImage = "D:/RikkeiAcademy/SourceCode/JSP_Servlet_UploadImage/src/main/webapp/images";
            File file = new File(pathFolderImage);
            if (!file.exists()) {
                file.mkdir();
            }
            //B2. Lay du lieu anh tu request, add duong dan vao doi tuong proNew, ghi anh vao folder images
            for (Part part : request.getParts()) {
                if (part.getName().equals("image")) {
                    //part nay chua anh chinh cua product
                    //--set duong dan anh chinh vao doi tuong product can them moi
                    proNew.setProductImage(part.getSubmittedFileName());
                    //--ghi anh chinh vao folder images
                    part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                } else if (part.getName().equals("subImages")) {
                    //part nay chua anh phu cua product
                    //--set duong dan anh phu vao doi tuong product can them moi
                    proNew.getListImage().add(part.getSubmittedFileName());
                    //--Ghi anh phu vao folder images
                    part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                }
            }
            //B3. Ket noi CSDL them du lieu vao bang Product va Images
            Connection conn = null;
            CallableStatement callSt = null;
            try {
                conn = ConnectionDB.openConnection();
                //insert du lieu vao bang product
                callSt = conn.prepareCall("{call insertProduct(?,?,?,?)}");
                callSt.setString(1, proNew.getProductName());
                callSt.setString(2, proNew.getProductImage());
                callSt.setBoolean(3, proNew.isProductStatus());
                callSt.registerOutParameter(4, Types.INTEGER);
                callSt.execute();
                int productId = callSt.getInt(4);
                //insert tat ca anh phu vao bang images
                for (String imgLink : proNew.getListImage()) {
                    CallableStatement callSt2 = conn.prepareCall("{call insertImages(?,?)}");
                    callSt2.setString(1,imgLink);
                    callSt2.setInt(2,productId);
                    callSt2.executeUpdate();
                    callSt2.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                ConnectionDB.closeConnection(conn, callSt);
            }
            //lay lai du lieu de hien thi
            getAllProduct(request,response);
        }
    }
}
