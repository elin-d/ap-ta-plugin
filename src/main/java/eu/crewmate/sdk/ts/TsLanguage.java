package eu.crewmate.sdk.ts;

import com.intellij.lang.Language;

public class TsLanguage extends Language {

    public static final TsLanguage INSTANCE = new TsLanguage();

    private TsLanguage() {
        super("Ts");

    }
}