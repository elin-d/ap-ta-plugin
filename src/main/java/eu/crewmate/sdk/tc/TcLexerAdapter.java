package eu.crewmate.sdk.tc;

import com.intellij.lexer.FlexAdapter;

public class TcLexerAdapter extends FlexAdapter {

    public TcLexerAdapter() {
        super(new TcLexer(null));
    }

}
