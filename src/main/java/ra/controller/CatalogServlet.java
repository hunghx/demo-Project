package ra.controller;

import ra.model.entity.Catalog;
import ra.model.services.catalog.CatalogServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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
                    boolean check = catalogServiceImpl.save(newCatalog);
                    showAllCatalog(request,response);
                    break;
                case "UPDATE":
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    Catalog catalogUpdate  = new Catalog(id,name);
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
