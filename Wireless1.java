package kz.proffix4.spring;

/**
 * Класс, соответсвующий записи в таблице Wireless1
 *
 */
public class Wireless1 {

    int id;            // Код записи
    String Brand;  // Имя клиента
    String Name;   // Фамилия клиента
    int Price;           // Возраст клиента

    public Wireless1() {
        this.id = 0;
        this.Brand = "";
        this.Name = "";
        this.Price = 0;
    }

    public Wireless1(String Brand, String Name, int Price) {
        this.id = 0;
        this.Brand = Brand;
        this.Name = Name;
        this.Price = Price;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return Brand;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return String.format("Бренд=%s, Название=%s, Цена=%d", Brand, Name, Price);
    }

}
