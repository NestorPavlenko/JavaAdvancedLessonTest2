package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.dao.impl.ProductDaoimpl;
import ua.lviv.lgs.dao.impl.UserDaoimpl;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private static ProductService productServiceImpl;
    private ProductDao productDao;

    private ProductServiceImpl() {
        try {
            productDao = new ProductDaoimpl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOGGER.error(e);
        }
    }

    public static ProductService getProductServiceImpl () {
        if (productServiceImpl == null) {
            productServiceImpl = new ProductServiceImpl();
        }
        return productServiceImpl;
    }

    @Override
    public Product create(Product t) {
        return productDao.create(t);
    }

    @Override
    public Product read(Integer id) {
        return productDao.read(id);
    }

    @Override
    public Product update(Product t) {
        return productDao.update(t);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        productDao.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDao.readAll();
    }
}
