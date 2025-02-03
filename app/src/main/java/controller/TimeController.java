package controller;

import view.View;

/**
 * The TimeController class.
 */
public class TimeController {
  private int currentDay;
  private View view;

  /**
   * Constructs a TimeController with the initial day set to 0.
   */
  public TimeController() {
    this.currentDay = 0;
    this.view = new View();
  }

  /**
   * Advances the current day by a user-specified number of days.
   * Prompts the user for the number of days to advance.
   */
  public void advanceDays() {
    int days = view.promptForDays();
    if (days > 0) {
      this.currentDay += days;
    }
  }

  /**
   * Retrieves the current day in the application.
   *
   * @return the current day as an integer
   */
  public int getCurrentDay() {
    return this.currentDay;
  }

  /**
   * Resets the current day to a specified start date.
   *
   * @param startDate the day to which the current day should be reset
   */
  public void resetTime(int startDate) {
    this.currentDay = startDate;
  }
}
