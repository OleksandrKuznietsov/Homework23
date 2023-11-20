package org.example.Servlets;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Order;
import org.example.OrderRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet
public class OrderController extends HttpServlet {
    OrderRepository orderRepository = new OrderRepository();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try(PrintWriter writer = resp.getWriter()){
            final String id = req.getParameter("id");
            if (id == null){
                writer.println(gson.toJson(orderRepository.getAllOrders()));
            }else {
                writer.println(gson.toJson(orderRepository.getOrderById(Integer.parseInt(id))));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Order order = gson.fromJson(req.getReader(), Order.class);
        orderRepository.addOrder(order);
        System.out.println(order);
        try(PrintWriter writer = resp.getWriter()){
            writer.println(gson.toJson(order.getId()));
        }
    }
}
