package ua.lviv.lgs.dao;

import ua.lviv.lgs.domain.Bucket;

import java.util.List;

public interface BucketDao {
    Bucket create(Bucket t);

    Bucket read(Integer id);

    Bucket update(Bucket t);

    void delete(Integer id);

    List<Bucket> readAll();
}
