package org.immregistries.dqa.hl7util.model;

import org.apache.commons.lang3.StringUtils;

public class ErrorLocation {
  private String segmentId = "";
  private int segmentSequence = 0;
  private int fieldPosition = 0;
  private int fieldRepetition = 0;
  private int componentNumber = 0;
  private int subComponentNumber = 0;

  public String getMessageMapLocator() {
    String messageMapLocator = segmentId;
    messageMapLocator += "[" + (segmentSequence == 0 ? 1 : segmentSequence) + "]-";
    messageMapLocator += fieldPosition == 0 ? 1 : fieldPosition;
    if (fieldRepetition > 1) {
      messageMapLocator += "~" + fieldRepetition;
    }
    if (componentNumber == 0) {
      messageMapLocator += "-1";
    } else {
      messageMapLocator += "-" + componentNumber;
    }
    if (subComponentNumber == 0) {
      messageMapLocator += "-1";
    } else {
      messageMapLocator += "-" + subComponentNumber;
    }

    return messageMapLocator;
  }

  public String getMessageMapLocatorFieldOnly() {
    String messageMapLocator = segmentId;
    messageMapLocator += "[" + (segmentSequence == 0 ? 1 : segmentSequence) + "]-";
    messageMapLocator += fieldPosition == 0 ? 1 : fieldPosition;
    if (fieldRepetition > 1) {
      messageMapLocator += "~" + fieldRepetition;
    }

    return messageMapLocator;
  }

  public boolean hasSegmentId() {
    return !StringUtils.isBlank(segmentId);
  }

  public boolean hasFieldPosition() {
    return fieldPosition > 0;
  }

  public boolean hasComponentNumber() {
    return componentNumber > 0;
  }

  public boolean hasSubComponentNumber() {
    return subComponentNumber > 0;
  }

  public ErrorLocation() {
    // default
  }

  public ErrorLocation(String hl7Reference) {
    if (!StringUtils.isBlank(hl7Reference)) {
      int pos = hl7Reference.indexOf("-");
      if (pos == -1) {
        segmentId = hl7Reference;
        return;
      }
      segmentId = hl7Reference.substring(0, pos);
      if ((pos + 1) <= hl7Reference.length()) {
        hl7Reference = hl7Reference.substring(pos + 1);
        if (hl7Reference.length() > 0) {
          pos = hl7Reference.indexOf(".");
          try {
            if (pos == -1) {
              fieldPosition = Integer.parseInt(hl7Reference);
            } else {
              fieldPosition = Integer.parseInt(hl7Reference.substring(0, pos));
              if ((pos + 1) < hl7Reference.length()) {
                componentNumber = Integer.parseInt(hl7Reference.substring(pos + 1));
              }
            }
          } catch (NumberFormatException nfe) {
            // ignore
          }
        }
      }
    }
  }

  public String getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(String segmentId) {
    this.segmentId = segmentId;
  }

  public int getSegmentSequence() {
    return segmentSequence;
  }

  public void setSegmentSequence(int segmentSequence) {
    this.segmentSequence = segmentSequence;
  }

  public int getFieldPosition() {
    return fieldPosition;
  }

  public void setFieldPosition(int fieldPosition) {
    this.fieldPosition = fieldPosition;
  }

  public int getFieldRepetition() {
    return fieldRepetition;
  }

  public void setFieldRepetition(int fieldRepetition) {
    this.fieldRepetition = fieldRepetition;
  }

  public int getComponentNumber() {
    return componentNumber;
  }

  public void setComponentNumber(int componentNumber) {
    this.componentNumber = componentNumber;
  }

  public int getSubComponentNumber() {
    return subComponentNumber;
  }

  public void setSubComponentNumber(int subComponentNumber) {
    this.subComponentNumber = subComponentNumber;
  }
}
