package kz.proffix4.spring;

import java.util.Arrays;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;

class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с бинами

            WirelessDAO wirelessDAO = (WirelessDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице клиентов 

            wirelessDAO.deleteAll(); // Удаление всех записей

            Wireless1 wireless1 = new Wireless1("Sony", "wh-1000xm4", 150000); // Создание нового объекта таблицы клиентов 
            wirelessDAO.insert(wireless1); // Вставить новый объект (запись) в таблицу клиентов

            wirelessDAO.insert(new Wireless1("Apple", "AirPods MAX", 300000)); // Вставить новый объект (запись) в таблицу клиентов
            wirelessDAO.insert(new Wireless1("Samsung", "Galaxy Buds2 Pro", 120000)); // Вставить новый объект (запись) в таблицу клиентов

            /* NEW */
            System.out.println("Начальная БД:");
            List<Wireless1> list = wirelessDAO.selectAll();
            for (Wireless1 myWireless : list) {
                System.out.println(myWireless.getBrand() + " " + myWireless.getName() + " " + myWireless.getPrice());
            }
            System.out.println();
            
            System.out.println("Поиск по цене 150 000:");
            Wireless1 wireless2 = wirelessDAO.findByPrice(150000); // Поиск записи по возрасту клиента
            System.out.println(wireless2 != null ? wireless2 : "Нет данных"); // Вывод на экран найденной записи
            System.out.println();

            wirelessDAO.delete("Apple", "AirPods MAX"); // Удалениезаписи пл имени и фамилии

            /* NEW */
            System.out.println("Поиск по части брэнда - 'SO':");
            List<Wireless1> wirelesses = wirelessDAO.findByBrand("So"); // Поиск записей по фрагменту имени
            if (wirelesses != null) {
                for (Object element : wirelesses) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();

            wirelessDAO.append("Sony", "wf-1000xm4", 10000); // Добавлние записей

            /* NEW */
            wirelessDAO.deleteByPrice("10000");

            wirelessDAO.append("Sony1", "wh-1200xm4", 12000);
            wirelessDAO.append("Sony2", "wf-1020xm4", 23000);
            wirelessDAO.append("Sony3", "whf-10xm4", 21000);

            /* NEW */
            wirelessDAO.updateBrand("Sony678", "Sony"); // Изменение записей в таблице
            wirelessDAO.updateName("4mx0021-hw", "wh-1200xm4");
            wirelessDAO.updatePriceAtBrand("Sony1", "50000", "12000");

            System.out.println("Данные в таблице после добавлений БД:");

            list = wirelessDAO.selectAll();
            for (Wireless1 myWireless : list) {
                System.out.println(myWireless.getBrand() + " " + myWireless.getName() + " " + myWireless.getPrice());
            }
            System.out.println();
            
            /* NEW */
            
            System.out.println("Поиск по цене меньше 100 000:");
            List<Wireless1> wirelesses2 = wirelessDAO.findByPriceLess(100000); // Поиск записи по возрасту клиента
            if (wirelesses2 != null) {
                for (Object element : wirelesses2) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();

            
            System.out.println("Вывод записей с имением Galaxy Buds2 Pro и бренда Samsung:");

            list = wirelessDAO.select("Samsung", "Galaxy Buds2 Pro");
            for (Wireless1 myWireless7 : list) {
                System.out.println(myWireless7.getBrand() + " " + myWireless7.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
}
