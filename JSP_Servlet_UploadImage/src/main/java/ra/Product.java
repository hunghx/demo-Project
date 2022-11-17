package ra;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productId;
    private String productName;
    private String productImage;
    private boolean productStatus;
    private List<String> listImage = new ArrayList<>();

    public Product() {
    }

    public Product(int productId, String productName, String productImage, boolean productStatus, List<String> listImage) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.listImage = listImage;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public List<String> getListImage() {
        return listImage;
    }

    public void setListImage(List<String> listImage) {
        this.listImage = listImage;
    }
}
