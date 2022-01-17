package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Category extends  Entity implements Serializable {
    private int category;
    private String categoryName;
    private BigDecimal categoryPrice;

    public Category() {
    }

    public Category(int category, String categoryName, BigDecimal categoryPrice) {
        this.category = category;
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getCategoryPrice() {
        return categoryPrice;
    }

    public void setCategoryPrice(BigDecimal categoryPrice) {
        this.categoryPrice = categoryPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return this.category == category.category && categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, categoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + category +
                ", categoryName='" + categoryName + '\'' +
                ", categoryPrice=" + categoryPrice +
                '}';
    }
}
