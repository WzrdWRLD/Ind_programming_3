package kz.proffix4.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

/**
 * Реализация интерфейса работы с таблицей Person
 *
 */
public class WirelessDAO implements IWirelessDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Wireless1 wireless) { // Реализация вставки новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO Wireless1 (BRAND, NAME, PRICE) VALUES(?,?,?)",
                new Object[]{wireless.getBrand(), wireless.getName(), wireless.getPrice()});
    }

    @Override
    public void append(String Brand, String Name, int Price) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO Wireless1 (BRAND, NAME, PRICE) VALUES(?,?,?)", 
                new Object[]{Brand, Name, Price});
    }
    
    @Override
    public void append(String Brand, String Name) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO Wireless1 (BRAND, NAME) VALUES(?,?)", 
                new Object[]{Brand, Name});
    }

    @Override
    public void deleteByName(String name) {  // Реализация удаления записей по фамилии
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM Wireless1 WHERE NAME LIKE ?", new Object[]{'%' + name + '%'});
    }
    
    @Override
    public void deleteByPrice(String Price) {  // Реализация удаления записей по фамилии
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM Wireless1 WHERE Price LIKE ?", new Object[]{'%' + Price + '%'});
    }
    
    @Override
    public void delete(final String Brand, final String Name) {  // Реализация удаления записей с указанным именем и фамилией
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from Wireless1 where BRAND= ? AND NAME = ?", new Object[]{Brand, Name});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE from Wireless1");
    }

    @Override
    public void updateBrand(String newBrand, String oldBrand) {  // Изменение записей в таблице
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE Wireless1 SET BRAND = ? WHERE BRAND = ?", new Object[]{newBrand, oldBrand});
    }
    
    @Override
    public void updateName(String newName, String oldName){
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE Wireless1 SET NAME = ? WHERE NAME = ?", new Object[]{newName, oldName});
    }
    
    @Override
    public void updatePriceAtBrand(String Brand, String newPrice, String oldPrice) {
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE Wireless1 SET PRICE = ? WHERE BRAND = ? AND PRICE = ?", new Object[]{newPrice, Brand, oldPrice});
    }

    @Override
        public Wireless1 findByPrice(int price) { // Реализация роиска записи с заданным возрастом
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Wireless1> wireless = jt.query("SELECT * FROM Wireless1 WHERE PRICE = ?",
                new Object[]{price}, new WirelessRowMapper());
        return wireless.size() > 0 ? wireless.get(0) : null;
    }
       
    @Override
    public List<Wireless1> findByPriceLess(int price) {
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Wireless1> wireless = jt.query("SELECT * FROM Wireless1 WHERE PRICE < ?",
                new Object[]{price}, new WirelessRowMapper());
        return wireless.size() > 0 ? wireless : null;
    }

    @Override
    public List<Wireless1> findByBrand(String brand) {  // Реализация поиска записей по имени
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM Wireless1 WHERE BRAND LIKE ?";
        List<Wireless1> ws = jt.query(sql, new Object[]{'%' + brand + '%'}, new WirelessRowMapper());
        return ws;
    }

    @Override
    public List<Wireless1> select(String brand, String name) {  // Реализация получения записей с заданным именем и фамилией
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from Wireless1 where BRAND = ? AND NAME= ?",
                new Object[]{brand, name}, new WirelessRowMapper());
    }
    
    @Override
    public List<Wireless1> selectAll() {  // Реализация получения всех записей
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from Wireless1", new WirelessRowMapper());
    }
}
