package eu.crewmate.sdk.tc;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import eu.crewmate.sdk.tc.psi.TcTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class TcSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey PAUSE =
            createTextAttributesKey("tc_PAUSE", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey VERIFICATION =
            createTextAttributesKey("tc_VERIFICATION", DefaultLanguageHighlighterColors.STATIC_METHOD);
    public static final TextAttributesKey PROCEDURE =
            createTextAttributesKey("tc_PROCEDURE", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey PR_NAME =
            createTextAttributesKey("tc_PR_NAME", DefaultLanguageHighlighterColors.INSTANCE_METHOD);
    public static final TextAttributesKey ELEMENT_TYPE =
            createTextAttributesKey("tc_TYPE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey ELEMENT_KEY =
            createTextAttributesKey("tc_ELEMENT_KEY", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("tc_VALUE", DefaultLanguageHighlighterColors.INSTANCE_METHOD);
    public static final TextAttributesKey STR_OPTION =
            createTextAttributesKey("tc_STR_OPTION", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey INT_OPTION =
            createTextAttributesKey("tc_INT_OPTION", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey PATH_OPTION =
            createTextAttributesKey("tc_PATH_OPTION", Integer.parseInt(ApplicationInfo.getInstance().getMajorVersion())>2021 ? DefaultLanguageHighlighterColors.HIGHLIGHTED_REFERENCE : DefaultLanguageHighlighterColors.CLASS_REFERENCE);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("tc_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("tc_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] PAUSES = new TextAttributesKey[]{PAUSE};
    private static final TextAttributesKey[] VERIFICATIONS = new TextAttributesKey[]{VERIFICATION};
    private static final TextAttributesKey[] TYPES = new TextAttributesKey[]{ELEMENT_TYPE};
    private static final TextAttributesKey[] PROCEDURES = new TextAttributesKey[]{PROCEDURE};
    private static final TextAttributesKey[] PR_NAMES = new TextAttributesKey[]{PR_NAME};
    private static final TextAttributesKey[] BAD_CHARS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] KEYS = new TextAttributesKey[]{ELEMENT_KEY};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] STR_OPTIONS = new TextAttributesKey[]{STR_OPTION};
    private static final TextAttributesKey[] INT_OPTIONS = new TextAttributesKey[]{INT_OPTION};
    private static final TextAttributesKey[] PATH_OPTIONS = new TextAttributesKey[]{PATH_OPTION};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new TcLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(TcTypes.PAUSE)) {
            return PAUSES;
        }
        if (tokenType.equals(TcTypes.VERIFICATION)) {
            return VERIFICATIONS;
        }
        if (tokenType.equals(TcTypes.PROCEDURE)) {
            return PROCEDURES;
        }
        if (tokenType.equals(TcTypes.PR_NAME)) {
            return PR_NAMES;
        }
        if (tokenType.equals(TcTypes.ELEMENT_TYPE)) {
            return TYPES;
        }
        if (tokenType.equals(TcTypes.ELEMENT_KEY)) {
            return KEYS;
        }
        if (tokenType.equals(TcTypes.VALUE)) {
            return VALUE_KEYS;
        }
        if (tokenType.equals(TcTypes.STR_OPTION)) {
            return STR_OPTIONS;
        }
        //special case for verification type (it's string option)
        if (tokenType.equals(TcTypes.VER_TYPE)) {
            return STR_OPTIONS;
        }
        if (tokenType.equals(TcTypes.INT_OPTION)) {
            return INT_OPTIONS;
        }
        if (tokenType.equals(TcTypes.PATH_OPTION)) {
            return PATH_OPTIONS;
        }
        if (tokenType.equals(TcTypes.COMMENT)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHARS;
        }
        return EMPTY_KEYS;
    }

}