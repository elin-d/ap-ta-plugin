// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static eu.crewmate.sdk.tc.psi.TcTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TcParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return tcFile(b, l + 1);
  }

  /* ********************************************************** */
  // ELEMENT_TYPE key* key? VALUE? option* EOL?
  public static boolean action(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action")) return false;
    if (!nextTokenIs(b, ELEMENT_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELEMENT_TYPE);
    r = r && action_1(b, l + 1);
    r = r && action_2(b, l + 1);
    r = r && action_3(b, l + 1);
    r = r && action_4(b, l + 1);
    r = r && action_5(b, l + 1);
    exit_section_(b, m, ACTION, r);
    return r;
  }

  // key*
  private static boolean action_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!key(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "action_1", c)) break;
    }
    return true;
  }

  // key?
  private static boolean action_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_2")) return false;
    key(b, l + 1);
    return true;
  }

  // VALUE?
  private static boolean action_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_3")) return false;
    consumeToken(b, VALUE);
    return true;
  }

  // option*
  private static boolean action_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!option(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "action_4", c)) break;
    }
    return true;
  }

  // EOL?
  private static boolean action_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_5")) return false;
    consumeToken(b, EOL);
    return true;
  }

  /* ********************************************************** */
  // COMMENT
  public static boolean commentLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commentLine")) return false;
    if (!nextTokenIs(b, COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    exit_section_(b, m, COMMENT_LINE, r);
    return r;
  }

  /* ********************************************************** */
  // (commentLine | pausedLine | line)*
  public static boolean container(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "container")) return false;
    Marker m = enter_section_(b, l, _NONE_, CONTAINER, "<container>");
    while (true) {
      int c = current_position_(b);
      if (!container_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "container", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // commentLine | pausedLine | line
  private static boolean container_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "container_0")) return false;
    boolean r;
    r = commentLine(b, l + 1);
    if (!r) r = pausedLine(b, l + 1);
    if (!r) r = line(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ELEMENT_KEY
  public static boolean key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key")) return false;
    if (!nextTokenIs(b, ELEMENT_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELEMENT_KEY);
    exit_section_(b, m, KEY, r);
    return r;
  }

  /* ********************************************************** */
  // verification_rule | procedure_line | action | unknown | EOL
  static boolean line(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line")) return false;
    boolean r;
    r = verification_rule(b, l + 1);
    if (!r) r = procedure_line(b, l + 1);
    if (!r) r = action(b, l + 1);
    if (!r) r = unknown(b, l + 1);
    if (!r) r = consumeToken(b, EOL);
    return r;
  }

  /* ********************************************************** */
  // STR_OPTION | INT_OPTION | PATH_OPTION
  public static boolean option(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION, "<option>");
    r = consumeToken(b, STR_OPTION);
    if (!r) r = consumeToken(b, INT_OPTION);
    if (!r) r = consumeToken(b, PATH_OPTION);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PAUSE+ line
  public static boolean pausedLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pausedLine")) return false;
    if (!nextTokenIs(b, PAUSE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = pausedLine_0(b, l + 1);
    r = r && line(b, l + 1);
    exit_section_(b, m, PAUSED_LINE, r);
    return r;
  }

  // PAUSE+
  private static boolean pausedLine_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pausedLine_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PAUSE);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, PAUSE)) break;
      if (!empty_element_parsed_guard_(b, "pausedLine_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PR_NAME
  public static boolean prname(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "prname")) return false;
    if (!nextTokenIs(b, PR_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PR_NAME);
    exit_section_(b, m, PRNAME, r);
    return r;
  }

  /* ********************************************************** */
  // PROCEDURE prname? option* EOL?
  public static boolean procedure_line(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_line")) return false;
    if (!nextTokenIs(b, PROCEDURE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PROCEDURE);
    r = r && procedure_line_1(b, l + 1);
    r = r && procedure_line_2(b, l + 1);
    r = r && procedure_line_3(b, l + 1);
    exit_section_(b, m, PROCEDURE_LINE, r);
    return r;
  }

  // prname?
  private static boolean procedure_line_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_line_1")) return false;
    prname(b, l + 1);
    return true;
  }

  // option*
  private static boolean procedure_line_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_line_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!option(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "procedure_line_2", c)) break;
    }
    return true;
  }

  // EOL?
  private static boolean procedure_line_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_line_3")) return false;
    consumeToken(b, EOL);
    return true;
  }

  /* ********************************************************** */
  // container
  static boolean tcFile(PsiBuilder b, int l) {
    return container(b, l + 1);
  }

  /* ********************************************************** */
  // (PYTHON+ EOL*)+
  public static boolean unknown(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unknown")) return false;
    if (!nextTokenIs(b, PYTHON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unknown_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!unknown_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "unknown", c)) break;
    }
    exit_section_(b, m, UNKNOWN, r);
    return r;
  }

  // PYTHON+ EOL*
  private static boolean unknown_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unknown_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unknown_0_0(b, l + 1);
    r = r && unknown_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PYTHON+
  private static boolean unknown_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unknown_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PYTHON);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, PYTHON)) break;
      if (!empty_element_parsed_guard_(b, "unknown_0_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // EOL*
  private static boolean unknown_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unknown_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, EOL)) break;
      if (!empty_element_parsed_guard_(b, "unknown_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // VERIFICATION vtype? option* vkey? option* EOL?
  public static boolean verification_rule(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verification_rule")) return false;
    if (!nextTokenIs(b, VERIFICATION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VERIFICATION);
    r = r && verification_rule_1(b, l + 1);
    r = r && verification_rule_2(b, l + 1);
    r = r && verification_rule_3(b, l + 1);
    r = r && verification_rule_4(b, l + 1);
    r = r && verification_rule_5(b, l + 1);
    exit_section_(b, m, VERIFICATION_RULE, r);
    return r;
  }

  // vtype?
  private static boolean verification_rule_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verification_rule_1")) return false;
    vtype(b, l + 1);
    return true;
  }

  // option*
  private static boolean verification_rule_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verification_rule_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!option(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "verification_rule_2", c)) break;
    }
    return true;
  }

  // vkey?
  private static boolean verification_rule_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verification_rule_3")) return false;
    vkey(b, l + 1);
    return true;
  }

  // option*
  private static boolean verification_rule_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verification_rule_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!option(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "verification_rule_4", c)) break;
    }
    return true;
  }

  // EOL?
  private static boolean verification_rule_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verification_rule_5")) return false;
    consumeToken(b, EOL);
    return true;
  }

  /* ********************************************************** */
  // IMAGE_NAME
  public static boolean vkey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vkey")) return false;
    if (!nextTokenIs(b, IMAGE_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMAGE_NAME);
    exit_section_(b, m, VKEY, r);
    return r;
  }

  /* ********************************************************** */
  // VER_TYPE
  public static boolean vtype(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "vtype")) return false;
    if (!nextTokenIs(b, VER_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VER_TYPE);
    exit_section_(b, m, VTYPE, r);
    return r;
  }

}
