package eu.crewmate.sdk.tc;

import com.intellij.lang.Language;

public class TcLanguage extends Language {

    public static final TcLanguage INSTANCE = new TcLanguage();

    private TcLanguage() {
        super("Tc");

    }
}
