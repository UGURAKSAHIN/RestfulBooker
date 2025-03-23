# REST Assured Demo - GET, POST, and PUT Requests
This project demonstrates how to use REST Assured to make GET, POST, and PUT requests to a REST API demo site. The tests verify the status code and key fields in the returned response

# Project Setup

# Prerequisites:
-Java Development Kit (JDK) 8 or higher
-Apache Maven (for dependency management and build)
-REST Assured library
-TestNG Framework

# How It Works:
GET Request:

The testGetRequest() method retrieves a specific post (ID 1) and checks that the response contains the expected title.

POST Request:

The testPostRequest() method creates a new post with a request body and verifies that the post is created successfully by checking the response status and fields.

PUT Request:

The testPutRequest() method updates an existing post (ID 1) with new data and verifies the updated response.

