
package classes;

import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

public class ProductCatalog {

    protected Products[] items1;
    ArrayList<Products> items = new ArrayList<Products>();

    public ProductCatalog() {

    try {
            Connection conn;
            Statement stm;
            ResultSet rst;

            Class.forName(DBClass.driver_url);
            conn = DriverManager.getConnection(DBClass.db_url, DBClass.db_user, DBClass.db_pass);

            stm = conn.createStatement();
            rst = stm.executeQuery("SELECT * FROM product");
                while (rst.next()) //next read the current record and move to next,if end of record found it return false
                {
                    String product_id = rst.getString("product_id");
                    String product_name = rst.getString("product_name");
                    String product_category = rst.getString("product_category");
                    double cutted_price = rst.getDouble("cutted_price");
                    double show_price = rst.getDouble("show_price");
                    int stock = rst.getInt("stock");
                    int re_order_value = rst.getInt("re_order_value");
                    String image = rst.getString("image");
                    String description = rst.getString("description");
                    int star_value = rst.getInt("star_value");
                    int offer = rst.getInt("offer");
                    Products p= new Products();
                    p.setProduct_id(product_id);
                    p.setProduct_name(product_name);
                    p.setProduct_category(product_category);
                    p.setCutted_price(cutted_price);
                    p.setShow_price(show_price);
                    p.setStock(stock);
                    p.setRe_order_value(re_order_value);
                    p.setImage(image);
                    p.setDescription(description);
                    p.setStar_value(star_value);
                    p.setOffer(offer);
                    items.add(p);
                }
                System.out.println("Database Products:"+items);
        }
        catch(Exception ex){}
                
    }

                
                /**
                 * returns an array containing all the items in the catalog
                 */
    public Products[] getItems() {
        return getItems(0, items.size());
    }

    /**
     * returns an array containing a subset of items from the catalog
     */
    public Products[] getItems(int startingLocation, int numItems) {
// If the number of items to be returned is larger than the number
// in the catalog, adjust the number to be returned.
        if (numItems > items.size()) {
            numItems = items.size();
        }

// If by returning numItems items you would run out of items (if there
// are 5 items, you ask for 3, but give a starting location of 4),
// adjust the starting location backward to ensure that the proper
// number of items is returned.
        if (startingLocation + numItems >= items.size()) {
            startingLocation = items.size() - numItems;
        }

// Create an array for the returned items.
        Products[] returnItems = new Products[numItems];

// Copy the items from the catalog into the return array.
        //System.arraycopy(items, startingLocation,returnItems, 0, numItems);
        
        items.toArray(returnItems);

        return returnItems;
    }

    /**
     * Returns true if there are items at a particular starting location. This
     * is helpful in determining whether a page should show a "Next" button to
     * see the next page of catalog items.
     */
    public boolean itemsAvailable(int startingLocation) {
        if (startingLocation >= items.size()) {
            return false;
        }
        return true;
    }

    /**
     * Searches for an item by product code and returns it. If there is no such
     * item, this method returns null.
     */
    public Products findItemByProductCode(String productCode) {
// Linear searches aren't a good idea for big arrays, but this
// one is small.
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct_id().equals(productCode)) {
                return items.get(i);
            }
        }

        return null;
    }
}
