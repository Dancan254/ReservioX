# Reserviox

Reserviox is a Java-based application designed to streamline the process of advertising and booking services. Built with Spring Boot and Maven, this project aims to provide a robust platform for companies to post ads and for clients to find and book these services efficiently.

## Features

- **User Authentication**: Secure signup and login functionality for clients and companies.
- **Ad Posting**: Companies can post ads about their services, including details like service name, description, price, and an image.
- **Ad Browsing**: Clients can view all available ads, search for specific services, and get detailed information about each ad.
- **Ad Management**: Companies have the ability to update or delete their posted ads.

## Technologies

- **Java**: The core programming language used.
- **Spring Boot**: Framework for creating stand-alone, production-grade Spring-based applications.
- **Maven**: Dependency management and build automation tool.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java JDK 11 or later
- Maven 3.6 or later

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Dancan254/ReservioX.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ReservioX
   ```
3. Build the project with Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

After starting the application, it will be accessible at `http://localhost:8080`.

## Usage

### API Endpoints

- **Post an Ad**: Send a POST request to `/api/company/ad/{companyId}` with the ad details.
- **View All Ads**: Send a GET request to `/api/company/ads/{companyId}` to retrieve all ads posted by a specific company.
- **Get Ad Details**: Send a GET request to `/api/company/ad/{adId}` to get details of a specific ad.
- **Update an Ad**: Send a PUT request to `/api/company/ad/{adId}` with the updated ad details.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any bugs or improvements.

## License

Distributed under the MIT License. See `LICENSE` for more information.
