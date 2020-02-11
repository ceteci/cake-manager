# cake-manager

waracle cakemanager microservice demo app architectural<br/>
runs on http://localhost:8080/

![cakeManager](https://user-images.githubusercontent.com/1232089/74264732-17e9fe00-4cf9-11ea-98bf-79c1780d662f.png)

# Dependencies
- Java 11
- Maven
- Spring
- Docker
- Cassandra
- JUnit
- Mockito


# Run
1. first run docker compose file<br/>
- docker-compose -f /docs/docker-compose.yml up -f
2. before running, create below keyspace and table on cassandra using initial.cql in /docs folder.
- keySpace: waracleSpace
- table: cakes
3. run in this order
- i. ConfigServerApplication      (http://localhost:8888/)
- ii. EurekaServerApplication     (http://localhost:8761/)
- iii. GatewayServerApplication   (http://localhost:8080/)
- iv. CakeServiceApplication      (http://localhost:8501/)
4. get a valid token<br/>
- username: waracle
- password: 1234
5. test endpoints with token

please use below sample postman message to test

1. to get token<br/>
curl --location --request POST 'http://localhost:8080/login?username=adad&password=424234' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "waracle",
    "password": "1234"
}'
2. to get all cakes<br/>
curl --location --request GET 'http://localhost:8080/' \
--header 'Authorization: Bearer xxxAA11'
3. to add new cake<br/>
curl --location --request POST 'http://localhost:8080' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer xxxAA11' \
--data-raw '{ 
      "title":"waracle kek",
      "desc":"sponge with jam",
      "image":"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"
}'
4. update an existing cake<br/>
curl --location --request PUT 'http://localhost:8080/cakes/1352976a-d0de-4f65-acd2-b8edafdbb18e' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer xxxAA11' \
--data-raw '{
    "title": "waracle kek updated",
    "desc": "sponge with jam",
    "image": "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"
}'
5. delete an existing cake<br/>
curl --location --request DELETE 'http://localhost:8080/cakes/8b33cf02-c98b-4e61-9cf6-209e421a45a6' \
--header 'Authorization: Bearer xxAA111'


