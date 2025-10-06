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

public class TcProcedureLineImpl extends ASTWrapperPsiElement implements TcProcedureLine {

  public TcProcedureLineImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TcVisitor visitor) {
    visitor.visitProcedureLine(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TcVisitor) accept((TcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TcOption> getOptionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TcOption.class);
  }

  @Override
  @Nullable
  public TcPrname getPrname() {
    return findChildByClass(TcPrname.class);
  }

}
