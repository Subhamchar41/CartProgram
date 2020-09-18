
package classes;


public class Products implements java.io.Serializable{
    public String product_id;
    public String product_name;
    public String product_category;
    public double cutted_price;
    public double show_price;
    public int stock;
    public int re_order_value;
    public String image;
    public String description;
    public int star_value;
    public int offer;

    public Products() {
    }

    public Products(String product_id, String product_name, String product_category, double cutted_price, double show_price, int stock, int re_order_value, String image, String description, int star_value, int offer) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_category = product_category;
        this.cutted_price = cutted_price;
        this.show_price = show_price;
        this.stock = stock;
        this.re_order_value = re_order_value;
        this.image = image;
        this.description = description;
        this.star_value = star_value;
        this.offer = offer;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public double getCutted_price() {
        return cutted_price;
    }

    public void setCutted_price(double cutted_price) {
        this.cutted_price = cutted_price;
    }

    public double getShow_price() {
        return show_price;
    }

    public void setShow_price(double show_price) {
        this.show_price = show_price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getRe_order_value() {
        return re_order_value;
    }

    public void setRe_order_value(int re_order_value) {
        this.re_order_value = re_order_value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStar_value() {
        return star_value;
    }

    public void setStar_value(int star_value) {
        this.star_value = star_value;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }
    
    
  

    
}
    

