package eu.crewmate.sdk.tc.dom;

import java.util.List;

public interface Guimap extends com.intellij.util.xml.DomElement {
    List<Element> getElements();
}