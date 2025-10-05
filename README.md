# ap-ta-plugin

![Build](https://github.com/elin-d/ap-ta-plugin/workflows/Build/badge.svg)

## ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [x] Get familiar with the [template documentation][template].
- [x] Adjust the [pluginGroup](./gradle.properties) and [pluginName](./gradle.properties), as well as the [id](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [x] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [x] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [x] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [x] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.
- [] Configure the [CODECOV_TOKEN](https://docs.codecov.com/docs/quick-start) secret for automated test coverage reports on PRs

<!-- Plugin description -->

A JetBrains PyCharm plugin that provides enhanced IDE support for the Allplan test automation framework


🔍 Smart Code Completion Intelligent: auto-completion for test case syntax and framework-specific keywords Context-aware suggestions based on the testing framework conventions.


✅ Code Verification & Validation Real-time syntax validation for test case files Line markers to indicate test verification points and potential issues Inline error detection and quick fixes.


📝 Language Support Custom lexical analysis for test case files (.tc files) Syntax highlighting tailored for Allplan test automation scripts Proper code formatting and structure recognition.


🛠️ Developer Tools Seamless integration with IntelliJ IDEA's development environment Enhanced navigation and code browsing capabilities Support for refactoring operations on test case files.

<!-- Plugin description end -->

## Installation

- Manually:

  Download the [latest release](https://github.com/elin-d/ap-ta-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
