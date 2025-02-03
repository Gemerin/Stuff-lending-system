package controller;

import model.domain.Item;
import model.domain.Member;
import model.menu.ItemMainMenu.ItemMainMenuChoice;
import model.menu.ItemSubMenu.ItemSubMenuChoice;
import model.menu.MainMenu.MainMenuChoice;
import model.menu.MemberMainMenu.MemberMainMenuChoice;
import model.menu.MemberSubMenu.MemberSubMenuChoice;
import view.View;

/**
 * The MainController class.
 */
public class MainController {

  private MemberController memberController;
  private ItemController itemController;
  private ContractController contractController;
  private TimeController timeController;
  private View view;

  /**
   * Constructs a MainController with the specified controllers.
   *
   * @param memberController   the controller for member-related operations
   * @param itemController     the controller for item-related operations
   * @param contractController the controller for contract-related operations
   * @param timeController     the controller for time management
   */
  public MainController(MemberController memberController, ItemController itemController,
      ContractController contractController, TimeController timeController) {
    this.memberController = new MemberController(memberController);
    this.itemController = new ItemController(itemController);
    this.contractController = contractController;
    this.timeController = timeController;
    this.view = new View();
  }

  /**
   * Starts the main application loop, displaying the menu and handling user
   * choices.
   */
  public void startApplication() {
    boolean running = true;
    while (running) {
      view.displayMainMenu();
      MainMenuChoice choice = view.getMainMenuChoice();
      running = handleMainMenuChoice(choice);
    }
  }

  private boolean handleMainMenuChoice(MainMenuChoice choice) {
    switch (choice) {
      case MEMBER_MENU:
        displayMemberMainMenu();
        break;
      case ITEM_MENU:
        displayItemMainMenu();
        break;
      case ADVANCE_TIME:
        timeController.advanceDays();
        break;
      case EXIT:
        view.displayExitMessage();
        return false;
      case INVALID:
      default:
        view.displayDefaultMessage();
    }
    return true;
  }

  /**
   * Displays the member main menu and handles user choices in the member context.
   */
  public void displayMemberMainMenu() {
    boolean running = true;
    while (running) {
      view.displayMemberMainMenu();
      MemberMainMenuChoice choice = view.getMemberMainMenuChoice();
      running = handleMemberChoice(choice);
    }
  }

  private boolean handleMemberChoice(MemberMainMenuChoice choice) {
    switch (choice) {
      case ADD_MEMBER:
        memberController.addMember();
        break;
      case SELECT_MEMBER:
        selectMember();
        break;
      case LIST_MEMBERS_SIMPLE:
        memberController.listMembersSimple();
        view.returnToMenu();
        break;
      case LIST_MEMBERS_VERBOSE:
        memberController.listMembersDetailed(timeController);
        view.returnToMenu();
        break;
      case RETURN:
        view.returnToMenu();
        return false;
      case INVALID:
      default:
        view.displayDefaultMessage();
    }
    return true;
  }

  /**
   * Allows the user to select a member and displays the submenu for the selected
   * member.
   */
  public void selectMember() {
    Member member = memberController.selectMember();
    displayMemberSubMenu(member);
  }

  /**
   * Displays the member submenu for a specific member and handles user choices.
   *
   * @param member the member whose submenu is being displayed
   */
  public void displayMemberSubMenu(Member member) {
    boolean running = true;
    while (running) {
      view.displayMemberSubMenu(member);
      MemberSubMenuChoice choice = view.getMemberSubMenuChoice();
      running = handleSubMemberChoice(choice, member);
    }
  }

  private boolean handleSubMemberChoice(MemberSubMenuChoice choice, Member member) {
    switch (choice) {
      case DELETE_MEMBER:
        memberController.deleteMember(member);
        return false;
      case UPDATE_MEMBER:
        memberController.updateMemberInfo(member);
        break;
      case FULL_INFO:
        memberController.viewMember(member, timeController);
        view.returnToMenu();
        break;
      case ADD_ITEM:
        itemController.addItem(member, timeController);
        break;
      case DELETE_ITEM:
        deleteItem(member);
        break;
      case BORROW_ITEM:
        borrowItem(member);
        break;
      case RETURN:
        view.returnToMenu();
        return false;
      case INVALID:
      default:
        view.displayDefaultMessage();
    }
    return true;
  }

  /**
   * Allows a member to delete one of their items by selecting the item to delete.
   *
   * @param member the member who owns the item
   */
  private void deleteItem(Member member) {
    Item item = itemController.selectItem();
    itemController.deleteItem(item);
  }

  /**
   * Allows a member to borrow an item by selecting an item and creating a
   * contract for the borrowing.
   *
   * @param member the member who wants to borrow the item
   */
  private void borrowItem(Member member) {
    Item item = itemController.selectItem();
    contractController.createContract(member, item);
  }

  /**
   * Displays the item main menu and handles user choices in the item context.
   */
  public void displayItemMainMenu() {
    boolean running = true;
    while (running) {
      view.displayItemMainMenu();
      ItemMainMenuChoice choice = view.getItemMainMenuChoice();
      running = handleItemChoice(choice);
    }
  }

  private boolean handleItemChoice(ItemMainMenuChoice choice) {
    switch (choice) {
      case SELECT_ITEM:
        selectItem();
        break;
      case RETURN:
        view.returnToMenu();
        return false;
      case INVALID:
      default:
        view.displayDefaultMessage();
    }
    return true;
  }

  /**
   * Allows the user to select an item and displays the submenu for the selected
   * item.
   */
  public void selectItem() {
    Item item = itemController.selectItem();
    displayItemSubMenu(item);
  }

  /**
   * Displays the item submenu for a specific item and handles user choices.
   *
   * @param item the item whose submenu is being displayed
   */
  public void displayItemSubMenu(Item item) {
    boolean running = true;
    while (running) {
      view.displayItemSubMenu(item);
      ItemSubMenuChoice choice = view.getItemSubMenuChoice();
      running = handleSubItemChoice(choice, item);
    }
  }

  private boolean handleSubItemChoice(ItemSubMenuChoice choice, Item item) {
    switch (choice) {
      case DELETE_ITEM:
        itemController.deleteItem(item);
        return false;
      case UPDATE_ITEM:
        itemController.updateItemInfo(item);
        break;
      case ITEM_INFORMATION:
        view.displayItemInfo(item);
        view.returnToMenu();
        break;
      case ITEM_CONTRACTS:
        contractController.listContractsForItem(item);
        view.returnToMenu();
        break;
      case RETURN:
        view.returnToMenu();
        return false;
      case INVALID:
      default:
        view.displayDefaultMessage();
    }
    return true;
  }
}
