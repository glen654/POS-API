# POS System Backend

This repository contains the backend API for the Point of Sale (POS) system. The frontend has already been developed, and this backend is designed to integrate seamlessly with it, providing robust, scalable, and secure services to handle the core business logic and data management.

# Introduction

The POS System is designed to manage customer orders, inventory, and sales efficiently. This backend was developed using Jakarta EE, with a focus on maintaining a proper layered architecture, applying best practices in coding, and ensuring secure database interactions.

# Architecture

The application follows a layered architecture, comprising:

* Presentation Layer: Interfaces with the frontend via RESTful APIs.
* Business Logic Layer: Contains the core logic, ensuring that business rules are enforced.
* Data Access Layer: Handles database operations using native SQL queries, abstracted via Data Access Objects (DAOs).

# Tech Stack

* Jakarta EE: Enterprise framework for building robust and scalable applications.
* MySQL: Relational database for storing persistent data.
* AJAX/Fetch: Used for asynchronous communication between the frontend and backend.
* JNDI: Java Naming and Directory Interface for managing database configuration.

# Database Configuration

Database connectivity is managed using JNDI. The persistence.xml or relevant configuration files are set up to ensure secure and efficient access to the MySQL database.

* Database: MySQL
* JNDI Name: java:/comp/env/jdbc/posDB
* Schema: The database schema includes tables for Customers, Orders, Items, and Order Details.

# API Endpoints

The backend exposes a set of RESTful APIs to perform operations like creating orders, updating customer information, and managing inventory. Detailed documentation for these endpoints is provided here.

Example API Endpoints:

### *  Customer Operations

* GET /posApi/customers
* POST /posApi/customers
* PUT /posApi/customers/{customerId}
* DELETE /posApi/customers/{customerId}


### * Item Operation
* GET /posApi/item
* POST /posApi/item
* PUT /posApi/item/{id}
* DELETE /posApi/item/{id}

### * Order Operations

* GET /posApi/orders
* POST /posApi/orders

# Logging

Logging is implemented using Jakarta EE's built-in logging mechanisms. Different logging levels (INFO, DEBUG, ERROR) are applied appropriately to capture application events, aiding in monitoring and troubleshooting.

# Getting Started

To set up the project locally:

Clone the repository:

sh
Copy code
git clone https://github.com/your-username/POS-API.git
cd pos-system-backend
Set up the database:

Ensure MySQL is installed and running.
Import the database schema provided in the sql directory.
Configure JNDI:

Set up the JNDI resource in your Jakarta EE server configuration (e.g.TomCat).
Build and deploy the application:

Use your preferred Jakarta EE compatible server to deploy the application.
Run the application:

Access the application via the frontend connected to the backend.
