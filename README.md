# 🚀 Playwright Java Hybrid Framework 
**Project Skeleton & CI/CD Setup (v0.0.0)**

![Main CI Pipeline](https://github.com/opencode-qa/playwright-hybrid-framework/actions/workflows/main-ci.yml/badge.svg)
![Feature PR Check](https://github.com/opencode-qa/playwright-hybrid-framework/actions/workflows/feature-pr.yml/badge.svg)

> An enterprise-grade, highly scalable automation framework foundation using Java, Playwright, TestNG, and robust CI/CD governance.

---

## 📚 Table of Contents
1. [Project Overview](#project-overview)
2. [Technical Architecture](#technical-architecture)
3. [Branching Strategy](#branching-strategy)
4. [Versioning Scheme](#versioning-scheme)
5. [Initial Setup](#initial-setup)
6. [Repository Automation](#repository-automation)
7. [Development Workflow](#development-workflow)
8. [Future Roadmap](#future-roadmap)
9. [Contributing](#contributing)
10. [Author](#author)
11. [License](#license)

---

## 📌 Project Overview
This project establishes the strict foundational architecture for a modern UI/API automation framework. At `v0.0.0`, the primary focus is not on testing dependencies, but on **repository governance, CI/CD pipelines, and automated script management.**

### 🎯 Goals of v0.0.0:
- Establish the Maven `pom.xml` enforcing a minimum of **Java 17 / 21**.
- Implement strict GitHub Actions pipelines (`main-ci.yml`, `feature-pr.yml`).
- Deploy a custom, milestone-gated POM Validator (`pom-validator.sh` v2.0) with HTML reporting.
- Automate repository operations (PR creation, version bumping, and issue linking) using GitHub CLI (`gh`).

---

## 🧱 Technical Architecture

### 📁 Folder Structure
```txt
playwright-hybrid-framework/
├── .github/
│   ├── features/           # Feature PR templates
│   ├── issues/             # Automated issue templates
│   ├── releases/           # Release PR templates
│   └── workflows/          # CI/CD Pipelines (main, feature, release)
├── scripts/                # Bash automation engine
│   ├── feature-pr.sh       # Automated feature PR creation
│   ├── release-pr.sh       # Version bumping & release orchestration
│   ├── issues.sh           # Batch issue generation
│   ├── milestones.sh       # Roadmap synchronization
│   └── pom-validator.sh    # Custom Maven dependency/plugin gatekeeper
├── src/                    
│   ├── main/java/          # Core framework utilities (future)
│   └── test/java/          # Playwright test execution (future)
├── pom.xml                 # Maven configuration (baseline)
└── README.md               # Documentation
```
## 🧩 High-Level Component Diagram
```marmaid
graph TD
    A[Developer] -->|git push| B(feature/* branch)
    B --> C{feature-pr.sh}
    C -->|creates PR| D[GitHub PR]
    D --> E[feature-pr.yml CI]
    E -->|build + test| F{Pass?}
    F -->|Yes| G[Merge to dev]
    F -->|No| H[Fix & Re-push]
    G --> I[main-ci.yml runs on dev]
    I -->|quality + security checks| J{Pass?}
    J -->|Yes| K[Ready for release]
    K --> L[release-pr.yml workflow]
    L --> M[Create release PR to main]
    M -->|auto-merge| N[Tag & GitHub Release]
    N --> O[Update dev to next SNAPSHOT]
    
    subgraph "Automation Scripts"
        C
        S[pom-validator.sh]
        T[issues.sh / milestones.sh]
    end
    
    subgraph "GitHub Actions"
        E
        I
        L
    end```

## 🌿 Branching Strategy
We follow a strict Git Flow governed by GitHub Branch Protection rules:
```marmaid
gitGraph
   commit id: "Initial commit"
   branch dev
   commit id: "v0.0.0: Project Skeleton"
   branch feature/v0.1.0-setup
   commit id: "Add Playwright Core & TestNG"
   checkout dev
   merge feature/v0.1.0-setup tag: "merged feature"
   checkout main
   merge dev tag: "v0.0.0"
```
- `main` – Protected production-ready code. Commits only via Automated Release PRs.

`dev` – Protected integration branch. Commits only via passing Feature PRs.

`feature/*` – Active development branches.

## 🧮 Versioning Scheme
We strictly adhere to Semantic Versioning (SemVer) , fully automated via mvn versions:set during the release pipeline.
```marmaid
flowchart LR
    A[Commit messages] --> B{Contains<br/>BREAKING CHANGE?}
    B -->|Yes| C[Major +1]
    B -->|No| D{Contains feat:?}
    D -->|Yes| E[Minor +1, patch=0]
    D -->|No| F[Patch +1]
    C --> G[New version MAJOR.MINOR.PATCH]
    E --> G
    F --> G
    G --> H[mvn versions:set]
```
**Current Baseline:** `v0.0.0` 

## ⚙️ Initial Setup
### ✅ Prerequisites

- Java JDK: `17 or 21`

- Maven: `3.8+`

- Git: `2.30+`

GitHub CLI (gh) – authenticated locally for script execution.

### 💻 Installation

# Clone the repository
```bash
git clone https://github.com/your-username/playwright-hybrid-framework.git
cd playwright-hybrid-framework
```
# Authenticate GitHub CLI (Required for automation scripts)
```bash
gh auth login
```
# Run local POM validation
```bash
./scripts/pom-validator.sh --strict --html
```

## 🤖 Repository Automation
This framework utilizes a custom bash scripting engine to automate tedious repository management tasks:
| Script             | Purpose                                                                                     | Execution                       |
| ------------------ | ------------------------------------------------------------------------------------------- | ------------------------------- |
| `pom-validator.sh` | Validates dependencies against the current milestone map. Generates Dark-Mode HTML reports. | Local & CI (`main-ci.yml`)      |
| `feature-pr.sh`    | Creates/updates Feature PRs, assigns reviewers, and links issues automatically.             | Local (Terminal)                |
| `release-pr.sh`    | Bumps SemVer, generates release notes, and auto-merges snapshot updates to `dev`.           | GitHub Actions (Manual Trigger) |
| `milestones.sh`    | Synchronizes `milestones.json` with GitHub to track project health.                         | Local (Terminal)                |
| `issues.sh`        | Batch creates GitHub issues from Markdown templates.                                        | Local (Terminal)                |

## 🔁 Development Workflow
```mermaid
sequenceDiagram
    participant Dev as Developer
    participant Git as GitHub
    participant Actions as GitHub Actions
    participant Scripts as Automation Scripts

    Dev->>Git: git checkout -b feature/xyz
    Dev->>Git: git commit -m "feat: ..."
    Dev->>Git: git push origin feature/xyz
    Dev->>Scripts: ./scripts/feature-pr.sh
    Scripts->>Git: Create/Update PR
    Git->>Actions: Trigger feature-pr.yml
    Actions->>Actions: mvn clean verify
    Actions-->>Git: Status check
    Git-->>Dev: PR ready for review
    Dev->>Git: Approve & merge (to dev)
    Git->>Actions: Trigger main-ci.yml on dev
    Actions-->>Git: Validation passes
    Note over Git: Release workflow can be manually triggered
    ```
## 🛣️ Future Roadmap

| Version  | Milestone Definition                                        | Status    |
| -------- | ----------------------------------------------------------- | --------- |
| `v0.0.0` | Project Skeleton & CI Setup (Baseline)                      | ✅ Done    |
| `v0.1.0` | Playwright Core & First Test (TC_001)                       | 🚧 Next   |
| `v0.2.0` | Logging (Log4j2) & Config Management                        | ⏳ Planned |
| `v0.3.0` | Page Object Model Architecture                              | ⏳ Planned |
| `v0.4.0` | Data-Driven Framework Setup (Excel/JSON)                    | ⏳ Planned |
| `v0.5.0` | Advanced Playwright Features (Network Interception/Mocking) | ⏳ Planned |
| `v0.6.0` | Visual Regression Testing                                   | ⏳ Planned |
| `v0.7.0` | Cross-Browser & Parallel Execution                          | ⏳ Planned |
| `v0.8.0` | TestNG Listeners & Retry Logic                              | ⏳ Planned |
| `v0.9.0` | Allure Reporting & Dashboard Integration                    | ⏳ Planned |
| `v1.0.0` | First Stable Master Release                                 | ⏳ Planned |

## 🤝 Contributing
```bash
# Fork the repository
# Create feature branch
git checkout -b feature/your-feature

# Commit changes
git commit -am "Add your feature"

# Push to origin
git push origin feature/your-feature

# Execute PR script to generate automated documentation
./scripts/feature-pr.sh
```

## 👨‍💻 Author
**ANUJ KUMAR** | 🏅 QA Lead & AI-Assisted Testing Specialist
📧 Email: **anujpatiyal@live.in**
🔗 [LinkedIn Profile]()

📜 License
Distributed under the MIT License.

“First, solve the problem. Then, write the code.” – John Johnson

This framework adheres to this principle by prioritizing governance, CI/CD, and automation architecture before a single test is written.
