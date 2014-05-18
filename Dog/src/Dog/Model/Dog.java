package Dog.Model;


import Dog.Controller.Controller;
import Dog.Model.Model;
import Dog.View.View;

public class Dog {

    public static void main(String[] args) {

        View theView = new View();
        Model theModel = new Model();

        Controller theController = new Controller(theView, theModel);

    }
}
