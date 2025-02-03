package model.domain;

import java.util.UUID;

/**
 * The Contract class.
 */
public class Contract {
  private String contractId;
  private int startDate;
  private int endDate;
  private Item item;
  private Member owner;
  private Member member;
  private double totalCost;

  /**
   * Constructs a new Contract with the specified start date, end date, item,
   * owner, and borrower.
   *
   * @param startDate the start date of the contract
   * @param endDate   the end date of the contract
   * @param item      the item being borrowed
   * @param owner     the owner of the item
   * @param member    the member borrowing the item
   */
  public Contract(int startDate, int endDate, Item item, Member owner, Member member) {
    this.contractId = generateContractId();
    this.startDate = startDate;
    this.endDate = endDate;
    this.item = new Item(item);
    this.owner = new Member(owner);
    this.member = new Member(member);
    this.totalCost = calculateTotalCost();
  }

  /**
   * Generates a unique contract ID using UUID.
   *
   * @return a unique contract ID as a String
   */
  private String generateContractId() {
    return "CONTRACT" + UUID.randomUUID().toString();
  }

  /**
   * Calculates the total cost of the contract based on the duration and the
   * item's cost per day.
   *
   * @return the total cost of the contract
   */
  public double calculateTotalCost() {
    int duration = endDate - startDate;
    return duration * item.getCostPerDay();
  }

  /**
   * Returns the unique ID of the contract.
   *
   * @return the contract ID
   */
  public String getContractId() {
    return contractId;
  }

  /**
   * Returns the item associated with the contract.
   *
   * @return the item being borrowed
   */
  public Item getItem() {
    return new Item(item);
  }

  /**
   * Returns the member borrowing the item.
   *
   * @return the member borrowing the item
   */
  public Member getMember() {
    return new Member(member);
  }

  /**
   * Returns the owner of the item.
   *
   * @return the owner of the item
   */
  public Member getOwner() {
    return new Member(owner);
  }

  /**
   * Returns the start date of the contract.
   *
   * @return the start date of the contract
   */
  public int getStartDate() {
    return startDate;
  }

  /**
   * Returns the end date of the contract.
   *
   * @return the end date of the contract
   */
  public int getEndDate() {
    return endDate;
  }

  /**
   * Returns the total cost of the contract.
   *
   * @return the total cost of the contract
   */
  public double getTotalCost() {
    return totalCost;
  }
}
