package model.persistence;

import java.util.ArrayList;
import java.util.List;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * The HardCode class provides an in-memory implementation of the Persistence
 * interface.
 */
public class HardCode implements Persistence {
  private List<Member> members;
  private List<Item> items;
  private List<Contract> contracts;

  /**
   * Constructs a new HardCode instance and initializes hardcoded data for
   * members, items, and contracts.
   */
  public HardCode() {
    members = new ArrayList<>();
    items = new ArrayList<>();
    contracts = new ArrayList<>();

    // Add hardcoded members
    Member m1 = new Member("Member1", "m1@example.com", "1234567890");
    m1.setCredits(500);
    Member m2 = new Member("Member2", "m2@example.com", "0987654321");
    m2.setCredits(100);
    Member m3 = new Member("Member3", "m3@example.com", "12398700");
    m3.setCredits(100);
    members.add(m1);
    members.add(m2);
    members.add(m3);

    // Add hardcoded items
    Item i1 = new Item("Hammer", "Heavy hammer", "Tool", m1, 0, 50.0);
    items.add(i1);
    Item i2 = new Item("Lawnmower", "Lawnmower with cable", "Tool", m1, 0, 10);
    items.add(i2);

    // Add hardcoded contracts
    Contract c1 = new Contract(5, 7, i2, m1, m3);
    contracts.add(c1);
  }

  /**
   * Retrieves the list of hardcoded members.
   *
   * @return a list of hardcoded members
   */
  @Override
  public List<Member> getMembers() {
    return new ArrayList<>(members);
  }

  /**
   * Retrieves the list of hardcoded items.
   *
   * @return a list of hardcoded items
   */
  @Override
  public List<Item> getItems() {
    return new ArrayList<>(items);
  }

  /**
   * Retrieves the list of hardcoded contracts.
   *
   * @return a list of hardcoded contracts
   */
  @Override
  public List<Contract> getContracts() {
    return new ArrayList<>(contracts);
  }

  /**
   * Adds a member to the system.
   *
   * @param member the member to add
   */
  @Override
  public void addMember(Member member) {
    this.members.add(member);
  }

  /**
   * Adds an item to the system.
   *
   * @param item the item to add
   */
  @Override
  public void addItem(Item item) {
    this.items.add(item);
  }

  /**
   * Adds a contract to the system.
   *
   * @param contract the contract to add
   */
  @Override
  public void addContract(Contract contract) {
    this.contracts.add(contract);
  }

  /**
   * Removes a member from the system.
   *
   * @param member the member to remove
   */
  @Override
  public void deleteMember(Member member) {
    this.members.remove(member);
  }

  /**
   * Removes an item from the system.
   *
   * @param item the item to remove
   */
  @Override
  public void deleteItem(Item item) {
    this.items.remove(item);
  }
}
