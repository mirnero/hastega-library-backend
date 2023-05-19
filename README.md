
## Postgres Database
docker-compose up

## Start App
./mvnw spring-boot:run

## ALLINONE with docker-compose
cd src/main
docker-compose up

## API
### Get all books
GET http://localhost:8080/books

### Get books with parameters
GET http://localhost:8080/books?page=0&size=10&search=Dewer&sort=dateAdded

RESPONSE
```json
{
	"content": [
		{
			"id": 115,
			"title": "Man from Elysian Fields, The",
			"author": "Dewer",
			"isbn": "236531546-1",
			"dateAdded": "2023-03-12",
			"dateDeleted": null,
			"plot": null,
			"numberOfReads": 369
		}
	],
	"pageable": {
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 10,
		"paged": true,
		"unpaged": false
	},
	"last": true,
	"totalPages": 1,
	"totalElements": 1,
	"size": 10,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 1,
	"empty": false
}
```

### Get book with id = {id}
GET http://localhost:8080/{id}

REPSONSE
```json
{
	"id": 118,
	"title": "Keys of the Kingdom, The",
	"author": "Broomfield",
	"isbn": "150116441-4",
	"dateAdded": "2022-09-22",
	"dateDeleted": "2023-05-19",
	"plot": "Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy.",
	"numberOfReads": 36
}
```

### Create new book
POST http://localhost:8080/books
```json
{
	"title": "Åsa-Nisse - Wälkom to Knohult",
	"author": "Gaize",
	"isbn": "958197989-1",
	"plot": "Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.",
}
```
RESPONSE
```json
{
	"id": 29,
	"title": "Åsa-Nisse - Wälkom to Knohult",
	"author": "Gaize",
	"isbn": "958197989-1",
	"dateAdded": "2023-05-19",
	"dateDeleted": null,
	"plot": "Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.",
	"numberOfReads": 0
}
```

### Edit book with id = {id}
PUT http://localhost:8080/books/{id}
```json
{
	"title": "New title"
}
```
RESPONSE
```json
{
	"id": 29,
	"title": "New title",
	"author": "Gaize",
	"isbn": "958197989-1",
	"dateAdded": "2023-05-19",
	"dateDeleted": null,
	"plot": "Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.",
	"numberOfReads": 0
}
```


### Delete book with id = {id}
DELETE http://localhost:8080/books/{id}

RESPONSE
```json
{
	"id": 29,
	"title": "New title",
	"author": "Gaize",
	"isbn": "958197989-1",
	"dateAdded": "2023-05-19",
	"dateDeleted": "2023-05-19",
	"plot": "Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.",
	"numberOfReads": 0
}
```

### Read book with id = {id}
PUT http://localhost:8080/books/{id}/read

RESPONSE
```json
{
	"id": 29,
	"title": "New title",
	"author": "Gaize",
	"isbn": "958197989-1",
	"dateAdded": "2023-05-19",
	"dateDeleted": null,
	"plot": "Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.",
	"numberOfReads": 1
}
```