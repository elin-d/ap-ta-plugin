// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.ts.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static eu.crewmate.sdk.ts.psi.TsTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import eu.crewmate.sdk.ts.psi.*;

public class TsPropertyImpl extends ASTWrapperPsiElement implements TsProperty {

  public TsPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TsVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TsVisitor) accept((TsVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TsValue getValue() {
    return findChildByClass(TsValue.class);
  }

  @Override
  public String getKey() {
    return TsPsiImplUtil.getKey(this);
  }

}
