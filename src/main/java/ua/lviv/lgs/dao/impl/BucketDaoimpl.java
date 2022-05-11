package ua.lviv.lgs.dao.impl;



import ua.lviv.lgs.dao.BucketCRUD;
import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class BucketDaoimpl implements BucketCRUD, BucketDao {
    private static String READ_ALL = "select * from bucket";
    private static String CREATE = "insert into bucket(`user_id`, `product_id`, `purchase_date`) values (?,?,?)";
    private static String READ_BY_ID = "select * from bucket where id = ?";
    private static String DELETE_BY_ID = "delete from bucket where id = ?";

    private static Logger LOGGER = Logger.getLogger(BucketDaoimpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BucketDaoimpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public Bucket create(Bucket bucket) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            bucket.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucket;
    }

    @Override
    public Bucket read(Integer id) {
        Bucket bucket = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Integer bucketId = resultSet.getInt("id");
            Integer userId = resultSet.getInt("user_id");
            Integer productId = resultSet.getInt("product_id");
            java.util.Date purchaseDate = resultSet.getDate("purchase_date");

            bucket = new Bucket(bucketId, userId, productId, purchaseDate);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return bucket;
    }

    @Override
    public Bucket update (Bucket update) {
        throw new IllegalStateException("there is no update for bucket");
    }

    @Override
    public void delete (Integer id){
            try {
                preparedStatement = connection.prepareStatement(DELETE_BY_ID);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }

    @Override
    public List<Bucket> readAll() {
        List<Bucket> bucketRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer bucketId = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                Integer productId = resultSet.getInt("product_id");
                java.util.Date purchaseDate = resultSet.getDate("purchase_date");
                bucketRecords.add(new Bucket(bucketId, userId, productId, purchaseDate));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucketRecords;
    }
}
