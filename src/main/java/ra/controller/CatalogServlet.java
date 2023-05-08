package ra.controller;

import ra.model.entity.Catalog;
import ra.model.services.catalog.CatalogServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
@WebServlet(name = "CatalogServlet", value = "/CatalogServlet")
public class CatalogServlet extends HttpServlet {
    private CatalogServiceImpl catalogServiceImpl = new CatalogServiceImpl();
    private List<Catalog> lists ;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action){
                case "GETALL":
                    showAllCatalog(request,response);
                    break;
                case "CREATE":
                    response.sendRedirect("view/newCatalog.jsp");
                    break;
                case "DELETE":
                    String id = request.getParameter("id");
                    catalogServiceImpl.delete(id);
                    showAllCatalog(request,response);
                    break;
                case "EDIT":
                    String idEdit = request.getParameter("id");
                    Catalog catalogEdit = catalogServiceImpl.findById(idEdit);
                    request.setAttribute("catalog_edit",catalogEdit);
                    request.getRequestDispatcher("view/editCatalog.jsp").forward(request,response);
                    break;
                case "SEARCH":
                    String searchName = request.getParameter("search");
                    List<Catalog> listSearch = catalogServiceImpl.findByName(searchName);
                    request.setAttribute("list_catalog",listSearch);
                    request.setAttribute("search", searchName);
                    request.getRequestDispatcher("view/catalogs.jsp").forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "ADD":
                    Catalog newCatalog = new Catalog();
//                    newCatalog.setId(request.getParameter("id"));
                    newCatalog.setName(request.getParameter("name"));
                    // up load file
                    String pathImage ="E:/JAVACORE/demo-Project/src/main/webapp/image";
                    File file = new File(pathImage);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                   for (Part part :request.getParts()){
                       if (part.getName().equals("image")) {
                           // cách 1
                           String fileName = part.getSubmittedFileName();
                           part.write(pathImage+File.separator +fileName);
                           newCatalog.setUrl(fileName);
                           // cách 2
//                           String fileName = part.getSubmittedFileName();
//                           InputStream inputStream = part.getInputStream();
//                           OutputStream outputStream = new FileOutputStream(new File(pathImage, fileName));
//
//                           byte[] buffer = new byte[1024];
//                           int length;
//                           while ((length = inputStream.read(buffer)) != -1) {
//                               outputStream.write(buffer, 0, length);
//                           }
//
//                           inputStream.close();
//                           outputStream.close();
//
//                           newCatalog.setUrl(fileName);
//                           break;
                       }
                   }

                    boolean check = catalogServiceImpl.save(newCatalog);

                    showAllCatalog(request,response);
                    break;
                case "UPDATE":
                    Catalog catalogUpdate  = new Catalog();
                    String id = request.getParameter("id");
                    catalogUpdate.setId(id);
                    String name = request.getParameter("name");
                    catalogUpdate.setName(name);
                    // upload lại file


                    catalogServiceImpl.update(catalogUpdate);
                    showAllCatalog(request, response);
                    break;
            }
        }
    }
    protected void showAllCatalog(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        lists = catalogServiceImpl.findAll();
        request.setAttribute("list_catalog",lists);
        request.getRequestDispatcher("view/catalogs.jsp").forward(request, response);
    }
}
