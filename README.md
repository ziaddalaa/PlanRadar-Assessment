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

1. **Auth:** Handles cookies, logs in, and opens the **Forms & Lists** module.
2. **Build:** Creates a form, adds a **Short Text** field, and enables the **Mandatory** setting.
3. **Validate:** Opens **Ticket Preview** and asserts mandatory indicator (`* Short Text`).
4. **Persist:** Saves the form, verifies the confirmation toast, and exits safely.

