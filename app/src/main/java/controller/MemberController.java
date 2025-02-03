package controller;

import java.util.List;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;
import model.persistence.Persistence;
import view.View;

/**
 * Controller class for managing member-related operations.
 */
public class MemberController {
  private Persistence persistence;
  private View view;

  /**
   * Constructs a MemberController.
   *
   * @param persistence the persistence layer to use for data storage.
   */
  public MemberController(Persistence persistence) {
    this.persistence = persistence;
    this.view = new View();
  }

  public MemberController(MemberController other) {
    this.persistence = other.persistence;
    this.view = new View();
  }

  /**
   * Adds a new member based on user input.
   */
  public void addMember() {
    view.displayAddMemberTitle();
    String name = view.promptForName();
    String email = view.promptForEmail();
    String phoneNumber = view.promptForPhoneNumber();

    if (isDuplicate(email, phoneNumber)) {
      view.displayDuplicationMessage();
      return;
    }
    Member member = new Member(name, email, phoneNumber);
    persistence.addMember(member);
    view.displayCreatedMember(member);
  }

  /**
   * Allows the user to select a member from the list.
   *
   * @return the selected Member object, or null if canceled
   */
  public Member selectMember() {
    List<Member> members = persistence.getMembers();

    if (members.isEmpty()) {
      view.memberNotFound();
      view.returnToMenu();
      return null;
    }

    view.selectMember();
    listMembersSimple();
    int choice = view.getChoice();

    if (choice == 0) {
      return null;
    }

    if (choice > 0 && choice <= members.size()) {
      return members.get(choice - 1);
    } else {
      view.displayDefaultMessage();
      return selectMember();
    }
  }

  /**
   * Checks if a member with the given email or phone number already exists.
   *
   * @param email       the email address to check for duplication
   * @param phoneNumber the phone number to check for duplication
   * @return {true} if a duplicate exists
   */
  private boolean isDuplicate(String email, String phoneNumber) {
    for (Member member : persistence.getMembers()) {
      if (member.getEmail().equals(email)
          || member.getPhoneNumber().equals(phoneNumber)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Deletes a member with the specified member ID.
   *
   * @param member the Member object to delete
   */
  public void deleteMember(Member member) {
    for (Member memberInList : persistence.getMembers()) {
      if (memberInList.getMemberId().equals(member.getMemberId())) {
        persistence.deleteMember(member);
      }
    }
  }

  /**
   * Displays a simple list of all members.
   */
  public void listMembersSimple() {
    view.displayMemberListTitle();
    List<Member> members = persistence.getMembers();
    for (int i = 0; i < members.size(); i++) {
      Member member = members.get(i);
      int numberOfOwnedItems = (int) persistence.getItems().stream()
          .filter(item -> item.getOwner().getMemberId().equals(member.getMemberId()))
          .count();
      view.displayMemberSimple(member, i, numberOfOwnedItems);
    }
  }

  /**
   * Displays a detailed list of all members.
   */
  public void listMembersDetailed(TimeController timeController) {
    view.displayMemberListTitle();
    for (Member member : persistence.getMembers()) {
      view.displayMemberDetailedMember(member);
      handleItemsAndContracts(member, timeController);
    }
  }

  /**
   * Displays detailed information for a specific member.
   *
   * @param member the Member object to view
   */
  public void viewMember(Member member, TimeController timeController) {
    view.displayMemberFullTitle();
    for (Member memberInList : persistence.getMembers()) {
      if (memberInList.getMemberId().equals(member.getMemberId())) {
        view.displayMemberInfo(member);
        handleItemsAndContracts(member, timeController);
        view.returnToMenu();
      }
    }
    view.memberNotFound();
  }

  private void handleItemsAndContracts(Member member, TimeController timeController) {
    for (Item item : persistence.getItems()) {
      if (item.getOwner().getMemberId().equals(member.getMemberId())) {
        view.displayItemInfo(item);

        Boolean isLent = false;
        for (Contract contract : persistence.getContracts()) {
          if (contract.getItem().getItemId().equals(item.getItemId())) {
            if (timeController.getCurrentDay() >= contract.getStartDate()
                && timeController.getCurrentDay() <= contract.getEndDate()) {
              view.displayContractInfo(contract);
              isLent = true;
            }
          }
        }
        if (!isLent) {
          view.displayAvailibility();
        }
      }
    }
  }

  /**
   * Updates the information of a member.
   *
   * @param member the Member object of the member to update
   */
  public void updateMemberInfo(Member member) {
    String newName = view.promptForName();
    String newEmail = view.promptForEmail();
    String newPhone = view.promptForPhoneNumber();

    boolean memberFound = false;
    for (Member memberInList : persistence.getMembers()) {
      if (memberInList.getMemberId().equals(member.getMemberId())) {
        member.setName(newName);
        member.setEmail(newEmail);
        member.setPhoneNumber(newPhone);
        view.displayMemberUpdateConfirmation();
        memberFound = true;
        break;
      }
    }
    if (!memberFound) {
      view.memberNotFound();
    }
  }

  /**
   * Adds a specified amount of credits to a member's account.
   *
   * @param memberId the id of the member
   * @param amount   the amount of credits to add
   */
  public void addCredits(String memberId, int amount) {
    for (Member member : persistence.getMembers()) {
      if (member.getMemberId().equals(memberId)) {
        member.setCredits(member.getCredits() + amount);
        break;
      }
    }
  }

  /**
   * Deducts a specified amount of credits from a member's account.
   *
   * @param memberId the id of the member
   * @param amount   the amount of credits to deduct
   */
  public void deductCredits(String memberId, int amount) {
    for (Member member : persistence.getMembers()) {
      if (member.getMemberId().equals(memberId)) {
        member.setCredits(member.getCredits() - amount);
        break;
      }
    }
  }
}
