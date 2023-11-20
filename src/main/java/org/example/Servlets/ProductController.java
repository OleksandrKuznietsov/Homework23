package org.example.Servlets;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Order;
import org.example.Product;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet
public class ProductController extends HttpServlet {
    private final Order order;
    private final Gson gson = new Gson();
    public ProductController(Order order) {
        this.order = order;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try(PrintWriter writer = resp.getWriter()){
            final String id = req.getParameter("id");
            if (id == null){
                writer.println(gson.toJson(order.getProducts()));
            }else {
                writer.println(gson.toJson(getProductById(Integer.parseInt(id))));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Product product = gson.fromJson(req.getReader(), Product.class);
        order.addProduct(product);
        System.out.println(product);
        try(PrintWriter writer = resp.getWriter()){
            writer.println(gson.toJson(product.getId()));
        }
    }

    private Product getProductById(int productId){
        for (Product product : order.getProducts()) {
            if (product.getId() == productId){
                return product;
            }
        }
        return null;
    }
}
