package eu.crewmate.sdk.tc.dom;

import com.intellij.util.xml.DomFileDescription;

public class UIElementFileDescription extends DomFileDescription {
    private static final Class<Element> elementClass = Element.class;
    private static final String rootTag = "element";

    public UIElementFileDescription() {
        super(Element.class, "element", "");
    }
}
