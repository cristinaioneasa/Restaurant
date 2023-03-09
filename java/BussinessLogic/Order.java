package BussinessLogic;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Ioneasa Cristina
 */

public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private Date orderDate;

    public Order(int orderId, int clientId) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = new Date();
    }

    public Order(int orderId, int clientId, Date orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    @Override
    public int hashCode() {
        return orderDate.hashCode() + orderId + clientId;
    }

    @Override
    public String toString() {
        return "\nOrder" +
                "\norderId=" + orderId +
                "\nclientId=" + clientId +
                "\norderDate=" + orderDate;
    }
}
