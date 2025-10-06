// This is a generated file. Not intended for manual editing.
package eu.crewmate.sdk.tc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TcVerificationRule extends PsiElement {

  @NotNull
  List<TcOption> getOptionList();

  @Nullable
  TcVkey getVkey();

  @Nullable
  TcVtype getVtype();

}
