// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TcContainer extends PsiElement {

  @NotNull
  List<TcAction> getActionList();

  @NotNull
  List<TcCommentLine> getCommentLineList();

  @NotNull
  List<TcPausedLine> getPausedLineList();

  @NotNull
  List<TcProcedureLine> getProcedureLineList();

  @NotNull
  List<TcUnknown> getUnknownList();

  @NotNull
  List<TcVerificationRule> getVerificationRuleList();

}
