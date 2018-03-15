package org.immregistries.dqa.vxu.parse;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.hl7util.model.ErrorLocation;
import org.immregistries.dqa.hl7util.model.MetaFieldInfo;
import org.immregistries.dqa.hl7util.parser.HL7MessageMap;
import org.immregistries.dqa.vxu.VxuField;

public enum MetaParser {
  INSTANCE;

  public MetaFieldInfo mapAndSet(VxuField vxuField, HL7MessageMap map) {
    return mapAndSet(0, vxuField, map);
  }

  public MetaFieldInfo mapAndSet(int absoluteSegmentIndex, VxuField vxuField, HL7MessageMap map) {
    ErrorLocation errorLocation = new ErrorLocation(vxuField.getHl7Field());
    String value = mapValue(absoluteSegmentIndex, map, errorLocation);

    MetaFieldInfo meta = new MetaFieldInfo();
    meta.setVxuField(vxuField);
    meta.setValue(value);
    meta.setErrorLocation(errorLocation);
    return meta;
  }

  public MetaFieldInfo mapAndSetCodedValue(VxuField vxuField, HL7MessageMap map,
      String[] codeTableNames) {
    return mapAndSetCodedValue(0, vxuField, map, codeTableNames);
  }

  public MetaFieldInfo mapAndSetCodedValue(int absoluteSegmentIndex, VxuField vxuField,
      HL7MessageMap map, String[] codeTableNames) {
    ErrorLocation errorLocation = new ErrorLocation(vxuField.getHl7Field());
    errorLocation.setComponentNumber(1);
    String value = mapValue(absoluteSegmentIndex, map, errorLocation);
    errorLocation.setComponentNumber(3);
    String tableName = mapValue(absoluteSegmentIndex, map, errorLocation);
    errorLocation.setComponentNumber(4);
    String valueAlt = mapValue(absoluteSegmentIndex, map, errorLocation);
    errorLocation.setComponentNumber(6);
    String tableNameAlt = mapValue(absoluteSegmentIndex, map, errorLocation);
    errorLocation.setComponentNumber(1);

    boolean valueFound = false;
    for (String s : codeTableNames) {
      if (s.equalsIgnoreCase(tableName)) {
        valueFound = true;
      }
    }
    if (!valueFound) {
      for (String s : codeTableNames) {
        if (s.equalsIgnoreCase(tableNameAlt)) {
          valueFound = true;
        }
      }
      if (valueFound) {
        value = valueAlt;
        errorLocation.setComponentNumber(4);
      }
    }
    if (valueFound) {
      MetaFieldInfo meta = new MetaFieldInfo();
      meta.setVxuField(vxuField);
      meta.setValue(value);
      meta.setErrorLocation(errorLocation);
      return meta;
    }
    return null;
  }

  public String mapValue(int absoluteSegmentIndex, HL7MessageMap map, ErrorLocation errorLocation) {
    String value;
    if (absoluteSegmentIndex > 1) {
      value = map.getAtIndex(errorLocation.getMessageMapLocator(), absoluteSegmentIndex);
    } else {
      value = map.get(errorLocation.getMessageMapLocator());
    }
    return value;
  }

  public MetaFieldInfo mapAndSet(VxuField vxuField, HL7MessageMap map, String selectHL7Ref,
      String selectValue) {
    return mapAndSet(0, vxuField, map, selectHL7Ref, selectValue);
  }

  public MetaFieldInfo mapAndSet(int absoluteSegmentIndex, VxuField vxuField, HL7MessageMap map,
      String selectHL7Ref, String selectValue) {
    {
      ErrorLocation errorLocation = new ErrorLocation(selectHL7Ref);
      selectHL7Ref = errorLocation.getMessageMapLocator();
    }
    int fieldRep = map.findFieldRepWithValue(selectValue, selectHL7Ref, 1);

    if (fieldRep > 0) {
      ErrorLocation errorLocation = new ErrorLocation(vxuField.getHl7Field());
      errorLocation.setFieldRepetition(fieldRep);
      MetaFieldInfo meta = createMetaField(absoluteSegmentIndex, vxuField, map, errorLocation);
      return meta;
    }
    return null;
  }


  public List<MetaFieldInfo> mapAndSetAll(VxuField vxuField, HL7MessageMap map) {
    return mapAndSetAll(0, vxuField, map);
  }

  public List<MetaFieldInfo> mapAndSetAll(int absoluteSegmentIndex, VxuField vxuField,
      HL7MessageMap map) {
    int fieldCount = 0;
    {
      ErrorLocation errorLocation = new ErrorLocation(vxuField.getHl7Field());
      errorLocation.setComponentNumber(1);
      fieldCount = map.getFieldRepCountFor(errorLocation.getMessageMapLocatorFieldOnly());
    }

    List<MetaFieldInfo> metaFieldInfoList = new ArrayList<>();
    for (int i = 1; i <= fieldCount; i++) {
      ErrorLocation errorLocation = new ErrorLocation(vxuField.getHl7Field());
      errorLocation.setFieldRepetition(i);
      metaFieldInfoList.add(createMetaField(absoluteSegmentIndex, vxuField, map, errorLocation));
    }
    return metaFieldInfoList;
  }

  private MetaFieldInfo createMetaField(int absoluteSegmentIndex, VxuField vxuField,
      HL7MessageMap map, ErrorLocation errorLocation) {
    String value = map.get(errorLocation.getMessageMapLocator());
    MetaFieldInfo meta = new MetaFieldInfo();
    meta.setVxuField(vxuField);
    meta.setValue(value);
    meta.setErrorLocation(errorLocation);
    return meta;
  }
}
