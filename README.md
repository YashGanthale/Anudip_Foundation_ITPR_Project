# 🏋️‍♂️ Gym Member Management System

## 📘 Overview
The **Gym Member Management System** is a command-line Java application connected to a MySQL database using **JDBC**.  
It allows gym administrators to manage member details such as membership plan, joining date, and contact information efficiently.  
The system demonstrates the use of **CRUD operations** (Create, Read, Update, Delete) and JDBC connectivity between Java and MySQL.

---

## 🎯 Features
- ➕ Add new gym member records  
- 📋 Display all registered members  
- ✏️ Update existing member details  
- ❌ Delete a member record  
- 💾 Fully integrated with MySQL Database  

---

## 🧠 Technologies Used
- **Programming Language:** Java (JDK 17 or above)  
- **Database:** MySQL  
- **Connector:** MySQL Connector/J (JDBC Driver)  
- **IDE/Editor:** Visual Studio Code or IntelliJ IDEA  

---

## ⚙️ Database Setup

1. Open **MySQL Workbench** and run the following SQL commands:

   ```sql
   CREATE DATABASE gymdb;
   USE gymdb;

   CREATE TABLE members (
       id INT PRIMARY KEY,
       name VARCHAR(50),
       plan VARCHAR(30),
       join_date DATE,
       contact VARCHAR(15)
   );
