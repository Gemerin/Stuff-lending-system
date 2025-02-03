package controller;

import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;
import model.persistence.Persistence;
import view.View;

/**
 * The ContractController class manages contracts between members and items in
 * the system.
 */
public class ContractController {
  private Persistence persistence;
  private View view;

  /**
   * Constructs a ContractController.
   *
   * @param persistence the persistence layer used for storing and retrieving
   *                    contracts
   */
  public ContractController(Persistence persistence) {
    this.persistence = persistence;
    this.view = new View();
  }

  /**
   * Creates a new contract between a member and an item.
   *
   * @param member the member borrowing the item
   * @param item   the item to be borrowed
   */
  public void createContract(Member member, Item item) {
    int startDate = view.promptForStartDate();
    int endDate = view.promptForEndDate();
    Member owner = item.getOwner();

    if (isItemAvailable(item, startDate, endDate) && isCredible(member, item, startDate, endDate)) {
      Contract contract = new Contract(startDate, endDate, item, owner, member);
      persistence.addContract(contract);
      deductCredits(member, contract.getTotalCost());
    }
  }

  /**
   * Lists all contracts for a specified item.
   *
   * @param item the item whose contracts are to be listed
   */
  public void listContractsForItem(Item item) {
    view.displayContractListTitle();
    for (Contract contract : persistence.getContracts()) {
      if (contract.getItem().getItemId().equals(item.getItemId())) {
        view.displayContract(contract);
      }
    }
  }



  /**
   * Checks if the specified item is available for lending during the given date
   * range.
   *
   * @param item      the item to check
   * @param startDate the start date of the requested lending period
   * @param endDate   the end date of the requested lending period
   * @return {true} if the item is available during the specified date range
   */
  public boolean isItemAvailable(Item item, int startDate, int endDate) {
    for (Contract contract : persistence.getContracts()) {
      if (contract.getItem().getItemId().equals(item.getItemId())
          && !(endDate <= contract.getStartDate()
              || startDate >= contract.getEndDate())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the member has enough credits to borrow the item for the specified
   * date range.
   *
   * @param member    the member who wants to borrow the item
   * @param item      the item to be borrowed
   * @param startDate the start date of the borrowing period
   * @param endDate   the end date of the borrowing period
   * @return {true} if the member has enough credits
   */
  private boolean isCredible(Member member, Item item, int startDate, int endDate) {
    return member.getCredits() - ((endDate - startDate) * item.getCostPerDay()) > 0;
  }

  /**
   * Deducts the total cost of borrowing an item from the member's credits.
   *
   * @param member    the member whose credits are to be deducted
   * @param totalCost the total cost to deduct from the member's credits
   */
  public void deductCredits(Member member, double totalCost) {
    member.setCredits(member.getCredits() - totalCost);
  }
}
