# currency-exchange  MicroService


These instructions will get you a copy of the project up and running on your
local machine for development and testing purposes. See deployment for notes on
how to deploy the project on a live system.

**Access**

```
mvn compile
```
```
mvn test              # Run test cases
mvn verify            # Run test cases & static code validations & package
```
**URL EXPOSED MONGODB IN CLOUD AWS**
```
HOST: 3.133.79.86
PORT: 27017

```

**Example call service AuthLogin localhost**

*Request
```
examples request Body
----------------------------------------------
'{ 
 "username": "alessandro",
 "password": "passwordAlessandro"
 }'
---------------------------------------------
Import curl postman 'RAW TEXT'

curl --location --request POST 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=244E43DAA694C132B57EA64C69EB667C' \
--data-raw '{ 
 "username": "alessandro",
 "password": "passwordAlessandro"
 }'
```

*Response 
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiYWxlc3NhbmRybyIsImlhdCI6MTYwODQ2MDc1NSwiZXhwIjozMTMzMjk0MDk1fQ.UQXw3q7OVsFKRYCLugn5rLqOONBDMJ3OwLYvD9lM-qFK35nP_GtrntKIZXlPiRrIe8EYBsrwKoMnFCatCXinfA",
    "user": "alessandro"
}

```
**Example call service Post CurrencyExchange localhost**

*Request
```
curl --location --request POST 'http://localhost:8080/v1/exchange/currency' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiaGViZXJ0IiwiaWF0IjoxNjA4NDU4NzUzLCJleHAiOjMxMzMyOTIwOTN9.g4funoKWNqv1Afz-XO7LYRMFl1VwsBLGX0FyuMQHELXQEAQMRucDxH-w5i66J52IZe2mFL3R-AFCOKX6s4w4qA' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=244E43DAA694C132B57EA64C69EB667C' \
--data-raw '{
    "amount":250,
    "originCurrency":"PEN",
    "destinationCurrency":"USD"
}'
```
*Response 
```
{
    "amount": 250,
    "amountWithExchangeRate": 70.00,
    "originCurrency": "PEN",
    "destinationCurrency": "USD",
    "exchangeRate": 0.28
}
```

**Example call service Update ExchangeRate localhost**

*Request
```
curl --location --request PATCH 'http://localhost:8080/v1/exchange/rate' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiaGViZXJ0IiwiaWF0IjoxNjA4NDUxMDU1LCJleHAiOjMxMzMyODQzOTV9.97tVaOrqzF3v4o_Z8mCESTy737BbkVhoCZaHjtW9nGmqmXH9EQHndfUjkubTEo85nUzJucrOSUkaR62R6TqMew' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=244E43DAA694C132B57EA64C69EB667C' \
--data-raw '{
    "exchangeRate":3.56,
    "currencyTypeOrigin":"USD",
    "currencyTypeDestination":"PEN"
}'

```

*Response 
```
{
    "id": "5fdf2d16bbf8d161fa3390e1",
    "currencyTypeOrigin": "USD",
    "currencyTypeDestination": "PEN",
    "exchangeRate": 3.56
}

```
**Software & Libs**

```
* Java SDK 11 
* Maven 3.x or higher
* Docker 17.12.x or higher
* lombok 
* spring-boot-starter-data-mongodb-reactive
* spring-boot-starter-security
* jjwt-api
* jjwt-impl
* jjwt-jackson
* spring-boot-starter-webflux
* lombok
* spring-boot-devtools
* validation-api
* spring-boot-starter-validation
```


**TEST LIBS & PLUGINS**

```
* spring-boot-starter-test
* reactor-test
* junit
```

**CREATE IMAGE DB MONGO LOCAL**

```
#container mysql

# replace directory in windows /opt/data example C://data

docker run --name some-mongo -p 27017:27017 -v /opt/data:/data/db -d mongo:latest
```

**CREATE IMAGE APPLICATION JAVA**
```
#test & generate jar
mvn verify

#build image currency-exchange
 
docker build -t currency-exchange:0.0.1 --build-arg artifact_id=currency-exchange --build-arg artifact_version=0.0.1  -f devops/Dockerfile .

docker run -p 8080:8080 -d currency-exchange:0.0.1

```


Run MicroService within Eclipse or IntelliJ.


