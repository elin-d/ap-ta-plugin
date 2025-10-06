// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import eu.crewmate.sdk.tc.psi.impl.*;

public interface TcTypes {

  IElementType ACTION = new TcElementType("ACTION");
  IElementType COMMENT_LINE = new TcElementType("COMMENT_LINE");
  IElementType CONTAINER = new TcElementType("CONTAINER");
  IElementType KEY = new TcElementType("KEY");
  IElementType OPTION = new TcElementType("OPTION");
  IElementType PAUSED_LINE = new TcElementType("PAUSED_LINE");
  IElementType PRNAME = new TcElementType("PRNAME");
  IElementType PROCEDURE_LINE = new TcElementType("PROCEDURE_LINE");
  IElementType UNKNOWN = new TcElementType("UNKNOWN");
  IElementType VERIFICATION_RULE = new TcElementType("VERIFICATION_RULE");
  IElementType VKEY = new TcElementType("VKEY");
  IElementType VTYPE = new TcElementType("VTYPE");

  IElementType COMMENT = new TcTokenType("COMMENT");
  IElementType ELEMENT_KEY = new TcTokenType("ELEMENT_KEY");
  IElementType ELEMENT_TYPE = new TcTokenType("ELEMENT_TYPE");
  IElementType EOL = new TcTokenType("EOL");
  IElementType IMAGE_NAME = new TcTokenType("IMAGE_NAME");
  IElementType INT_OPTION = new TcTokenType("INT_OPTION");
  IElementType PATH_OPTION = new TcTokenType("PATH_OPTION");
  IElementType PAUSE = new TcTokenType("PAUSE");
  IElementType PROCEDURE = new TcTokenType("PROCEDURE");
  IElementType PR_NAME = new TcTokenType("PR_NAME");
  IElementType PYTHON = new TcTokenType("PYTHON");
  IElementType STR_OPTION = new TcTokenType("STR_OPTION");
  IElementType VALUE = new TcTokenType("VALUE");
  IElementType VERIFICATION = new TcTokenType("VERIFICATION");
  IElementType VER_TYPE = new TcTokenType("VER_TYPE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ACTION) {
        return new TcActionImpl(node);
      }
      else if (type == COMMENT_LINE) {
        return new TcCommentLineImpl(node);
      }
      else if (type == CONTAINER) {
        return new TcContainerImpl(node);
      }
      else if (type == KEY) {
        return new TcKeyImpl(node);
      }
      else if (type == OPTION) {
        return new TcOptionImpl(node);
      }
      else if (type == PAUSED_LINE) {
        return new TcPausedLineImpl(node);
      }
      else if (type == PRNAME) {
        return new TcPrnameImpl(node);
      }
      else if (type == PROCEDURE_LINE) {
        return new TcProcedureLineImpl(node);
      }
      else if (type == UNKNOWN) {
        return new TcUnknownImpl(node);
      }
      else if (type == VERIFICATION_RULE) {
        return new TcVerificationRuleImpl(node);
      }
      else if (type == VKEY) {
        return new TcVkeyImpl(node);
      }
      else if (type == VTYPE) {
        return new TcVtypeImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
