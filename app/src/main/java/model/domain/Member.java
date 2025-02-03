package model.domain;

import java.util.Random;

/**
 * The Member class.
 */
public class Member {

  private Random random;
  private double credit;
  private String memberId;
  private String username;
  private String email;
  private String profileDetails;
  private boolean isBlocked;
  private boolean isDeleted;
  private String phoneNumber;

  /**
   * Constructs a new Member with the specified username, email, and phone number.
   *
   * @param name        the name of the member
   * @param email       the email address of the member
   * @param phoneNumber the phone number of the member
   */
  public Member(String name, String email, String phoneNumber) {
    this.random = new Random();
    this.username = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.memberId = generateMemberId();
    this.credit = 0;
    this.profileDetails = "";
    this.isBlocked = false;
    this.isDeleted = false;
  }

  /**
   * Copy constructor for creating a new {Member} based on an existing {Member}
   * object.
   *
   * @param original the original {@code Member} object to copy
   */
  public Member(Member original) {
    this.username = original.username;
    this.email = original.email;
    this.phoneNumber = original.phoneNumber;
    this.memberId = original.memberId;
    this.credit = original.credit;
  }

  /**
   * Generates a unique member ID using a random number generator.
   *
   * @return a unique member ID as a String
   */
  public String generateMemberId() {
    int randomNumber = random.nextInt(100000);
    return "MEM" + String.format("%06d", randomNumber);
  }

  /**
   * Returns the name (username) of the member.
   *
   * @return the username of the member
   */
  public String getName() {
    return username;
  }

  /**
   * Sets the name (username) of the member.
   *
   * @param name the username to set
   */
  public void setName(String name) {
    this.username = name;
  }

  /**
   * Returns the email address of the member.
   *
   * @return the email address of the member
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the member.
   *
   * @param email the email address to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns the phone number of the member.
   *
   * @return the phone number of the member
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the phone number of the member.
   *
   * @param phoneNumber the phone number to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns the current credit balance of the member.
   *
   * @return the member's current credit balance
   */
  public double getCredits() {
    return credit;
  }

  /**
   * Sets the credit balance of the member.
   *
   * @param credit the credit amount to set
   */
  public void setCredits(double credit) {
    this.credit = credit;
  }

  /**
   * Returns the unique member ID.
   *
   * @return the member ID
   */
  public String getMemberId() {
    return memberId;
  }

  /**
   * Returns additional profile details about the member.
   *
   * @return the profile details
   */
  public String getProfileDetails() {
    return profileDetails;
  }

  /**
   * Sets additional profile details about the member.
   *
   * @param profileDetails the profile details to set
   */
  public void setProfileDetails(String profileDetails) {
    this.profileDetails = profileDetails;
  }

  /**
   * Returns whether the member is currently blocked.
   *
   * @return {true} if the member is blocked
   */
  public boolean isBlocked() {
    return isBlocked;
  }

  /**
   * Sets the blocked status of the member.
   *
   * @param isBlocked the blocked status to set
   */
  public void setBlocked(boolean isBlocked) {
    this.isBlocked = isBlocked;
  }

  /**
   * Returns whether the member is marked as deleted.
   *
   * @return {@code true} if the member is marked as deleted; {@code false}
   *         otherwise
   */
  public boolean isDeleted() {
    return isDeleted;
  }

  /**
   * Sets the deleted status of the member.
   *
   * @param isDeleted the deleted status to set
   */
  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }
}
