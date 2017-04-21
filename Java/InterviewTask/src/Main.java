
public class Main {

    public static void main(String[] args) {

        //создаем объекты дочерних классов
        PaidService service1 = new FixedHourlyCostPaidService("service1", "Google Orkut", 11);
        PaidService service2 = new FixedHourlyCostPaidService("service2", "Google Voice", 9.4);
        PaidService service5 = new FixedMonthlyCostPaidService("service5", "YouTube", 8064);
        PaidService service3 = new FixedHourlyCostPaidService("service3", "Mandrill", 11.2) ;
        PaidService service4 = new FixedHourlyCostPaidService("service4", "Google Finance", 7.8);
        PaidService service7 = new FixedMonthlyCostPaidService("service7", "Google Building Maker", 5347)  ;
        PaidService service6 = new FixedMonthlyCostPaidService("service6", "LinkedIn", 6863) ;

        //создаем массив из созданных ранее объектов
        PaidService[] services = new PaidService[] {
                service1, service2, service5, service3, service4, service7, service6
        };

        /* сортируем исходный массив способом пузырька (немного переделана, так как мы оперируем с объектами PaidService, а не с целыми числами)
           см. ссылку https://ru.wikibooks.org/wiki/%D0%A0%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D0%BE%D0%B2/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0/%D0%9F%D1%83%D0%B7%D1%8B%D1%80%D1%8C%D0%BA%D0%BE%D0%BC
         */
        PaidService[] sortedServices = sortServices(services);

        //задание 1 - выводим все отсортированные сервисы
        System.out.println("\n----- 1. ALL SERVICES -----\n");
        for (PaidService service : sortedServices) {
            System.out.println(service.getId() + " / " + service.getName()
                    + " / " + service.calculateAverageMonthlyCosts());
        }

        //задание 2 - вывести name для первых 5 сервисов из отсортированного массива
        System.out.println("\n----- 2. FIRST 5 SERVICES -----\n");
        for (int i=0; i<5; i++) {
            System.out.println(sortedServices[i].getName());
        }

        //задание 3 - вывести последние 3 сервиса из отсортированного массива
        System.out.println("\n----- 3. LAST 3 SERVICES -----\n");
        for (int i=sortedServices.length - 3; i<sortedServices.length; i++) {
            System.out.println(sortedServices[i].getId());
        }

        //задание 4 - сравнить 2 сервиса (в нашем случае YouTube и Mandrill
        System.out.println("\n----- 4. COMPARE 2 SERVICES -----\n");
        compareServices(service5, service3);

    }

    public static PaidService[] sortServices(PaidService[] services) {
        for (int i = services.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                double costs1 = services[j].calculateAverageMonthlyCosts();
                double costs2 = services[j+1].calculateAverageMonthlyCosts();
                //самая главная переделка в условии if - сравниваем средние месячные costs, если они одинаковые, тогда сравниваем имя сервиса
                if (costs1 < costs2 || (costs1 == costs2 && services[j].getName().compareTo(services[j+1].getName()) > 0)) {
                    PaidService t = services[j]; //оперируем с объектами типа PaidService - тип объектов массива
                    services[j] = services[j + 1];
                    services[j + 1] = t;
                }
            }
        }
        return services;
    }

    public static void compareServices(PaidService service1, PaidService service2) {
        double costs1 = service1.calculateAverageMonthlyCosts();
        double costs2 = service2.calculateAverageMonthlyCosts();

        if (costs1 > costs2) {
            System.out.println(service1.getName());
            System.out.println(service2.getName());
        } else if (costs2 > costs1) {
            System.out.println(service2.getName());
            System.out.println(service1.getName());
        } else { //одинаковые
            System.out.println(service1.getName() + " / " + service2.getName());
        }
    }
}
