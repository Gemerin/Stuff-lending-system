package view;

import java.util.Scanner;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;
import model.menu.ItemMainMenu.ItemMainMenuChoice;
import model.menu.ItemSubMenu.ItemSubMenuChoice;
import model.menu.MainMenu.MainMenuChoice;
import model.menu.MemberMainMenu.MemberMainMenuChoice;
import model.menu.MemberSubMenu.MemberSubMenuChoice;

/**
 * View gathers input and sends to the controller.
 */
public class View {

  private Scanner scanner;

  /**
   * Constructs a new View instance and initializes the scanner for user input.
   */
  public View() {
    this.scanner = new Scanner(System.in, "UTF-8");
  }

  /**
   * Closes the scanner resource when it's no longer needed.
   */
  public void closeScanner() {
    if (scanner != null) {
      scanner.close();
    }
  }

  /**
   * Displays the main menu to the user.
   */
  public void displayMainMenu() {
    clearConsole();
    System.out.println("The Stuff Lending Menu");
    System.out.println("----------------------");
    System.out.println("1. Member Menu");
    System.out.println("2. Item Menu");
    System.out.println("3. Advance Time");
    System.out.println("0. Exit");
  }

  /**
   * Retrieves the selected main menu choice based on the user's input.
   *
   * @return the corresponding {MainMenuChoice} based on the user's input.
   */
  public MainMenuChoice getMainMenuChoice() {
    int choice = getChoice();
    switch (choice) {
      case 1:
        return MainMenuChoice.MEMBER_MENU;
      case 2:
        return MainMenuChoice.ITEM_MENU;
      case 3:
        return MainMenuChoice.ADVANCE_TIME;
      case 0:
        return MainMenuChoice.EXIT;
      default:
        return MainMenuChoice.INVALID;
    }
  }

  /**
   * Displays the member menu to the user.
   */
  public void displayMemberMainMenu() {
    clearConsole();
    System.out.println("The Member Menu");
    System.out.println("-----------------------------");
    System.out.println("1. Add Member");
    System.out.println("2. Select Member");
    System.out.println("3. List All Members - Simple");
    System.out.println("4. List All Members - Verbose");
    System.out.println("0. Return");
  }

  /**
   * Retrieves the selected member main menu choice based on the user's input.
   *
   * @return the corresponding {MemberMainMenuChoice} based on the user's input.
   */
  public MemberMainMenuChoice getMemberMainMenuChoice() {
    int choice = getChoice();
    switch (choice) {
      case 1:
        return MemberMainMenuChoice.ADD_MEMBER;
      case 2:
        return MemberMainMenuChoice.SELECT_MEMBER;
      case 3:
        return MemberMainMenuChoice.LIST_MEMBERS_SIMPLE;
      case 4:
        return MemberMainMenuChoice.LIST_MEMBERS_VERBOSE;
      case 0:
        return MemberMainMenuChoice.RETURN;
      default:
        return MemberMainMenuChoice.INVALID;
    }
  }

  /**
   * Displays the member submenu for a specific member.
   *
   * @param member the member for whom the submenu is displayed
   */
  public void displayMemberSubMenu(Member member) {
    clearConsole();
    System.out.println("The Member Menu (" + member.getName() + ")");
    System.out.println("-----------------------------");
    System.out.println("1. Delete Member");
    System.out.println("2. Update Member");
    System.out.println("3. View Full Info");
    System.out.println("4. Add Item");
    System.out.println("5. Delete Item");
    System.out.println("6. Borrow Item");
    System.out.println("0. Return");
  }

  /**
   * Retrieves the selected member sub menu choice based on the user's input.
   *
   * @return the corresponding {MemberSubMenuChoice} based on the user's input.
   */
  public MemberSubMenuChoice getMemberSubMenuChoice() {
    int choice = getChoice();
    switch (choice) {
      case 1:
        return MemberSubMenuChoice.DELETE_MEMBER;
      case 2:
        return MemberSubMenuChoice.UPDATE_MEMBER;
      case 3:
        return MemberSubMenuChoice.FULL_INFO;
      case 4:
        return MemberSubMenuChoice.ADD_ITEM;
      case 5:
        return MemberSubMenuChoice.DELETE_ITEM;
      case 6:
        return MemberSubMenuChoice.BORROW_ITEM;
      case 0:
        return MemberSubMenuChoice.RETURN;
      default:
        return MemberSubMenuChoice.INVALID;
    }
  }

  /**
   * Displays the item main menu to the user.
   */
  public void displayItemMainMenu() {
    clearConsole();
    System.out.println("The Item Menu");
    System.out.println("-----------------------------");
    System.out.println("1. Select Item");
    System.out.println("0. Return");
  }

  /**
   * Retrieves the selected item main menu choice based on the user's input.
   *
   * @return the corresponding {ItemMainMenuChoice} based on the user's input.
   */
  public ItemMainMenuChoice getItemMainMenuChoice() {
    int choice = getChoice();
    switch (choice) {
      case 1:
        return ItemMainMenuChoice.SELECT_ITEM;
      case 0:
        return ItemMainMenuChoice.RETURN;
      default:
        return ItemMainMenuChoice.INVALID;
    }
  }

