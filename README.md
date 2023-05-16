# hastega-library-backend
Test tecnico

## Database
docker-compose up

## Start App
./mvnw spring-boot:run

## API
### Get all books
GET http://localhost:8080/books

### Get book with id = {id}
GET http://localhost:8080/{id}

### Create new book
POST http://localhost:8080/books
```json
{
	"title": "Åsa-Nisse - Wälkom to Knohult",
	"author": "Gaize",
	"isbn": "958197989-1",
	"dateAdded": "2023-04-05",
	"dateDeleted": "2023-05-10",
	"plot": "Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.",
	"numberOfReads": 25
}
```

### Edit book with id = {id}
PUT http://localhost:8080/books/{id}
```json
{
	"title": "Åsa-Nisse - Wälkom to Knohult - new title",
	"author": "Gaize",
	"isbn": "958197989-1",
	"dateAdded": "2023-04-05",
	"dateDeleted": "2023-05-10",
	"plot": "Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.",
	"numberOfReads": 25
}
```

### Delete book with id = {id}
DELETE http://localhost:8080/books/{id}
