package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Transfer extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int transfer;
    private String transferMode;
    private BigDecimal price;

    public Transfer() {
    }

    public Transfer(int transfer, String transferMode, BigDecimal price) {
        this.transfer = transfer;
        this.transferMode = transferMode;
        this.price = price;
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public String getTransferMode() {
        return transferMode;
    }

    public void setTransferMode(String transferMode) {
        this.transferMode = transferMode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return this.transfer == transfer.transfer && Objects.equals(transferMode, transfer.transferMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transfer, transferMode);
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + transfer +
                ", transferMode=" + transferMode +
                ", price=" + price +
                '}';
    }
}
