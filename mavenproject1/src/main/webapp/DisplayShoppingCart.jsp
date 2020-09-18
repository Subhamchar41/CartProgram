<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Vector"%>
<%@page import="classes.Item"%>
<%@page import="classes.ShoppingCart"%>
<%@include file="header.jsp" %>



<!-- breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
				<li><a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
				<li class="active">Shopping Cart</li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->
<div class="brands">
		<div class="container">
<%-- Show the header with the shopping cart image --%>
<table border="0">
    <tr><td><img src="images/shopping-cart.png" alt="" width="100px"/><td><h1>Shopping Cart</h1>
    <tr><td><a href="ShowProductCatalog.jsp">Go To Home - All Products</a></td></tr>    
</table>

<%
// Get the current shopping cart from the user's session.
    ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

// If the user doesn't have a shopping cart yet, create one.
    if (cart == null)
    {
        cart = new ShoppingCart();
        session.setAttribute("ShoppingCart", cart);
    }

// Get the items from the cart.
    Vector items = cart.getItems();

// If there are no items, tell the user that the cart is empty.
    if (items.size() == 0)
    {
        out.println("<h3>Your shopping cart is empty.</h3>");
    }
    else
    {
%>
<%-- Display the header for the shopping cart table --%>
<br>
<table border=4>
    <tr><th>Description</th><th>Quantity</th><th>Price</th><th>Action</th></tr>
<%

        int numItems = items.size();

// Get a formatter to write out currency values.
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        for (int i=0; i < numItems; i++)
        {
            Item item = (Item) items.elementAt(i);

// Print the table row for the item.
            out.print("<tr><td>");
            out.print(item.description);
            out.print("</td><td>");
            out.print(item.orderQuantity);
            out.print("</td><td>");
            out.print(currency.format(item.price*item.orderQuantity));

// Print out a link that allows the user to delete an item from the cart.
            out.println("</td><td>"+
                "<a href=RemoveItemServlet?item="+
                i+"\">Remove</a></td></tr>" );
        }
        
       out.println("<tr><td colspan=\"4\">"+
                "<a href=\"Checkout.jsp\">Checkout</a></td></tr>"); 
    }
%>

</table>
 </div>
</div>
<%@include file="footer.jsp" %>