# LawFirmManager

**LawFirmManager** is a Java-based legal management system with a graphical user interface (GUI). Designed using the MVC architecture, it enables the registration, viewing, and control of data related to individuals, lawyers, legal cases, hearings, expenses, and courts.

## 📁 Project Structure

```
LawFirmManager/
└── src/
    ├── contracts/        # Domain interfaces
    ├── controllers/      # Application controllers
    ├── domain/           # Core domain entities (Person, Case, etc.)
    ├── dtos/             # Data Transfer Objects
    ├── enumerations/     # Enumerated types (e.g., Case Phases)
    ├── exceptions/       # Custom exception classes
    ├── main/             # Main entry point of the application
    ├── persistence/      # Data serialization logic
    ├── shared/           # Reusable types like CPF, CNPJ, Email
    ├── utils/            # Utility functions
    └── views/            # GUI (Swing-based)
```

## 🚀 Features

* Management of:

  * Individuals (natural and legal persons)
  * Lawyers
  * Legal cases and their phases
  * Courts
  * Case-related expenses
  * Hearings
* Data validation (CPF, CNPJ, Email)
* Data persistence through binary file serialization (`data.bin`)
* Java Swing-based interactive graphical user interface
* Clear separation of responsibilities using DTOs, Controllers, and Views

## 🛠️ Technologies Used

* Java SE 8+
* Java Swing (for the GUI)
* Java Serialization API
* Eclipse IDE (based on presence of `.project` and `.classpath` files)

## 🧪 How to Run

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-username/LawFirmManager.git
   ```

2. **Import into Eclipse**:

   * Go to `File > Import > Existing Projects into Workspace`
   * Select the `LawFirmManager/src` folder

3. **Run the application**:

   * Open the `main/Main.java` file
   * Right-click > `Run As > Java Application`

4. **Data is auto-persisted**:

   * All data is saved to `data.bin` using Java's object serialization.

## 📸 GUI Overview

The application provides separate windows (`Views`) for each main entity (e.g., `LawyerView`, `CaseView`), navigable from a central menu interface (`MenuView`).

## ⚠️ Notes

* Ensure Java is installed on your machine.
* The project uses `.gitignore` to prevent committing compiled `.class` files.

## 📄 License

This project is intended for educational purposes. Add a license section here if applicable (e.g., MIT, GPL).
