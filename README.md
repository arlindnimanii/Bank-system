Banking System Application
Overview
This is a simple banking system application implemented in Java. The application allows users to perform various banking operations such as opening accounts, depositing and withdrawing money, transferring funds, and viewing transaction history. The application also tracks and displays the total transaction fees collected by the bank.

Features
Open a new account
Display all account details
Search for an account by account number
Deposit money into an account
Withdraw money from an account
Transfer funds between accounts
View the list of transactions for any account
Check the total transaction fees collected by the bank
Requirements
Java Development Kit (JDK) 8 or higher
MySQL database
DB Browser for SQLite
Setup Instructions
Clone the Repository:

sh
Copy code
git clone https://github.com/your_username/your_repository.git
cd your_repository
Database Setup:

Install MySQL and create a new database.
Use the provided database/database_dump.sql file to set up your database schema and initial data.
Open MySQL Workbench and connect to your MySQL server.
Import the database_dump.sql file into your MySQL database.

Connect Database to DB Browser:

Open DB Browser for SQLite.
Open your MySQL database using the connection settings.
Ensure the database is properly connected to IntelliJ IDEA using the JDBC driver.
Configure the Project:

Update the database connection settings in the application.properties or application.yaml file located in the src/main/resources directory.
Run the Application:

Open the project in IntelliJ IDEA.
Build and run the project.
