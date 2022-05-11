package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.dao.impl.BucketDaoimpl;
import ua.lviv.lgs.dao.impl.UserDaoimpl;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {

    private static Logger LOGGER = Logger.getLogger(BucketServiceImpl.class);

    private static BucketService bucketServiceImpl;

    private BucketDao bucketDao;

    private BucketServiceImpl() {
        try {
            bucketDao = new BucketDaoimpl();
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

    public static BucketService getBucketServiceImpl () {
        if (bucketServiceImpl == null) {
            bucketServiceImpl = new BucketServiceImpl();
        }
        return bucketServiceImpl;
    }

    @Override
    public Bucket create(Bucket t) {
        return bucketDao.create(t);
    }

    @Override
    public Bucket read(Integer id) {
        return bucketDao.read(id);
    }

    @Override
    public Bucket update(Bucket t) {
        return bucketDao.update(t);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        bucketDao.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
