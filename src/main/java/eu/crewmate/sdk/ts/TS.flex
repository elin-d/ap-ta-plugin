package eu.crewmate.sdk.ts;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import eu.crewmate.sdk.ts.psi.TsTypes;
import com.intellij.psi.TokenType;

%%

%class TsLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
VALUE_CHARACTER=[^\n\t\f]
END_OF_LINE_COMMENT=("#")[^\r\n]*
SEPARATOR=[;]
PATH_SEPARATOR=[\\]
KEY_CHARACTER=[^;\n\t\f]

%state WAITING_VALUE

%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return TsTypes.COMMENT; }

<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return TsTypes.KEY; }

<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return TsTypes.SEPARATOR; }

<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

<WAITING_VALUE> {VALUE_CHARACTER}+                          { return TsTypes.KVALUE; }

({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }