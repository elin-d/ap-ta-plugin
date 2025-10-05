package eu.crewmate.sdk.tc;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import eu.crewmate.sdk.tc.psi.TcTypes;
import com.intellij.psi.TokenType;

%%

%class TcLexer
%implements FlexLexer
%unicode
%ignorecase
%function advance
%type IElementType
%eof{  return;
%eof}

EOL=\r|\n|\r\n
WHITE_SPACE=[\ \t\f]

LINE_COMMENT="#" [^\r\n]* {EOL}?
KEY_CHARACTER=[^;\r\n]
STR=[^;\\\r\n]
PATH=[^;\r\n]
DIGITS = [(\-|\+)?[:digit:]]+((\.|,)(\-|\+)?[[:digit:]])*
NONLETTER=[^[:letter:]\ \t\f;@#\n\r]

SEPARATOR=;


%state WAITING_VALUE1
%state WAITING_VALUE2
%state WAITING_COMMAND
%state OPTIONAL_VALUES
%state PR_COMMAND
%state VER_TYPE
%state VER_OPT
%state VER_VALUE


%%

<YYINITIAL> {LINE_COMMENT}                                  {return TcTypes.COMMENT; }

<YYINITIAL> {WHITE_SPACE}+                                  {return TokenType.WHITE_SPACE; }

<YYINITIAL>  @                                              {return TcTypes.PAUSE; }

<YYINITIAL> "verification"                                  {yybegin(VER_TYPE); return TcTypes.VERIFICATION; }

<YYINITIAL> "\?verification"                                {yybegin(VER_TYPE); return TcTypes.VERIFICATION; }

<YYINITIAL> "!verification"                                 {yybegin(VER_TYPE); return TcTypes.VERIFICATION; }

<YYINITIAL> "procedure"                                     {yybegin(PR_COMMAND); return TcTypes.PROCEDURE; }

<YYINITIAL> menu|dialog|toolbar|frame|statusbar|function    { yybegin(WAITING_VALUE1); return TcTypes.ELEMENT_TYPE; }

<YYINITIAL> [^#@;\ \r\n\t\f]{KEY_CHARACTER}*                {return TcTypes.PYTHON; }

<WAITING_VALUE1> {KEY_CHARACTER}+                           { yybegin(WAITING_VALUE2);  return TcTypes.ELEMENT_KEY; }

<WAITING_VALUE2> {KEY_CHARACTER}+                           { yybegin(WAITING_COMMAND); return TcTypes.ELEMENT_KEY; }

<WAITING_VALUE2> ";;"                                       { yybegin(WAITING_COMMAND); return TokenType.WHITE_SPACE; }

<WAITING_COMMAND> {KEY_CHARACTER}+                          { yybegin(OPTIONAL_VALUES); return TcTypes.VALUE; }

<PR_COMMAND> {KEY_CHARACTER}+                               { yybegin(OPTIONAL_VALUES); return TcTypes.PR_NAME; }

<OPTIONAL_VALUES> {DIGITS}+                                 { return TcTypes.INT_OPTION; }

<OPTIONAL_VALUES> {STR}+                                    { return TcTypes.STR_OPTION; }

<OPTIONAL_VALUES> {PATH}+                                   { return TcTypes.PATH_OPTION; }

<VER_TYPE> image|image-looks-same                           { yybegin(VER_OPT); return TcTypes.VER_TYPE; }

<VER_TYPE> {KEY_CHARACTER}+                                 { yybegin(OPTIONAL_VALUES); return TcTypes.VER_TYPE; }

<VER_VALUE> {KEY_CHARACTER}+                                { yybegin(OPTIONAL_VALUES); return TcTypes.IMAGE_NAME; }

<VER_OPT> "run"                                             { yybegin(VER_VALUE); return TcTypes.STR_OPTION; }

<VER_OPT> {KEY_CHARACTER}+                                  { return TcTypes.STR_OPTION; }

{EOL}                                                       {yybegin(YYINITIAL); return TcTypes.EOL;}

{SEPARATOR}                                                 { return TokenType.WHITE_SPACE;}

[^]                                                         { return TokenType.BAD_CHARACTER; }