// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static eu.crewmate.sdk.tc.psi.TcTypes.*;
import eu.crewmate.sdk.tc.psi.*;
import com.intellij.psi.PsiReference;

public class TcKeyImpl extends TcKeyNamedElementImpl implements TcKey {

  public TcKeyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TcVisitor visitor) {
    visitor.visitKey(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TcVisitor) accept((TcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getName() {
    return TcPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return TcPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiReference getReference() {
    return TcPsiImplUtil.getReference(this);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return TcPsiImplUtil.getNameIdentifier(this);
  }

}
