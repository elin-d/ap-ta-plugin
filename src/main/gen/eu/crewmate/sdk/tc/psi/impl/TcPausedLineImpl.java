// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static eu.crewmate.sdk.tc.psi.TcTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import eu.crewmate.sdk.tc.psi.*;

public class TcPausedLineImpl extends ASTWrapperPsiElement implements TcPausedLine {

  public TcPausedLineImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TcVisitor visitor) {
    visitor.visitPausedLine(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TcVisitor) accept((TcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TcAction getAction() {
    return findChildByClass(TcAction.class);
  }

  @Override
  @Nullable
  public TcProcedureLine getProcedureLine() {
    return findChildByClass(TcProcedureLine.class);
  }

  @Override
  @Nullable
  public TcUnknown getUnknown() {
    return findChildByClass(TcUnknown.class);
  }

  @Override
  @Nullable
  public TcVerificationRule getVerificationRule() {
    return findChildByClass(TcVerificationRule.class);
  }

}
