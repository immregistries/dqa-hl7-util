package org.immregistries.mqe.core.util;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;

public enum DateUtility {
  INSTANCE;
  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DateUtility.class);
  //example complete date time: 20150624073733.994-0500
  private final DateTimeFormatter timeZoneFormat1 = DateTimeFormat.forPattern("yyyyMMddHHmmss.SSSSZ");
  private final DateTimeFormatter timeZoneFormat2 = DateTimeFormat.forPattern("yyyyMMddHHmmssZ");
  private final DateTimeFormatter dateTimeFormat1 = DateTimeFormat.forPattern("yyyyMMddHHmmss");
  private final DateTimeFormatter dateTimeFormat2 = DateTimeFormat.forPattern("yyyyMMddHHmm");
  private final DateTimeFormatter dateOnlyFormat = DateTimeFormat.forPattern("yyyyMMdd");
  private final DateTimeFormatter dateTimeFormatSeconds = DateTimeFormat.forPattern("yyyyMMddHHmmss.SSSS");
  private final DateTimeFormatter[] DATE_FORMATS = {timeZoneFormat2, timeZoneFormat1,
      dateTimeFormat1, dateTimeFormat2, dateOnlyFormat, dateTimeFormatSeconds};

  public Date parseDate(String dateString) {

    DateTime dt = parseDateTime(dateString);

    if (dt != null) {
      return dt.toDate();
    }

    return null;
  }

  /**
   * This assumes a date format beginning with yyyyMMdd
   *
   * @param dateTimeStringOne a string with a date in it
   * @param dateTimeStringTwo another string with a date in it
   * @return
   */
  public boolean isDateOneBeforeTwoIgnoringTimeAndZone(String dateTimeStringOne, String dateTimeStringTwo) {
      try {
        String dateOnlyStringOne = dateTimeStringOne.substring(0,8);
        DateTime one = DateTime.parse(dateOnlyStringOne, dateOnlyFormat);
        String dateOnlyStringTwo = dateTimeStringTwo.substring(0,8);
        DateTime two = DateTime.parse(dateOnlyStringTwo, dateOnlyFormat);
        return two.isAfter(one);
      } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
        logger.info("these strings aren't eligible for a date compare: " + dateTimeStringOne + " vs " + dateTimeStringTwo);
      }
      return false;
  }

  public boolean hasTimezone(String dateString) {
    try {
      DateTime dt = DateTime.parse(dateString, timeZoneFormat1);
      return true;
    } catch (IllegalArgumentException iae) {
      try {
        DateTime dt = DateTime.parse(dateString, timeZoneFormat2);
        return true;
      } catch (IllegalArgumentException iae2) {
        return false;
      }
    }
  }

  public DateTime parseDateTime(String dateString) {

    if (StringUtils.isBlank(dateString)) {
      return null;
    }

    //This makes the class more flexible. It starts with the most likely format, and ends with the least.
    for (DateTimeFormatter dateFormatter : DATE_FORMATS) {
      try {
        return parseDateTime(dateString, dateFormatter);
      } catch (IllegalArgumentException iae) {
        continue;//try the next format.
      }
    }

    return null;
  }

  private DateTime parseDateTime(String dateString, DateTimeFormatter dtf) {
    return DateTime.parse(dateString, dtf);
  }

  /**
   * this is for testing to know what the string ought to look like.
   */
  protected String toFullNistString(DateTime in) {
    return in.toString(timeZoneFormat1);
  }

  //	This puts a dateTime object to the MQE's expected String format.
  public String toString(DateTime input) {
    return input.toString(timeZoneFormat2);
  }

  public String toDateString(DateTime input) {
    return input.toString(dateOnlyFormat);
  }
  //	This puts a dateTime object to the MQE's expected String format.
  public String toTzString(DateTime input) {
    return input.toString(timeZoneFormat2);
  }

  public String toString(Date input) {
    return new DateTime(input).toString(dateTimeFormat2);
  }

  public boolean isDate(String dateString) {
    return parseDate(dateString) != null;
  }

  public boolean isOutsideOfRange(String isThis, String beforeThis, String orAfterThis) {
    logger.debug(
        "dates to parse for evaluating isOutsideOfRange: isThis[" + isThis + "] beforeThis["
            + beforeThis + "] orAFterThis[" + orAfterThis + "]");

    Date isThisDate = parseDate(isThis);
    Date beforeThisDate = parseDate(beforeThis);
    Date orAfterThisDate = parseDate(orAfterThis);

    return isDateOutsideOfRange(isThisDate, beforeThisDate, orAfterThisDate);
  }

  /**
   * This evaluates whether the given date is within the second and third dates privided. a "true"
   * answer means the given date is outside of the range.
   */
  public boolean isDateOutsideOfRange(Date isThis, Date beforeThis, Date orAfterThis) {
    if (isThis == null || (beforeThis == null && orAfterThis == null)) {
      logger.debug("One of these is not a date: isThis[" + isThis + "] beforeThis[" + beforeThis
          + "] orAFterThis[" + orAfterThis + "]");
      return false;
    }

    if (isAfterDate(isThis, orAfterThis)) {
      logger.debug("date " + isThis + " is AFTER end of range:" + orAfterThis);
      return true;
    } else if (isBeforeDate(isThis, beforeThis)) {
      logger.debug("date " + isThis + " is BEFORE beginning of range:" + beforeThis);
      return true;
    }
    logger.debug("date " + isThis + " is within range " + beforeThis + " to " + orAfterThis);
    return false;
  }

  /**
   * This effectively truncates the time part of the date sent in and compares the Calendar Date
   * itself. So in order to be After, it has to be a different date altogether.
   *
   * @param isThisDate the date that should be after the other date
   * @param afterThis the date that should be before.
   */
  public boolean isAfterDate(Date isThisDate, Date afterThis) {
    //NOTE:  You can't just call the isBeforeDate method and invert the answer.
    //because of how it treats equals.  IF it's equal, it's not after, and it's not before.
    //So if you called isBefore with an equal date, it would say FALSE.  if you expected
    //to be able to invert that to tell if it's after, it would say TRUE and you would say
    //YES, it's after the date, even though its equal.

    if (isThisDate == null || afterThis == null) {
      return false;
    }
    LocalDate thisIs = new LocalDate(isThisDate);
    LocalDate isItAfterThis = new LocalDate(afterThis);

    return thisIs.isAfter(isItAfterThis);
  }

  /**
   * This effectively truncates the time part of the date sent in and compares the Calendar Date
   * itself. So in order to be before, it has to be a different date altogether.
   *
   * @param isThis the date that should be before the other date
   * @param beforeThis the date that should be after.
   */
  public boolean isBeforeDate(Date isThis, Date beforeThis) {
    if (isThis == null || beforeThis == null) {
      return false;
    }

    LocalDate thisDt = new LocalDate(isThis);
    LocalDate boundaryDt = new LocalDate(beforeThis);

    return thisDt.isBefore(boundaryDt);
  }
  
  public boolean isNotSameDate(Date isThis, Date equalThis) {
    if (isThis == null || equalThis == null) {
        return false;
      }

      LocalDate thisDt = new LocalDate(isThis);
      LocalDate boundaryDt = new LocalDate(equalThis);

      return !thisDt.isEqual(boundaryDt);	  
  }

  public int getYearsBetween(Date start, Date end) {
    if (start == null || end == null) {
      return -1;
    }

    DateTime startDt = new DateTime(start);
    DateTime endDt = new DateTime(end);

    return getYearsBetween(startDt, endDt);
  }

  public int monthsBetween(Date dateOne, Date dateTwo) {
    LocalDate d1 = new LocalDate(dateOne);
    LocalDate d2 = new LocalDate(dateTwo);

    int months = Math.abs(Months.monthsBetween(d1, d2).getMonths());

    return months;
  }

  public boolean isAdult(Date birthDate) {
    return this.getAge(birthDate) >= 18;
  }

  public int getAge(Date birthDate) {
    DateTime bDateTime = new DateTime(birthDate);
    return getAge(bDateTime);
  }

  public int getAge(DateTime bd) {
    return getYearsBetween(bd, new DateTime());
  }

  public int getYearsBetween(DateTime start, DateTime end) {
    if (start == null || end == null) {
      return -1;
    }
    return Years.yearsBetween(start, end).getYears();
  }

}
