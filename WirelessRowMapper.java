package kz.proffix4.spring;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Класс загрузки данных в объект Wireless1 из считанной записи таблицы БД
 *
 */
public class WirelessRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        WirelessResultSetExtractor extractor = new WirelessResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class WirelessResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Wireless1 wireless1 = new Wireless1();
            wireless1.setId(rs.getInt("id"));
            wireless1.setBrand(rs.getString("brand"));
            wireless1.setName(rs.getString("name"));
            wireless1.setPrice(rs.getInt("price"));
            return wireless1;
        }
    }
}
