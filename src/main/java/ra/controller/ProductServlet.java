package ra.controller;

import ra.model.entity.Catalog;
import ra.model.entity.Product;
import ra.model.services.catalog.CatalogServiceImpl;
import ra.model.services.product.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    ProductServiceImpl service = new ProductServiceImpl();

    CatalogServiceImpl catalogService = new CatalogServiceImpl();
    private List<Product> lists ;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action){
                case "GETALL":  showAllProduct(request, response);
                break;
                case "CREATE": createPrd(request, response);
                break;
                case "DELETE":{
                    int id = Integer.parseInt(request.getParameter("id"));
                    service.delete(id);
                    showAllProduct(request, response);
                    break;
                }
                case  "EDIT": {
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Product pEdit = service.findById(idEdit);
                    request.setAttribute("productEdit", pEdit);
                    request.setAttribute("list_cata", catalogService.findAll());
                    request.getRequestDispatcher("view/product/editProduct.jsp").forward(request, response);
                    break;
                }
                case "SEARCH" :{
                    String searchName = request.getParameter("search");
                    request.setAttribute("list_product",service.findByName(searchName));
                    request.setAttribute("search", searchName);
                    request.setAttribute("list_cata", catalogService.findAll());
                    request.getRequestDispatcher("view/product/product.jsp").forward(request, response);
                    break;
                }
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null){
            switch (action){
                case "ADD": {
                    Product p = new Product();
                    p.setName(request.getParameter("name"));
                    p.setPrice(Double.parseDouble(request.getParameter("price")));
                    p.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                    p.setCatalog(new CatalogServiceImpl().findById(request.getParameter("catalog")));
                    service.save(p);
                    showAllProduct(request, response);
                    break;
                }
                case "UPDATE": {
                    Product p = new Product();
                    p.setId(Integer.parseInt(request.getParameter("id")));
                    p.setName(request.getParameter("name"));
                    p.setPrice(Double.parseDouble(request.getParameter("price")));
                    p.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                    p.setCatalog(new CatalogServiceImpl().findById(request.getParameter("catalog")));
                    service.update(p);
                    showAllProduct(request, response);
                    break;
                }
                case "SEARCHBYCATALOG": {
                    request.setAttribute("list_product", service.searchByCatalog(Integer.parseInt(request.getParameter("catalog"))));
                    request.setAttribute("list_cata", catalogService.findAll());
                    request.getRequestDispatcher("view/product/product.jsp").forward(request, response);
                    break;
                }

            }
        }
    }

    public void createPrd(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("list_cata", new CatalogServiceImpl().findAll());
        request.getRequestDispatcher("view/product/createNewProduct.jsp").forward(request, response);
    }
    protected void showAllProduct(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        lists = service.findAll();
        request.setAttribute("list_cata", catalogService.findAll());
        request.setAttribute("list_product",lists);
        request.getRequestDispatcher("view/product/product.jsp").forward(request, response);
    }


}
