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
import com.intellij.psi.PsiReference;

public class TsValueImpl extends ASTWrapperPsiElement implements TsValue {

  public TsValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TsVisitor visitor) {
    visitor.visitValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TsVisitor) accept((TsVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getName() {
    return TsPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return TsPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiReference getReference() {
    return TsPsiImplUtil.getReference(this);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return TsPsiImplUtil.getNameIdentifier(this);
  }

}
