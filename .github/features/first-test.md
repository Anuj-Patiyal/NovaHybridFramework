---
title: "✅ v0.1.0 - Playwright Core & First Test Implementation"
assignees: opencode-qa
reviewers: Anuj-Patiyal
milestone: v0.1.0
linked_issue: 2
labels: enhancement, testing, playwright
---

# 🎯 Feature: Playwright Core & First Test (`v0.1.0`)

{{DYNAMIC_METADATA}}

## 📌 Summary
This feature introduces the **Playwright** and **TestNG** dependencies, along with the first real test case (`TC_001`) that automates the **Text Box** form on [DemoQA](https://demoqa.com). It moves the framework from a skeleton to a functional testing foundation.

## 📂 Key Changes

### 1. Maven Dependencies Added

```xml
<!-- Playwright -->
<dependency>
    <groupId>com.microsoft.playwright</groupId>
    <artifactId>playwright</artifactId>
    <version>1.59.0</version>
</dependency>

<!-- TestNG -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.12.0</version>
</dependency>
```

### 2. First Test Implementation

**Test Class:** `tests.TC_001`

#### Scenario:
- Launch Chrome browser (headed mode)
- Navigate to DemoQA
- Click Elements → Text Box
- Fill form (Name, Email, Current Address, Permanent Address)
- Submit and verify output appears

#### Assertions:
- TestNG `Assert.assertTrue` on output visibility

### 3. Test Execution Ready

```bash
mvn clean test
```

Surefire plugin configured (reports in `target/surefire-reports/`)

## 🛠️ How to Verify

```bash
# Run the test locally
mvn clean test

# Validate POM (milestone-gated)
./scripts/pom-validator.sh --strict --html
```

## ✅ Quality Gates
- Playwright version ≥ 1.50.0
- TestNG version ≥ 7.0.0
- Java 21 enforced
- Test passes on local execution

## 🚧 Next Steps (v0.2.0)
- Integrate Log4j2 for structured logging
- Add configuration management (properties / YAML)
- Implement BaseTest with reusable setup/teardown

## 🔗 Related Artifacts
- Issue: #2 – "Implement first Playwright test"
- Milestone: v0.1.0
