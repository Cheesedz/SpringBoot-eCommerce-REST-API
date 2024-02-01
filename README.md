# Spring Boot e-Commerce REST API

Build Restful CRUD API for an e-commerce system

# Tools
+ Spring Boot 3.2.1
+ Spring Security 6
+ MySQL 8
+ JPA
+ Hibernate

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Cheesedz/SpringBoot-eCommerce-REST-API.git
```

**2. Create Mysql database**
```bash
create database ecommerce
```
- run `src/main/resources/ecommerce.sql`

**3. Change MySQL username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your MySQL installation

**4. Run the app using Maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Rest APIs

The app defines the following CRUD APIs.

### Auth

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/auth/signup | Sign up | [JSON](#signup) |
| POST   | /api/auth/login | Log in | [JSON](#login) |

### Users

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users | Get all users (Only for admins) | |
| GET    | /api/users/{id} | Get user profile by id (For logged in user) | |
| GET    | /api/users/{id}/orders | Get all user's orders by id (For logged in user) | |
| GET    | /api/users/{id}/cart | Get all user's cart by id (For logged in user) | |
| GET    | /api/users/{id}/vouchers | Get all user's vouchers by id (For logged in user) | |
| GET    | /api/users/{id}/notifications | Get all user's notifications by id (For logged in user) | |
| GET    | /api/users/checkUsernameAvailability | Check if username is available to register | |
| GET    | /api/users/checkEmailAvailability | Check if email is available to register | |
| POST   | /api/users/add | Add user (Only for admins) | [JSON](#usercreate) |
| PUT    | /api/users/{id} | Update user (For logged in user or admin) | [JSON](#userupdate) |
| DELETE | /api/users/{id} | Delete user (For logged in user or admin) | |

### Shops

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/shops | Get all shops (Only for admins) | |
| GET    | /api/shops/{id} | Get shop by id | |
| GET    | /api/shops/{id}/all-products | Get all shop's products by shop's id | |
| POST   | /api/shops/add | Add new shop (By admin only) | [JSON](#shopcreate) |
| PUT    | /api/shops/{id} | Update shop (For shop or admin) | [JSON](#shopupdate) |
| DELETE | /api/shops/{id} | Delete shop (For shop or admin) | |

### Products

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/shops/{shopId}/products | Get all shop's products | |
| GET    | /api/shops/{shopId}/products/id | Get shop's product by id | |
| POST   | /api/shops/{shopId}/products/add | Add new product (By shop only) | [JSON](#productcreate) |
| PUT    | /api/shops/{id} | Update product (For shop or admin) | [JSON](#productupdate) |
| DELETE | /api/posts/{id} | Delete product (For shop or admin) | |

### Cart

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/{userId}/cart | Get user's cart (For logged in user or admin) | |
| GET    | /api/users/{userId}/cart/cartItems | Get all user's cart items (For logged in user or admin) | |
| GET    | /api/users/{userId}/cart/price | Get total price of cart (By logged in user) | |
| POST   | /api/users/{userId}/cart/add | Add new cart (Only by admin) | [JSON](#cartcreate) |
| PUT    | /api/users/{userId}/cart/{id} | Update cart (If cart belongs to logged in user or admin) | [JSON](#cartupdate) |
| DELETE | /api/users/{userId}/cart/{id} | Delete cart (If cart belongs to logged in user or admin) | |

### CartItems

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/{userId}/cart/cartItems/{id} | Get user's cart item by id (For logged in user or admin) | |
| POST   | /api/users/{userId}/cart/cartItems/add | Add new cart item (Only by user) | [JSON](#cartItemcreate) |
| PUT    | /api/users/{userId}/cart/cartItems/{id} | Update cart item (If cart belongs to logged in user or admin) | [JSON](#cartItemupdate) |
| DELETE | /api/users/{userId}/cart/cartItems/{id} | Delete cart item (If cart belongs to logged in user or admin) | |

### Notifications

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/{userId}/notifications | Get all user's notifications (For logged in user or admin) | |
| GET    | /api/users/{userId}/notifications/{id} | Get user's notification by id (For logged in user or admin) | |
| POST   | /api/users/{userId}/notifications/add | Add new notification (By shop or admin) | [JSON](#notificationcreate) |
| PUT    | /api/users/{userId}/notifications/{id} | Update notification (By shop or admin) | [JSON](#notificationupdate) |
| DELETE | /api/users/{userId}/notifications/{id} | Delete notification (By shop or admin) | |

### Vouchers

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/{userId}/vouchers | Get all user's vouchers (For logged in user or admin) | |
| GET    | /api/users/{userId}/vouchers/{id} | Get user's voucher by id (For logged in user or admin) | |
| POST   | /api/users/{userId}/vouchers/add | Add new voucher (By shop or admin) | [JSON](#vouchercreate) |
| PUT    | /api/users/{userId}/vouchers/{id} | Update voucher (By shop or admin) | [JSON](#voucherupdate) |
| DELETE | /api/users/{userId}/vouchers/{id} | Delete voucher (By shop or admin) | |

Test them using Postman or any other rest client.

## Sample Valid JSON Request Bodys

##### <a id="signup">Sign Up -> /api/auth/signup</a>
```json
{
	"name": "Nguyen Van A",
	"username": "abcdxyz",
	"email": "abcd@gmail.com",
	"gender": "male",
	"dob": "1995-02-15",
	"phone": "0123456789",
	"password": "password"
}
```

##### <a id="login">Log In -> /api/auth/login</a>
```json
{
	"usernameOrEmail": "abcd123",
	"password": "password"
}
```

##### <a id="usercreate">Create User -> /api/users/add</a>
```json
{
	"username": "tuandz",
	"name": "Nguyen Viet Tuan",
	"email": "tuan@gmail.com",
	"phone": "0974921999",
	"gender": "male",
	"dob": "2004-01-17"
}
```

##### <a id="userupdate">Update User -> /api/users/{id}</a>
```json
{
	"username": "tuandzvl",
	"name": "Nguyen Viet Tuan",
	"email": "tuan@gmail.com",
	"phone": "0974921999",
	"gender": "male",
	"dob": "2004-01-17"
}
```

##### <a id="shopcreate">Create Shop -> /api/shops/add</a>
```json
{
	"name": "Yamaha Town Le Van Luong",
	"typeOfShop": "Mall",
	"typeOfProduct": "Motorbike",
	"description": "One of the most famous motor showrooms in Ha Noi",
	"followers": "11549",
	"following": "412",
	"joiningDate": "2019-10-25",
	"rating": "4.9"
}
```

##### <a id="shopupdate">Update Shop -> /api/shops/{id}</a>
```json
{
	"name": "Yamaha Town Xuan Thuy",
	"typeOfShop": "Mall",
	"typeOfProduct": "Motorbike",
	"description": "One of the most famous motor showrooms in Ha Noi",
	"followers": "10241",
	"following": "122",
	"joiningDate": "2022-12-15",
	"rating": "4.6"
}
```

##### <a id="productcreate">Create Product ->/api/shops/{shopId}/products/add</a>
```json
{
	"productId": 12,
	"name": "Yamaha Sirius",
        "category": "Motorbike",
        "description": "Smooth",
        "shopName": "Yamaha Town",
        "sold": 9250,
        "price": 1920.0,
        "rating": 5.0,
        "available": 5813,
        "imgURL": ""
}
```

##### <a id="productupdate">Update Product ->/api/shops/{shopId}/products/{id}</a>
```json
{
	"productId": 16,
	"name": "Yamaha Jupiter",
        "category": "Motorbike",
        "description": "Exciting",
        "shopName": "Yamaha Town",
        "sold": 1250,
        "price": 1720.0,
        "rating": 5.0,
        "available": 5113,
        "imgURL": ""
}
```

##### <a id="cartcreate">Create Cart -> /api/users/{userId}/cart/add</a>
```json
{
	"userId": 102
}
```

##### <a id="cartupdate">Update Cart -> /api/users/{userId}/cart/{id}</a>
```json
{
	"userId": 132
}
```

##### <a id="cartItemcreate">Create Album -> /api/users/{userId}/cart/cartItems/add</a>
```json
{
	"cartId": 212,
	"productId": 12,
	"quantity": 3
}
```

##### <a id="cartItemupdate">Update Album -> /api/users/{userId}/cart/cartItems/{id}</a>
```json
{
	"cartId": 212,
	"productId": 16,
	"quantity": 1
}
```

##### <a id="notificationcreate">Create Notification -> /api/users/{userId}/notifications/add</a>
```json
{
	"title": "Exciting News! You Have a Chance to Win a Voucher!",
	"detailContent": "We hope this message finds you well! We're thrilled to inform you that you've been selected for an exclusive opportunity to win a fantastic voucher!",
	"userId": 123
}
```

##### <a id="notificationupdate">Update Notification -> /api/users/{userId}/notifications/{id}</a>
```json
{
	"title": "Exciting News! You Have a Chance to Win a Voucher!",
	"detailContent": "We hope this message finds you well! We're thrilled to inform you that you've been selected for an exclusive opportunity to win a fantastic voucher!",
	"userId": 101
}
```

##### <a id="vouchercreate">Create Voucher -> /api/users/{userId}/vouchers/add</a>
```json
{
	"name": "Free ship upto 15K",
	"description": "Only apply to all products",
	"minimumOrderValue": 20.0,
	"discountAmount": 15.0,
	"expirationDate": "2024-02-14",
	"userId": 201
}
```

##### <a id="voucherupdate">Update Voucher-> /api/users/{userId}/vouchers/{id}</a>
```json
{
	"name": "SAVE20NOW",
	"description": "Enjoy a special discount of 20% on your next purchase!",
	"minimumOrderValue": 50.0,
	"discountAmount": 20.0,
	"expirationDate": "2024-12-31",
	"userId": 221
}
```
