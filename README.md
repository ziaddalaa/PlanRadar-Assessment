# PlanRadar E2E Automation Suite

Concise End-to-End (E2E) automated testing suite for PlanRadar's Form Builder, validating form creation and mandatory field enforcement.

## 🛠️ Tech Stack

* **Language:** Java 21
* **Core:** Selenium WebDriver 4.46.0
* **Runner:** TestNG
* **Build:** Maven
* **Pattern:** Page Object Model (POM)

---

## ⚡ Quick Start

### 1. Configuration
Create `src/test/resources/config.properties`:
```properties
baseUrl=https://www.planradar.com
userEmail=your_email@example.com
userPassword=YourPassword123!
formName=E2E Plan Radar Assessment
fieldName=Short Text
```

### 2. Execute Tests


* **Via TestNG XML:** Right-click `testng.xml` $\rightarrow$ **Run**.


---

## 🧪 E2E Test Workflow (`EndToEndFormTest`)

The main E2E test suite (`E2ETest.java`) is decoupled into 3 sequential, dependent steps using TestNG `@Test(dependsOnMethods)`:
1. `test01_createFormWithMandatoryField`: Builds the dynamic form with mandatory short-text rules and list attributes.
2. `test02_linkFormToProject`: Associates the newly created form with the designated project via ID search.
3. `test03_validateTicketMandatoryEnforcement`: Triggers negative validation on blank submission and completes ticket creation.

