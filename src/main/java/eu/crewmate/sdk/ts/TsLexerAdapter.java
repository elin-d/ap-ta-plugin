package eu.crewmate.sdk.ts;

import com.intellij.lexer.FlexAdapter;

public class TsLexerAdapter extends FlexAdapter {

    public TsLexerAdapter() {
        super(new TsLexer(null));
    }

}
