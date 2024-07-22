package servlet;

import dto.OrderItemsDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.OrderItemsRepository;
import service.OrderItemsService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/OrderItems")
public class OrderItemsServlet extends HttpServlet {
    OrderItemsService orderItemsService = new OrderItemsService(new OrderItemsRepository());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("OrderItemID");
        if (id != null && !id.isEmpty()) {
            OrderItemsDto orderItemsDto = orderItemsService.findById(Integer.parseInt(id));
            PrintWriter out = response.getWriter();
            out.write(orderItemsDto.toString());
            out.close();
        } else {
            List<OrderItemsDto> orderItems = orderItemsService.findAll();
            PrintWriter out = response.getWriter();
            for (OrderItemsDto orderItem : orderItems) {
                out.write(orderItem.toString() + "\n");
            }
            out.close();
        }
        response.setStatus(200);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("OrderItemId");
        OrderItemsDto orderItemsDto = new OrderItemsDto();

        orderItemsDto.setOrderID(Integer.valueOf(request.getParameter("OrderID")));
        orderItemsDto.setProductName(String.valueOf(request.getParameter("ProductName")));
        orderItemsDto.setQuantity(Integer.valueOf(request.getParameter("Quantity")));
        orderItemsDto.setPrice(Double.valueOf(request.getParameter("Price")));
        orderItemsService.save(orderItemsDto);
        response.setStatus(200);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderItemsDto orderItemsDto = orderItemsService.findById(Integer.valueOf(request.getParameter("OrderItemID")));
        if (request.getParameter("OrderID") != null)
            orderItemsDto.setOrderID(Integer.valueOf(request.getParameter("OrderID")));
        if (request.getParameter("ProductName") != null)
            orderItemsDto.setProductName(String.valueOf(request.getParameter("ProductName")));
        if (request.getParameter("Quantity") != null)
            orderItemsDto.setQuantity(Integer.valueOf(request.getParameter("Quantity")));
        if (request.getParameter("Price") != null)
            orderItemsDto.setPrice(Double.valueOf(request.getParameter("Price")));
        orderItemsService.update(orderItemsDto);
        response.setStatus(200);
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderItemsService.deleteById(Integer.valueOf(request.getParameter("OrderItemID")));
        response.setStatus(200);
    }

}
