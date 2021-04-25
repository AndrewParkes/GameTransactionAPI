# GameTransactionAPI

This was a 7 day task done after work hours.
 
# Task:
The task is to design and build a simple REST API.

There is a requirement for an API that must be able to store and retrieve game transactions. A game transaction is a single piece of data that consists of a user id, a transaction id, a product and a monetary amount. 

The API must have at least two endpoints – one to store a single transaction and the second to retrieve all transactions.

How you store the data, what language in which you develop the API and how you serve the API is completely up to you.

# Overview
* Springboot application
* Connected to a Dynamo BD
* Deployed on a AWS EC2 instance
* Used API Gateway to expose Rest endpoints
* Implemented OAuth using AWS Cognito linked to a Authorizer in API Gateway

# Endpoints
* Save
* Update
* RetrieveAll
* RetrieveByUser
* RetrieveByProduct
* HealthEndpoints (Actuator)

# Validations
* Upfront object validation using ConstraintValidator
* Custome error class to manage exception flow - ProcessingRequestException 
* Controller Advice to log and parse excepptions
* ErrorController to parse rest exceptions
