# bank-account-kata

### Context: 
The implementation of a basic bank account management solution which includes mainly 3 user stories (deposit money, retrieve money and get operations hictory).

### architecture choice:
I used Java 17 and Spring Boot 3 to solve this Kata because you mentioned in the description to implement a simplified solution.
However, in this context, I notice that it would be a great idea to use **CQRS architecture** (the only reason i didn't used is  the demand of a simplified solution in the spec) for two reasons:
1) Here, separating application into Query and Command has meaning, since in Banking we would conserve ability to consult data even when write operations can not be done
2) Event sourcing, since Axon give as a great APIs to manage history of operations

### Application Endpoints could be accessed using Swagger2 Ui integrated into the Application with API documentation:
Once the application is launched in local use this link  http://localhost:8080/swagger-ui.html
### Testing:
The application is tested using Junit and Mockito.

