package model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Item class represents an item.
 */
public class Item {

  private int idCounter = 0;

  private String name;
  private String itemId;
  private String description;
  private String category;
  private int creationDate;
  private double costPerDay;
  private Member owner;
  private boolean isAvailable;
  private ArrayList<Contract> contracts;

  /**
   * Constructs a new Item with the specified name, description, category, owner,
   * creation date, and cost per day.
   * The item ID is automatically generated, and the item is initially set as
   * available.
   *
   * @param name         the name of the item
   * @param description  a description of the item
   * @param category     the category to which the item belongs
   * @param owner        the member who owns the item
   * @param creationDate the date the item was created
   * @param costPerDay   the cost to borrow the item per day
   */
  public Item(
      String name,
      String description,
      String category,
      Member owner,
      int creationDate,
      double costPerDay) {
    this.setName(name);
    this.itemId = generateItemId();
    this.description = description;
    this.category = category;
    this.owner = new Member(owner);
    this.creationDate = creationDate;
    this.costPerDay = costPerDay;
    this.contracts = new ArrayList<>();
    this.isAvailable = true;
  }

  /**
   * Copy constructor for creating a new {Item} based on an existing
   * {Item} object.
   *
   * @param original the original {@code Item} object to copy
   */
  public Item(Item original) {
    this.name = original.name;
    this.itemId = original.itemId;
    this.description = original.description;
    this.category = original.category;
    this.owner = original.owner;
    this.creationDate = original.creationDate;
    this.costPerDay = original.costPerDay;
    this.contracts = original.contracts;
    this.isAvailable = original.isAvailable;
  }

  /**
   * Generates a unique item ID for the item using an incrementing counter.
   *
   * @return a unique item ID as a String
   */
  private String generateItemId() {
    return "ITEM" + String.format("%03d", ++idCounter);
  }

  /**
   * Returns whether the item is currently available for borrowing.
   *
   * @return {@code true} if the item is available; {@code false} otherwise
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Sets the availability status of the item.
   *
   * @param isAvailable the availability status to set
   */
  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  /**
   * Returns the unique ID of the item.
   *
   * @return the item ID
   */
  public String getItemId() {
    return itemId;
  }

  /**
   * Sets the unique ID of the item.
   *
   * @param itemId the item ID to set
   */
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  /**
   * Returns the name of the item.
   *
   * @return the name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the item.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    // No validation required.
    this.name = name;
  }

  /**
   * Returns the description of the item.
   *
   * @return the description of the item
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the item.
   *
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the category of the item.
   *
   * @return the category of the item
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the category of the item.
   *
   * @param category the category to set
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Returns the creation date of the item.
   *
   * @return the creation date of the item
   */
  public int getCreationDate() {
    return creationDate;
  }

  /**
   * Returns the cost per day to borrow the item.
   *
   * @return the cost per day as a double
   */
  public double getCostPerDay() {
    return costPerDay;
  }

  /**
   * Sets the cost per day to borrow the item.
   *
   * @param costPerDay the cost per day to set
   */
  public void setCostPerDay(double costPerDay) {
    this.costPerDay = costPerDay;
  }

  /**
   * Returns the list of contracts associated with the item.
   *
   * @return the list of contracts for the item
   */
  public List<Contract> getContracts() {
    return new ArrayList<>(contracts);
  }

  /**
   * Adds a contract to the item's list of contracts.
   *
   * @param contract the contract to add
   */
  public void addContract(Contract contract) {
    this.contracts.add(contract);
  }

  /**
   * Returns the a copy of owner of the item.
   *
   * @return the member who owns the item
   */
  public Member getOwner() {
    return new Member(owner);
  }

  /**
   * Sets the owner of the item.
   *
   * @param owner the member to set as the owner
   */
  public void setOwner(Member owner) {
    this.owner = new Member(owner);
  }

  /**
   * Returns a string representation of the item, including its name, description,
   * category, and cost per day.
   *
   * @return a string representation of the item
   */
  @Override
  public String toString() {
    return ("Item Name: "
        + name
        + "\nDescription: "
        + description
        + "\nCategory: "
        + category
        + "\nCost Per Day: "
        + costPerDay);
  }
}
