# Bus Station Web Application

This repository contains a web application for managing records within a bus station environment. The application is developed using the Spring Boot, React, and Bootstrap frameworks.

## Features

- Management of entities:
  - Carrier (ID, Name, Address, Tax ID)
  - Line (ID, Number of Seats, Ticket Price, Departure Time, Destination, Carrier)

- The implemented REST API provides the following functionalities:
  - Fetching all carriers
  - Adding a new carrier
  - Fetching all lines (paginated)
  - Fetching lines by ID
  - Adding a new line
  - Modifying an existing line
  - Deleting an existing line

- Validation on the API level includes:
  - Number of seats cannot be a negative value
  - Destination must not be an empty string

- Additional features include:
  - Adding a new line via a form
  - Modifying a line on a separate page
  - Searching for lines based on destination, carrier, and price
  - Paginated data display
  - Ticket reservation for a specific line

## Installation

1. Clone this repository to your machine.
2. Run the Spring Boot backend application.
3. Run the React frontend application.
