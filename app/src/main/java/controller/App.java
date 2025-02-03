package controller;

import model.persistence.HardCode;

/**
 * Responsible for staring the application.
 */
public class App {

  /**
   * Application starting point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // instance of hardcode
    HardCode persistence = new HardCode();

    MemberController m = new MemberController(persistence);
    ItemController i = new ItemController(persistence);
    ContractController c = new ContractController(persistence);
    TimeController t = new TimeController();

    // Pass controllers to view
    MainController main = new MainController(m, i, c, t);
    
    // Start app by displaying menu
    main.startApplication();
  }
}
