package controller;

import java.util.List;
import model.domain.Item;
import model.domain.Member;
import model.persistence.Persistence;
import view.View;

/**
 * The ItemController class.
 */
public class ItemController {
  private Persistence persistence;
  private View view;

  /**
   * Constructs an ItemController.
   *
   * @param persistence the persistence layer used for storing and retrieving
   *                    items
   */
  public ItemController(Persistence persistence) {
    this.persistence = persistence;
    this.view = new View();

  }

  public ItemController(ItemController other) {
    this.persistence = other.persistence;
    this.view = new View();
  }

  /**
   * Adds a new item to the system for a specific member.
   *
   * @param owner          the member who owns the item
   * @param timeController the controller used to get the current day for item
   *                       creation
   */
  public void addItem(Member owner, TimeController timeController) {
    String name = view.promptForName();
    String description = view.promptForDescription();
    String category = view.promptForCategory();
    int creationDate = timeController.getCurrentDay();
    Double costPerDay = view.promptForCostPerDay();

    Item item = new Item(
        name,
        description,
        category,
        owner,
        creationDate,
        costPerDay);
    
    persistence.addItem(item);
    rewardCredits(owner);
    //memberController.addCredits(owner.getMemberId(), 100);
  }

  /**
   * Rewards the item owner with credits for adding a new item.
   *
   * @param owner the member who is rewarded with credits
   */
  private void rewardCredits(Member owner) {
    Double currentCredit = owner.getCredits();
    Double newCredit = currentCredit + 100;
    owner.setCredits(newCredit);
  }

  /**
   * Allows the user to select an item from the list of items.
   *
   * @return the selected item
   */
  public Item selectItem() {
    List<Item> items = persistence.getItems();

    if (items.isEmpty()) {
      view.itemsNotFound();
      view.returnToMenu();
      return null;
    }

    view.selectItem();
    listItems();
    int choice = view.getChoice();

    if (choice == 0) {
      return null;
    }

    if (choice > 0 && choice <= items.size()) {
      return items.get(choice - 1);
    } else {
      view.displayDefaultMessage();
      return selectItem();
    }
  }

  /**
   * Deletes a specified item from the system.
   *
   * @param item the item to be deleted
   */
  public void deleteItem(Item item) {
    for (Item itemInList : persistence.getItems()) {
      if (itemInList.getItemId().equals(item.getItemId())) {
        persistence.deleteItem(item);
      }
    }
  }

  /**
   * Lists all items in the system by displaying them in the view.
   */
  public void listItems() {
    view.displayItemListTitle();
    if (persistence.getItems().isEmpty()) {
      view.itemsNotFound();
      return;
    }
    List<Item> items = persistence.getItems();
    for (int i = 0; i < items.size(); i++) {
      Item item = items.get(i);
      view.displayItemSelection(item, i);
    }
  }

  /**
   * Updates the information of a specified item by prompting the user for new
   * details.
   *
   * @param item the item whose information is to be updated
   */
  public void updateItemInfo(Item item) {
    String newName = view.promptForName();
    String newDescription = view.promptForDescription();
    String newCategory = view.promptForCategory();
    Double newCostPerDay = view.promptForCostPerDay();

    boolean itemFound = false;
    for (Item itemInList : persistence.getItems()) {
      if (itemInList.getItemId().equals(item.getItemId())) {
        item.setName(newName);
        item.setDescription(newDescription);
        item.setCategory(newCategory);
        item.setCostPerDay(newCostPerDay);
        itemFound = true;
        break;
      }
    }
    if (!itemFound) {
      view.itemsNotFound();
    }
  }
}
