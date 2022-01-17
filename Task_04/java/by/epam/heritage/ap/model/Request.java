package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Request extends Entity implements Serializable {
    private int requestId;
    private int guestId;
    private int quantity;
    private int category;
    private int menu;
    private int transfer;
    private int distance;
    private LocalDate start;
    private LocalDate end;
    private LocalDate dateRequest;
    private boolean isResponded;

    public Request(){};

    public Request(int requestId, int guestId, int quantity, int category, int menu, int transfer, int distance, LocalDate start, LocalDate end, LocalDate dateRequest, boolean isResponded) {
        this.requestId = requestId;
        this.guestId = guestId;
        this.quantity = quantity;
        this.category = category;
        this.menu = menu;
        this.transfer = transfer;
        this.distance = distance;
        this.start = start;
        this.end = end;
        this.dateRequest = dateRequest;
        this.isResponded = isResponded;
    }

    public boolean isResponded() {
        return isResponded;
    }

    public void setResponded(boolean responded) {
        isResponded = responded;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return requestId == request.requestId && guestId == request.guestId && quantity == request.quantity && category == request.category && menu == request.menu && transfer == request.transfer && distance == request.distance && isResponded == request.isResponded && start.equals(request.start) && end.equals(request.end) && dateRequest.equals(request.dateRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, guestId, quantity, category, menu, transfer, distance, start, end, dateRequest, isResponded);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", userId=" + guestId +
                ", quantity=" + quantity +
                ", category=" + category +
                ", menuId=" + menu +
                ", transferId=" + transfer +
                ", distance=" + distance +
                ", dateIn=" + start +
                ", dateOut=" + end +
                ", dateRequest=" + dateRequest +
                ", isResponded=" + isResponded +
                '}';
    }
}
//public class Request extends Entity implements Serializable {
//    private int requestId;
//    private int userId;
//    private int quantity;
//    private int category;
//    private int menuId;
//    private int transferId;
//    private int distance;
//    private LocalDate dateIn;
//    private LocalDate dateOut;
//    private LocalDate dateRequest;
//    private boolean isResponded;
//
//    public Request(){};
//
//    public Request(int requestId, int userId, int quantity, int category, int menuId, int transferId, int distance, LocalDate dateIn, LocalDate dateOut, LocalDate dateRequest, boolean isResponded) {
//        this.requestId = requestId;
//        this.userId = userId;
//        this.quantity = quantity;
//        this.category = category;
//        this.menuId = menuId;
//        this.transferId = transferId;
//        this.distance = distance;
//        this.dateIn = dateIn;
//        this.dateOut = dateOut;
//        this.dateRequest = dateRequest;
//        this.isResponded = isResponded;
//    }
//
//    public boolean isResponded() {
//        return isResponded;
//    }
//
//    public void setResponded(boolean responded) {
//        isResponded = responded;
//    }
//
//    public int getRequestId() {
//        return requestId;
//    }
//
//    public void setRequestId(int requestId) {
//        this.requestId = requestId;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public int getCategory() {
//        return category;
//    }
//
//    public void setCategory(int category) {
//        this.category = category;
//    }
//
//    public int getMenuId() {
//        return menuId;
//    }
//
//    public void setMenuId(int menuId) {
//        this.menuId = menuId;
//    }
//
//    public int getTransferId() {
//        return transferId;
//    }
//
//    public void setTransferId(int transferId) {
//        this.transferId = transferId;
//    }
//
//    public int getDistance() {
//        return distance;
//    }
//
//    public void setDistance(int distance) {
//        this.distance = distance;
//    }
//
//    public LocalDate getDateIn() {
//        return dateIn;
//    }
//
//    public void setDateIn(LocalDate dateIn) {
//        this.dateIn = dateIn;
//    }
//
//    public LocalDate getDateOut() {
//        return dateOut;
//    }
//
//    public void setDateOut(LocalDate dateOut) {
//        this.dateOut = dateOut;
//    }
//
//    public LocalDate getDateRequest() {
//        return dateRequest;
//    }
//
//    public void setDateRequest(LocalDate dateRequest) {
//        this.dateRequest = dateRequest;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Request request = (Request) o;
//        return requestId == request.requestId && userId == request.userId && quantity == request.quantity && category == request.category && menuId == request.menuId && transferId == request.transferId && distance == request.distance && isResponded == request.isResponded && dateIn.equals(request.dateIn) && dateOut.equals(request.dateOut) && dateRequest.equals(request.dateRequest);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(requestId, userId, quantity, category, menuId, transferId, distance, dateIn, dateOut, dateRequest, isResponded);
//    }
//
//    @Override
//    public String toString() {
//        return "Request{" +
//                "requestId=" + requestId +
//                ", userId=" + userId +
//                ", quantity=" + quantity +
//                ", category=" + category +
//                ", menuId=" + menuId +
//                ", transferId=" + transferId +
//                ", distance=" + distance +
//                ", dateIn=" + dateIn +
//                ", dateOut=" + dateOut +
//                ", dateRequest=" + dateRequest +
//                ", isResponded=" + isResponded +
//                '}';
//    }
//}