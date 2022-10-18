package kz.proffix4.spring;

import javax.sql.DataSource;
import java.util.List;

/**
 * Интерфейс работы с таблицой Wireless1
 *
 */
public interface IWirelessDAO {
    void setDataSource(DataSource ds); // Установка связи с данныими
    void insert(Wireless1 wireless1); // Вставка новой записи
    void append(String Brand, String Name, int Price); // Добавление новой записи
    void append(String Brand, String Name); // Добавление новой записи
    void deleteByName(String Name); // Удаление записи по фамилии
    void deleteByPrice(String Price);
    void delete(String Brand, String Name); // Удаление записи с указанным именем и фамилией
    void deleteAll(); // Удаление всех запией
    void updateBrand(String newBrand, String oldBrand); // Изменение записей в таблице
    void updateName(String newName, String oldName);
    void updatePriceAtBrand(String Brand, String newPrice, String oldPrice);
    Wireless1 findByPrice(int price); // Получение записи с заданным 
    List<Wireless1> findByPriceLess(int price);
    List<Wireless1> findByBrand(String Brand); // Получение записей с заданным именем 
    List<Wireless1> select(String Brand, String Name); // Получение записей с заданным именем и фамилией
    List<Wireless1> selectAll(); // Получение всех записей
}
