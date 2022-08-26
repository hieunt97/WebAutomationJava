package model;

public class Product {

    private String prouductName;
    private String productPrice;
    private String productLink;

    public String getProuductName() {
        return prouductName;
    }

    public void setProuductName(String prouductName) {
        this.prouductName = prouductName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public void inforProduct() {
        System.out.println("Name: " + getProuductName());
        System.out.println("Price: " + getProductPrice());
        System.out.println("Link: " + getProductLink());
        System.out.println("-------------");
    }
}
