// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.ts.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import eu.crewmate.sdk.ts.psi.impl.*;

public interface TsTypes {

  IElementType PROPERTY = new TsElementType("PROPERTY");
  IElementType VALUE = new TsElementType("VALUE");

  IElementType COMMENT = new TsTokenType("COMMENT");
  IElementType CRLF = new TsTokenType("CRLF");
  IElementType KEY = new TsTokenType("KEY");
  IElementType KVALUE = new TsTokenType("KVALUE");
  IElementType SEPARATOR = new TsTokenType("SEPARATOR");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new TsPropertyImpl(node);
      }
      else if (type == VALUE) {
        return new TsValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
