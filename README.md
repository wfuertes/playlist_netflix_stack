# playlist

This is a small example of using Netflix OSS Stack for microservices. 
Here we have **playlist-edge** as our client or API that calls **playlist-middle** 
(responsible for all business rules and "hard" process like database connections or 
other as integration with external services). The **playlist-core** is just a holder
for common classes and data-types and the **playlist-client** is our REST client
used for **playlist-edge**.   

```
 (playlist-edge) ----- register ----> [Eureka] <----- register ----- (playlist-middle)
       |--- get list of services ---------^
(playlist-client) <----- call middle service using ribbon-client ------------^
```

### playlist-core

Common classes and data-type shared between edge, middle and our client.

### playlist-client

REST client responsible for our client load-balance using ribbon (https://github.com/Netflix/ribbon/wiki).
It is used at edge in order to access middle internal services. 

### playlist-middle

This is our microservice itself. It is responsible for all business logic, database access, external 
integrations, and beyond.

### playlist-edge

This is our API it is a tinny shell its responsibility is to call and mapping any middle service
responses in order to provide a good JSON / XML to the end-users.

### Infrastructure

I am using the Eureka-Server in order to the services discovery and connection all layer (edge and middle here). 

### Run locally

Pre-requirements:
* Docker; 
* Java 1.8;
* Maven 3.x.

1. Make sure you have docker installed. 
2. We need to run Eureka-Server:

```
$ docker pull wfuertes/eureka-server:latest
$ docker run -p 8080:8080 -d wfuertes/eureka-server
```
After you run eureka-server wait for about 3 minutes it takes some time to get up. 

3. Run *playlist-middle* and *playlist-edge* using maven. Use two terminals one for each.

3.1. 
```
$ cd path_to/playlist-middle
$ mvn clean tomcat7:run
```

3.2. 
```
$ cd path_to/playlist-edge
$ mvn clean tomcat7:run
```

After you run it, you need to wait about 1 minute for both start running and register at Eureka.


4. Finally, you can try some requests:

```
curl --location --request GET 'http://localhost:8091/playlistedge/playlist'
```

**Sample of Response:**
```
{
    "status": 200,
    "message": "Playlists retrieved with success",
    "playlists": [
        {
            "id": 1603047552688,
            "name": "Classic Rock I",
            "musics": [
                "Highway to Hell",
                "Jump",
                "Sultans Of Swing"
            ]
        },
        {
            "id": 1603047552688,
            "name": "Classic Rock II",
            "musics": [
                "Highway to Hell",
                "Jump",
                "Sultans Of Swing"
            ]
        },
        {
            "id": 1603047552688,
            "name": "Classic Rock II",
            "musics": [
                "Highway to Hell",
                "Jump",
                "Sultans Of Swing"
            ]
        }
    ]
}
```
