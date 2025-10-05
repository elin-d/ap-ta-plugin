package eu.crewmate.sdk.tc.dom;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.GenericAttributeValue;

import java.util.List;

public interface Element extends com.intellij.util.xml.DomElement {
    List<Element> getElements();
    @Attribute("name")
    GenericAttributeValue<String> getName();
    @Attribute("xfile")
    GenericAttributeValue<String> getXfile();
    @Attribute("xref")
    GenericAttributeValue<String> getXref();
    @Attribute("xfile_deep")
    GenericAttributeValue<String> getXdeep();
}