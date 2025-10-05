package eu.crewmate.sdk.tc.dom;

import com.intellij.util.xml.DomFileDescription;

public class RootUIMapFileDescription extends DomFileDescription {

    private static final Class<Guimap> elementClass = Guimap.class;
    private static final String rootTag = "guimap";

    public RootUIMapFileDescription() {
        super(elementClass, rootTag, "");
    }
}
