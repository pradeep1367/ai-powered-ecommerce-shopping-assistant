# AI-Powered E-commerce Shopping Assistant

A full-stack e-commerce application built using **Java, Spring Boot, Spring Security, JWT Authentication, MySQL, HTML, CSS and JavaScript**.

The application allows customers to register, log in securely, browse products, explore categories and manage their shopping cart.

AI-powered shopping assistance and product recommendations are planned as future enhancements.

## Features

### 1. User Authentication
- Customer registration and login.
- JWT-based authentication.
- Password encryption using BCrypt.
- Role-based authorization using Spring Security.

### 2. Product Management
- View available products.
- Browse product categories.
- Search products by keyword.
- Filter products by category and price.
- Sort products by price.
- Pagination support.

### 3. Shopping Cart Management
- Add products to the cart.
- View cart items.
- Update product quantities.
- Remove products from the cart.
- Calculate individual item subtotals.
- Calculate the overall cart total.

## Technologies Used

 Component             Technologies 
------------------------------------------------
 Backend               Java 21, Spring Boot 
 Security              Spring Security, JWT, BCrypt 
 ORM                   Spring Data JPA, Hibernate 
 Database              MySQL 
 Frontend              HTML5, CSS3, JavaScript 
 API Testing           Postman 
 Database Tool         MySQL Workbench 
 IDE                   Visual Studio Code 
 Build Tool            Maven 
 Version Control       Git and GitHub 

## Project Architecture

The application follows a layered backend architecture.

```text
AI-Powered E-commerce Shopping Assistant
│
├── backend/
│   └── src/main/java/com/ecommerce/backend/
│       ├── config/
│       ├── controller/
│       ├── dto/
│       ├── entity/
│       ├── filter/
│       ├── repository/
│       └── service/
│
├── frontend/
│   ├── index.html
│   ├── css/
│   │   └── style.css
│   ├── js/
│   │   └── app.js
│   └── pages/
│       ├── login.html
│       ├── register.html
│       ├── products.html
│       └── cart.html
│
├── database/
│   ├── schema.sql
│   ├── sample-data.sql
│   └── queries.sql
│
├── docs/
│   ├── architecture.md
│   ├── database-design.md
│   └── project-flowchart.md
│
├── ai-service/
└── README.md
```

## Application Workflow

1. A new customer registers an account.
2. The customer logs in using their credentials.
3. The backend validates credentials and generates a JWT.
4. The frontend uses the JWT for protected API requests.
5. Customers browse, search, filter and sort products.
6. Customers add products to their shopping cart.
7. Customers update quantities or remove products.
8. The application calculates item subtotals and the cart total.

## Database Setup

1. Install MySQL Server and MySQL Workbench.
2. Open the `database/schema.sql` file in MySQL Workbench.
3. Execute the schema script to create the required database tables.
4. Execute `database/sample-data.sql` to insert sample data.
5. Configure the database connection in the backend application configuration.

Keep database credentials and JWT secrets outside version control. Use environment variables or a local configuration file.

## Backend Setup

### Prerequisites
- Java 21
- MySQL Server
- Maven (or the included Maven Wrapper)

### Steps

1. Clone the repository:

```bash
git clone https://github.com/pradeep1367/ai-powered-ecommerce-shopping-assistant.git
```

2. Open the project:

```bash
cd ai-powered-ecommerce-shopping-assistant
```

3. Configure your MySQL connection and required environment variables.

4. Navigate to the backend:

```bash
cd backend
```

5. Start the Spring Boot application on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend runs at:

`http://localhost:8080`

## Frontend Setup

1. Open the project in Visual Studio Code.
2. Open the `frontend` folder.
3. Run `index.html` using the VS Code Live Server extension.
4. Navigate through the registration, login, products and cart pages.

Example local URL:

`http://127.0.0.1:5500`

## API Endpoints

Base URL: `http://localhost:8080/api/v1`

| Method         Endpoint                               Description 
--------------------------------------------------------------------------------
| POST         `/users/register`                     Register a customer 
| POST          `/users/login`                       Customer login 
| GET           `/categories`                        Get all categories 
| GET           `/products`                          Get all products 
| GET           `/products/{id}`                     Get product by ID 
| GET           `/products/filter`                   Search and filter products 
| POST          `/customer/cart/items`               Add product to cart 
| GET           `/customer/cart`                     View shopping cart 
| PUT           `/customer/cart/items/{cartItemId}`  Update cart quantity 
| DELETE        `/customer/cart/items/{cartItemId}`  Remove cart item 

Protected customer endpoints require a valid JWT in the Authorization header:

`Authorization: Bearer <token>`

## Current Project Scope

- User registration and login.
- JWT authentication and authorization.
- Product and category browsing.
- Product search, filtering, sorting and pagination.
- Shopping cart management.
- Cart subtotal and total price calculation.

## Future Enhancements

- AI-powered shopping assistant.
- Personalized product recommendations.
- Intelligent product search.
- Order management.
- Payment gateway integration.
- Order tracking.
- Product reviews and ratings.


----------------------------------------------------------------------------------------------------------------------------------------------

## Author

**Pradeep Mudhol**

GitHub: [pradeep1367](https://github.com/pradeep1367)

Project Repository: [AI-Powered E-commerce Shopping Assistant](https://github.com/pradeep1367/ai-powered-ecommerce-shopping-assistant)