  /**
   * Displays the submenu for a specific item.
   *
   * @param item the item for which the submenu is displayed
   */
  public void displayItemSubMenu(Item item) {
    clearConsole();
    System.out.println("The Item Menu (" + item.getName() + ")");
    System.out.println("-----------------------------");
    System.out.println("1. Delete Item");
    System.out.println("2. Update Item");
    System.out.println("3. View Item Information");
    System.out.println("4. View Contracts");
    System.out.println("0. Return");
  }

  /**
   * Retrieves the selected item sub menu choice based on the user's input.
   *
   * @return the corresponding {ItemSubMenuChoice} based on the user's input.
   */
  public ItemSubMenuChoice getItemSubMenuChoice() {
    int choice = getChoice();
    switch (choice) {
      case 1:
        return ItemSubMenuChoice.DELETE_ITEM;
      case 2:
        return ItemSubMenuChoice.UPDATE_ITEM;
      case 3:
        return ItemSubMenuChoice.ITEM_INFORMATION;
      case 4:
        return ItemSubMenuChoice.ITEM_CONTRACTS;
      case 0:
        return ItemSubMenuChoice.RETURN;
      default:
        return ItemSubMenuChoice.INVALID;
    }
  }

  /**
   * Prompts the user to enter a choice and returns it as an integer.
   *
   * @return the user's choice as an integer.
   */
  public int getChoice() {
    System.out.print("Your choice: ");
    String input = scanner.nextLine();
    while (input.isEmpty()) {
      System.out.print("Invalid input. Please enter a number: ");
      input = scanner.nextLine();
    }
    return Integer.parseInt(input);
  }

