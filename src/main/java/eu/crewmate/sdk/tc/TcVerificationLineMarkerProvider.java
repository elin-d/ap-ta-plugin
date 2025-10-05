package eu.crewmate.sdk.tc;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import eu.crewmate.sdk.tc.psi.TcContainer;
import eu.crewmate.sdk.tc.psi.TcVerificationRule;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class TcVerificationLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (element instanceof TcContainer) {
            TcContainer container = (TcContainer) element;
            PsiDirectory basesDir = null;
            if (container.getContainingFile().getParent() != null) {
                basesDir = container.getContainingFile().getParent().findSubdirectory("VP");
            }
            if (basesDir == null) return;
            for (TcVerificationRule vl : container.getVerificationRuleList()) {
                //in case of not image verification
                PsiElement vtype = vl.getVtype();
                PsiElement vkey = vl.getVkey();
                if (vkey == null || vtype == null) continue;
                if (!vtype.getText().toLowerCase().startsWith("image")) continue;
                final PsiElement maskFile = basesDir.findFile(vkey.getText() + ".png");
                if (maskFile != null) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(AllIcons.Diff.GutterCheckBoxIndeterminate)
                                    .setTarget(maskFile)
                                    .setTooltipText("Navigate to verification mask file");
                    result.add(builder.createLineMarkerInfo(vkey.getFirstChild()));
                }
                final List<PsiElement> baseFiles = TcUtil.findVerificationBases(basesDir, vkey.getText());
                if (!baseFiles.isEmpty()) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(AllIcons.Diff.GutterCheckBoxSelected)
                                    .setTargets(baseFiles)
                                    .setTooltipText("Navigate to verification base file");
                    result.add(builder.createLineMarkerInfo(vkey.getFirstChild()));
                }
            }

        }
    }
}
