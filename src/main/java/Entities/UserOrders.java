package Entities;

import Enums.CruiseStatusEnum;

import java.util.Objects;

public class UserOrders extends Cruise{
    private int orderId;
    private int u_id;
    private int quantity;
    private CruiseStatusEnum statusId;
    private String date;
    private double paymentAmount;
    private String images;

    public UserOrders(int orderId, int u_id, int quantity, CruiseStatusEnum statusId, String date, double paymentAmount, String images) {
        this.orderId = orderId;
        this.u_id = u_id;
        this.quantity = quantity;
        this.statusId = statusId;
        this.date = date;
        this.paymentAmount = paymentAmount;
        this.images = images;
    }

    public UserOrders(int u_id, int quantity, CruiseStatusEnum statusId, String date, double paymentAmount, String images) {
        this.u_id = u_id;
        this.quantity = quantity;
        this.statusId = statusId;
        this.date = date;
        this.paymentAmount = paymentAmount;
        this.images = images;
    }

    public UserOrders() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CruiseStatusEnum getStatusId() {
        return statusId;
    }

    public void setStatusId(CruiseStatusEnum statusId) {
        this.statusId = statusId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "UserOrders{" +
                "orderId=" + orderId +
                ", u_id=" + u_id +
                ", quantity=" + quantity +
                ", statusId=" + statusId +
                ", date='" + date + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", images='" + images + '\'' +
                '}';
    }
}
