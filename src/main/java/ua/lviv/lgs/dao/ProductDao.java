package ua.lviv.lgs.dao;

import ua.lviv.lgs.domain.Product;

import java.util.List;

public interface ProductDao {
    Product create(Product t);

    Product read(Integer id);

    List<Product> readAll();

    void delete(Integer id);

    Product update(Product t);
}
