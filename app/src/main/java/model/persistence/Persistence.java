package model.persistence;

import java.util.List;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * The Persistence interface defines the methods for retrieving members, items,
 * and contracts in the system.
 */
public interface Persistence {
  /**
   * Retrieves the list of members stored in the system.
   *
   * @return a list of members
   */
  List<Member> getMembers();

  /**
   * Retrieves the list of items stored in the system.
   *
   * @return a list of items
   */
  List<Item> getItems();

  /**
   * Retrieves the list of contracts stored in the system.
   *
   * @return a list of contracts
   */
  List<Contract> getContracts();

  /**
   * Adds a member to the system.
   *
   * @param member the member to add
   */
  void addMember(Member member);

  /**
   * Adds an item to the system.
   *
   * @param item the item to add
   */
  void addItem(Item item);

  /**
   * Adds a contract to the system.
   *
   * @param contract the contract to add
   */
  void addContract(Contract contract);

  /**
   * Removes a member from the system.
   *
   * @param member the member to remove
   */
  void deleteMember(Member member);

  /**
   * Removes an item from the system.
   *
   * @param item the item to remove
   */
  void deleteItem(Item item);
}
