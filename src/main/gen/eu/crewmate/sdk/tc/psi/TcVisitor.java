// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class TcVisitor extends PsiElementVisitor {

  public void visitAction(@NotNull TcAction o) {
    visitPsiElement(o);
  }

  public void visitCommentLine(@NotNull TcCommentLine o) {
    visitPsiElement(o);
  }

  public void visitContainer(@NotNull TcContainer o) {
    visitPsiElement(o);
  }

  public void visitKey(@NotNull TcKey o) {
    visitKeyNamedElement(o);
  }

  public void visitOption(@NotNull TcOption o) {
    visitPsiElement(o);
  }

  public void visitPausedLine(@NotNull TcPausedLine o) {
    visitPsiElement(o);
  }

  public void visitPrname(@NotNull TcPrname o) {
    visitKeyNamedElement(o);
  }

  public void visitProcedureLine(@NotNull TcProcedureLine o) {
    visitPsiElement(o);
  }

  public void visitUnknown(@NotNull TcUnknown o) {
    visitInjectionHost(o);
  }

  public void visitVerificationRule(@NotNull TcVerificationRule o) {
    visitPsiElement(o);
  }

  public void visitVkey(@NotNull TcVkey o) {
    visitPsiElement(o);
  }

  public void visitVtype(@NotNull TcVtype o) {
    visitPsiElement(o);
  }

  public void visitInjectionHost(@NotNull TcInjectionHost o) {
    visitPsiElement(o);
  }

  public void visitKeyNamedElement(@NotNull TcKeyNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
