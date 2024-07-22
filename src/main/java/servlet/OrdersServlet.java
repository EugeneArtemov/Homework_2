package servlet;

import dto.OrderItemsDto;
import dto.OrdersDto;
import entity.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.OrdersRepository;
import service.OrdersService;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/Orders")
public class OrdersServlet extends HttpServlet {
    OrdersService ordersService = new OrdersService(new OrdersRepository());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("OrderID");
        if (id != null && !id.isEmpty()) {
            OrdersDto ordersDto = ordersService.findById(Integer.parseInt(id));
            PrintWriter out = response.getWriter();
            out.write(ordersDto.toString());
            out.close();
        } else {

            PrintWriter out = response.getWriter();
            List<OrdersDto> orders = ordersService.findAll();
            for (OrdersDto order : orders) {
                out.write(order.toString() + "\n");
            }
            out.close();
        }
        response.setStatus(200);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setOrderDate(LocalDate.now());
        ordersDto.setCustomerName(request.getParameter("CustomerName"));
        ordersDto.setTotalAmount(Double.parseDouble(request.getParameter("TotalAmount")));
        ordersService.save(ordersDto);
        response.setStatus(200);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdersDto ordersDto = ordersService.findById(Integer.parseInt(request.getParameter("OrderID")));
        if (request.getParameter("OrderDate") != "null")
            ordersDto.setOrderDate(LocalDate.parse(request.getParameter("OrderDate")));
        if (request.getParameter("CustomerName") != "null")
            ordersDto.setCustomerName(request.getParameter("CustomerName"));
        if (request.getParameter("TotalAmount") != "null")
            ordersDto.setTotalAmount(Double.parseDouble(request.getParameter("TotalAmount")));
        ordersService.update(ordersDto);
        response.setStatus(200);
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ordersService.deleteById(Integer.parseInt(request.getParameter("OrderID")));
        response.setStatus(200);
    }
}
