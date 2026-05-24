---
title: "🎯 v0.0.0 - Project Skeleton & CI Setup"
assignees: opencode-qa, Anuj-Patiyal
reviewers: opencode-qa
milestone: v0.0.0
linked_issue: 1
labels: setup, ci-cd, core
---

# 🎯 feat: Project Skeleton & CI Setup (`v0.0.0`)


{{DYNAMIC_METADATA}}


This PR introduces the foundational setup for the **Playwright Java Hybrid Automation Framework**. It establishes the strict CI/CD gates, automated scripts, and the initial Maven scaffolding required before introducing testing dependencies in the next phase.

## 📂 Files Introduced or Modified
```txt
📦 playwright-hybrid-framework/
├── 📄 pom.xml              # Base Maven config enforcing Java 17 (🆕)
├── 📁 .github/workflows/   # main-ci.yml, feature-pr.yml, release-pr.yml (🆕)
├── 📁 scripts/             # pom-validator.sh, milestones.sh, etc. (🆕)
├── 📄 .gitignore           # Standard ignores for Java/Maven (🆕)
└── 📄 README.md            # Project overview and setup guide (🆕)
```
## Key Features Introduced
- ✅ Maven Initialization: Empty dependency tree, strict Java 17 enforcement.

- ✅ CI/CD Pipelines: Complete GitHub Actions architecture.

- ✅ Custom Validation: pom-validator.sh v2.0 with Dark Mode HTML reports.

- ✅ Repository Automation: Feature, Release, and Issue automation scripts.

## 🛠️ How to Verify
1. Run Maven validation locally:

```Bash
./scripts/pom-validator.sh --strict --html
```
2. Verify CI/CD execution: Check the Actions tab to ensure `Main CI Pipeline` passes successfully.

## 🚧 Next Steps
- Merge this PR into **dev**

- Open Release PR: **main ← dev**

- Tag initial release: `v0.0.0`
