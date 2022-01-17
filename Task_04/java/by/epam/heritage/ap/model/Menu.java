package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Menu extends Entity implements Serializable {
    private int menu;
    private BigDecimal price;
    private String menuMode;

    public Menu(){}

    public Menu(int menu, String menuMode, BigDecimal price) {
        this.menu = menu;
        this.price = price;
        this.menuMode = menuMode;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public String  getMenuMode() {
        return menuMode;
    }

    public void setMenuMode(String  menuMode) {
        this.menuMode = menuMode;
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
        Menu menu = (Menu) o;
        return this.menu == menu.menu && menuMode.equals(menu.menuMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, menuMode);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + menu +
                ", menuMode=" + menuMode +
                ", price=" + price +
                '}';
    }
}
