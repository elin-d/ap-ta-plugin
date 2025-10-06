// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TcPausedLine extends PsiElement {

  @Nullable
  TcAction getAction();

  @Nullable
  TcProcedureLine getProcedureLine();

  @Nullable
  TcUnknown getUnknown();

  @Nullable
  TcVerificationRule getVerificationRule();

}