  /**
   * Prompts the user to enter a name.
   *
   * @return the name entered by the user
   */
  public String promptForName() {
    System.out.print("Enter Name: ");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter an email address.
   *
   * @return the email address entered by the user
   */
  public String promptForEmail() {
    System.out.print("Enter Email: ");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter a phone number.
   *
   * @return the phone number entered by the user
   */
  public String promptForPhoneNumber() {
    System.out.print("Enter Phone: ");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter a description.
   *
   * @return the description entered by the user
   */
  public String promptForDescription() {
    System.out.print("Enter Description: ");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter a category.
   *
   * @return the category entered by the user
   */
  public String promptForCategory() {
    System.out.print("Enter Category: ");
    return scanner.nextLine();
  }

  /**
   * Prompts the user to enter the cost per day.
   *
   * @return the cost per day as a Double
   */
  public Double promptForCostPerDay() {
    System.out.print("Enter Cost Per Day: ");
    return scanner.nextDouble();
  }

  /**
   * Prompts the user to enter the number of days to advance the system.
   *
   * @return the number of days as an integer
   */
  public int promptForDays() {
    System.out.print("Enter Days To Advance: ");
    return scanner.nextInt();
  }

  /**
   * Prompts the user to enter the start date for lending an item.
   *
   * @return the start date as an integer
   */
  public int promptForStartDate() {
    System.out.print("Enter the start date for the lending preiod: ");
    return scanner.nextInt();
  }

  /**
   * Prompts the user to enter the end date for lending an item.
   *
   * @return the end date as an integer
   */
  public int promptForEndDate() {
    System.out.print("Enter the end date for the lending preiod: ");
    return scanner.nextInt();
  }

  /**
   * Displays a default message when an invalid choice is made.
   */
  public void displayDefaultMessage() {
    System.out.println("Invalid choice, please try again.");
    returnToMenu();
  }

  /**
   * Displays a message when exiting the application.
   */
  public void displayExitMessage() {
    System.out.println("Exiting...");
  }

  /**
   * Displays a return message when navigating back to the previous menu.
   */
  public void displayReturnMessage() {
    System.out.println("Returning...");
  }

  /**
   * Displays an error message.
   *
   * @param message the error message to display
   */
  public void displayErrorMessage(String message) {
    System.out.println("Error: " + message);
  }

  /**
   * Displays a message when a duplicate number or address is detected.
   */
  public void displayDuplicationMessage() {
    System.out.println("A member with the same email or phone number already exists.");
  }

  /**
   * Displays a confirmation message after updating member information.
   */
  public void displayMemberUpdateConfirmation() {
    System.out.println("Member information updated successfully.");
  }

  /**
   * Displays a message indicating a member was successfully created.
   *
   * @param member the member that was created
   */
  public void displayCreatedMember(Member member) {
    clearConsole();
    System.out.println("Member added successfully.");
    System.out.println("--------------------------");
    System.out.println("Namn: " + member.getName());
    System.out.println("E-post: " + member.getEmail());
    System.out.println("Telefonnummer: " + member.getPhoneNumber());
    returnToMenu();
  }

  /**
   * Displays a simple representation of a member.
   *
   * @param member             the member to display
   * @param i                  the index number
   * @param numberOfOwnedItems the number of items owned by the member
   */
  public void displayMemberSimple(Member member, int i, int numberOfOwnedItems) {
    System.out.println((i + 1) + ". ID: " + member.getMemberId()
        + ". Name: " + member.getName()
        + ", Email: " + member.getEmail()
        + ", Credits: " + member.getCredits()
        + ", Owned Items: " + numberOfOwnedItems);
  }

  /**
   * Displays a title indicating that the user is adding a member.
   */
  public void displayAddMemberTitle() {
    clearConsole();
    System.out.println("Add Member");
    System.out.println("-----------------");
  }

  /**
   * Displays detailed information about a member.
   *
   * @param member the member whose details are to be displayed
   */
  public void displayMemberDetailedMember(Member member) {
    System.out.println();
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Owned Items:");
  }

  /**
   * Displays a title for the full member information view.
   */
  public void displayMemberFullTitle() {
    clearConsole();
    System.out.println("Full Member Info");
    System.out.println("-----------------");
  }

  /**
   * Displays a title for the member list.
   */
  public void displayMemberListTitle() {
    clearConsole();
    System.out.println("Member list");
    System.out.println("-----------------");
  }

  /**
   * Displays a title for the item list.
   */
  public void displayItemListTitle() {
    clearConsole();
    System.out.println("Item list");
    System.out.println("-----------------");
  }

  /**
   * Displays a title for the contract list.
   */
  public void displayContractListTitle() {
    clearConsole();
    System.out.println("Contract list");
    System.out.println("-----------------");
  }

  /**
   * Displays the details of the specified contract.
   *
   * @param contract the contract whose details are to be displayed
   */
  public void displayContract(Contract contract) {
    System.out.println("Contract ID: " + contract.getContractId());
    System.out.println("Duration " + contract.getStartDate() + " - " + contract.getEndDate());
    System.out.println("Owner: " + contract.getOwner().getName());
    System.out.println("Borrower: " + contract.getMember().getName());
    System.out.println("Total Cost: " + contract.getTotalCost());
    System.out.println();
  }

  /**
   * Displays information about an item.
   *
   * @param item the item to display
   */
  public void displayItemInfo(Item item) {
    System.out.println();
    System.out.println("  Item Name: " + item.getName());
    System.out.println("  Item ID: " + item.getItemId());
    System.out.println("  Description: " + item.getDescription());
    System.out.println("  Category: " + item.getCategory());
    System.out.println("  Cost Per Day: " + item.getCostPerDay());
  }

  /**
   * Displays a list of selectable items.
   *
   * @param item the item to display
   * @param i    the index number of the item in the list
   */
  public void displayItemSelection(Item item, int i) {
    System.out.println((i + 1) + "." + " Item Name: " + item.getName());
    System.out.println("   Item ID: " + item.getItemId());
    System.out.println("   Description: " + item.getDescription());
    System.out.println("   Category: " + item.getCategory());
    System.out.println("   Cost Per Day: " + item.getCostPerDay());
    System.out.println();
  }

  /**
   * Displays information about a contract.
   *
   * @param contract the contract to display
   */
  public void displayContractInfo(Contract contract) {
    System.out.println("  Currently Lent To: " + contract.getMember().getName());
    System.out.println(
        "  Lending Period: " + contract.getStartDate() + " to " + contract.getEndDate());
    System.out.println();
  }

  /**
   * Displays information about an item contract.
   *
   * @param contract the contract to display
   */
  public void displayItemContractInfo(Contract contract) {
    System.out.println("  Owned by: " + contract.getOwner().getName());
    System.out.println("  Lent To: " + contract.getMember().getName());
    System.out.println(
        "  Lending Period: " + contract.getStartDate() + " to " + contract.getEndDate());
    System.out.println();
  }

  /**
   * Displays a message indicating the item is currently available.
   */
  public void displayAvailibility() {
    System.out.println("  Currently Available");
    System.out.println();
  }

  /**
   * Displays detailed information about a member.
   *
   * @param member the member whose information is to be displayed
   */
  public void displayMemberInfo(Member member) {
    System.out.println("ID: " + member.getMemberId());
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone: " + member.getPhoneNumber());
    System.out.println("Credits: " + member.getCredits());
    System.out.println("Owned Items:");
  }

  /**
   * Displays a message prompting the user to select a member.
   */
  public void selectMember() {
    clearConsole();
    System.out.println("Select a Member:");
    System.out.println("-----------------------------");
  }

  /**
   * Displays a message prompting the user to select an item.
   */
  public void selectItem() {
    clearConsole();
    System.out.println("Select an Item:");
    System.out.println("-----------------------------");
  }

  /**
   * Displays a message indicating that a member was not found.
   */
  public void memberNotFound() {
    System.out.println("Member not found.");
  }

  /**
   * Displays a message indicating that no items were found.
   */
  public void itemsNotFound() {
    System.out.println("No items available.");
  }

  /**
   * Clears the console by printing multiple empty lines.
   */
  private void clearConsole() {
    for (int i = 0; i < 20; i++) {
      System.out.println();
    }
  }

  /**
   * Prompts the user to press Enter to return to the menu.
   */
  public void returnToMenu() {
    System.out.println("\nPress Enter to return to the menu...");
    scanner.nextLine();
  }
}
