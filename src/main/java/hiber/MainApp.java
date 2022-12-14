package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User(new Car("Model111", 111),"User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User(new Car("Model222", 222),"User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User(new Car("Model333", 333), "User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User(new Car("Model 444", 444), "User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();

         List<User> carOwner = userService.getUserByCar("model111", 111);
         for (User userA : carOwner) {
            System.out.println(userA.getFirstName() +" " + userA.getLastName());
            System.out.println("Car: " + userA.getCar());
         }
      }

      context.close();
   }
}
