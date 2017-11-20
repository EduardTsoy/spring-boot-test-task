package com.example.eduardtsoy.springboottesttask.domain;

import com.example.eduardtsoy.springboottesttask.utils.AbstractRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final String SQL_INSERT =
            " INSERT INTO product (name, brand, price, quantity) " +
                    " VALUES (:name, :brand, :price, :quantity) ";

    private static final String SQL_SELECT =
            " SELECT id, name, brand, price, quantity FROM product ";

    private static final String WHERE_ID = " WHERE id = :id ";

    private static final String ORDER_BY_ID = " ORDER BY id ";

    private static final String SQL_UPDATE =
            " UPDATE product SET " +
                    " name = :name " +
                    ", brand = :brand " +
                    ", price = :price " +
                    ", quantity = :quantity " +
                    WHERE_ID;

    private static final String SQL_DELETE =
            " DELETE FROM product " +
                    WHERE_ID;

    private final NamedParameterJdbcTemplate jdbc;

    @Autowired
    public ProductRepositoryImpl(@Nonnull final DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Nonnull
    @Override
    public Product create(@Nonnull final Product product) {
        final MapSqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("brand", product.getBrand())
                .addValue("price", product.getPrice())
                .addValue("quantity", product.getQuantity());
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        final int rowCount = jdbc.update(SQL_INSERT, sqlParams, keyHolder, new String[]{"id"});
        if (rowCount != 1) {
            throw new RuntimeException("Expected to insert 1 row, actual " + rowCount);
        }
        final long id = keyHolder.getKey().longValue();
        return findById(id);
    }

    @Nonnull
    @Override
    public List<Product> findAll() {
        final List<Product> result = jdbc.query(
                SQL_SELECT + ORDER_BY_ID,
                EmptySqlParameterSource.INSTANCE,
                new AbstractRowMapper<Product>() {
                    @Override
                    public Product mapRow(final ResultSet rs,
                                          final int i) throws SQLException {
                        return new Product(
                                getLong(rs, "id"),
                                getString(rs, "name"),
                                getString(rs, "brand"),
                                getBigDecimal(rs, "price"),
                                getLong(rs, "quantity")
                        );
                    }
                });
        return result;
    }

    @Override
    public Product findById(final long id) {
        final MapSqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue("id", id);
        final List<Product> result = jdbc.query(
                SQL_SELECT + WHERE_ID,
                sqlParams,
                new AbstractRowMapper<Product>() {
                    @Override
                    public Product mapRow(final ResultSet rs,
                                          final int i) throws SQLException {
                        return new Product(
                                getLong(rs, "id"),
                                getString(rs, "name"),
                                getString(rs, "brand"),
                                getBigDecimal(rs, "price"),
                                getLong(rs, "quantity")
                        );
                    }
                });
        if (result.size() > 1) {
            throw new RuntimeException("Expected to 0 or 1 row, actual " + result.size());
        }
        return (result.size() == 1) ? result.get(0) : null;
    }

    @Nonnull
    @Override
    public Product update(@Nonnull final Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("\n  // ID should not be null");
        }
        final MapSqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("name", product.getName())
                .addValue("brand", product.getBrand())
                .addValue("price", product.getPrice())
                .addValue("quantity", product.getQuantity());
        final int rowCount = jdbc.update(SQL_UPDATE, sqlParams);
        if (rowCount != 1) {
            throw new RuntimeException("Expected to update 1 row, actual " + rowCount);
        }
        return findById(product.getId());
    }

    @Override
    @Nonnull
    public Product delete(final long id) {
        final Product product = findById(id);
        if (product != null) {
            final MapSqlParameterSource sqlParams = new MapSqlParameterSource()
                    .addValue("id", id);
            final int rowCount = jdbc.update(SQL_DELETE, sqlParams);
            if (rowCount != 1) {
                throw new RuntimeException("Expected to delete 1 row, actual " + rowCount);
            }
        } else {
            throw new RuntimeException("\n  // Couldn't find a Product with ID=" + id);
        }
        return product;
    }
}
