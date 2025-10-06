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

public class TcContainerImpl extends ASTWrapperPsiElement implements TcContainer {

  public TcContainerImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TcVisitor visitor) {
    visitor.visitContainer(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TcVisitor) accept((TcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TcAction> getActionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TcAction.class);
  }

  @Override
  @NotNull
  public List<TcCommentLine> getCommentLineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TcCommentLine.class);
  }

  @Override
  @NotNull
  public List<TcPausedLine> getPausedLineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TcPausedLine.class);
  }

  @Override
  @NotNull
  public List<TcProcedureLine> getProcedureLineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TcProcedureLine.class);
  }

  @Override
  @NotNull
  public List<TcUnknown> getUnknownList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TcUnknown.class);
  }

  @Override
  @NotNull
  public List<TcVerificationRule> getVerificationRuleList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TcVerificationRule.class);
  }

}
