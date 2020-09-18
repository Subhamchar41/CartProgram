
package classes;
import java.util.*;
import java.io.*;

public class ShoppingCart implements java.io.Serializable
{
// The shopping cart items are stored in a Vector.
    protected Vector items;

    public ShoppingCart()
    {
        items = new Vector();
    }

/** Returns a Vector containing the items in the cart. The Vector
 *  returned is a clone, so modifying the vector won't affect the
 *  contents of the cart.
 */
    public Vector getItems()
    {
        return (Vector) items.clone();
    }

    public synchronized  void addItem(Item newItem)
    {
        //items.addElement(newItem);
        
        Enumeration e = items.elements();

// See if there is already an item like this in the cart.
        while (e.hasMoreElements())
        {
            Item currItem = (Item) e.nextElement();

            if (newItem.equals(currItem))
            {
// Update the order quantity on the existing item.
                currItem.orderQuantity = currItem.orderQuantity +
                    newItem.orderQuantity;
                return;
            }
        }

// Didn't find one like this one, so add this one to the cart.
        items.addElement(newItem);
    }

    public synchronized  void removeItem(int itemIndex)
    {
        //items.removeElementAt(itemIndex);
        Item item = (Item) items.elementAt(itemIndex);

// Remove 1 instance of this item from the quantity (an instance
// is the number of items in the quantity, such as 1 car, 12 hot dogs).
        item.orderQuantity = item.orderQuantity - item.quantity;

// If the order quantity reaches 0, remove this item from the cart.
        if (item.orderQuantity <= 0)
        {
            items.removeElementAt(itemIndex);
        }
    }

// Warning! This order number is reset every time the server is
// restarted. This technique of generating an order number is
// just for demonstration.
    protected static int nextOrderNumber = 1;

// Submit the order and return a confirmation number.
    public String completeOrder(Shipping shipping, Billing billing)
        throws ShoppingCartException
    {
// You would normally insert the order into a database or send
// it to an application server. For the sake of simplicity
// this shopping cart just writes the order to a file.
        try
        {
            int orderNumber = 0;

// Make sure no other threads can be generating an order number.
            synchronized (this)
            {
                orderNumber = nextOrderNumber;
                nextOrderNumber = nextOrderNumber + 1;
            }
            PrintWriter out = new PrintWriter(
                new FileOutputStream("order"+orderNumber));

// Print the shipping info.
            out.println(shipping.name);
            out.println(shipping.address1);
            if (shipping.address2 != null)
            {
                out.println(shipping.address2);
            }
            out.print(shipping.city);
            if (shipping.state != null)
            {
                out.print(", "+shipping.state);
            }
            if (shipping.postalCode != null)
            {
                out.print(" "+shipping.postalCode);
            }
            out.println(" "+shipping.country);
            out.println(shipping.email);

// Print the billing info.
            out.println(billing.nameOnCard);
            out.println(billing.creditCardType);
            out.println(billing.creditCardNumber);
            out.println(billing.creditCardExpiration);

// Print out the items.
            Enumeration e = items.elements();
            while (e.hasMoreElements())
            {
                Item item = (Item) e.nextElement();

                out.println(item.productCode+","+item.orderQuantity);
            }
            out.close();

// Return a confirmation number (the order number as a string in this case).
            return ""+orderNumber;
        }
        catch (Exception exc)
        {
            throw new ShoppingCartException(
                "Error saving order: "+exc.toString());
        }
    }
}