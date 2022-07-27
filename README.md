# Spring Boot, MongoDB, React

Build a Fully-Fledged Task App with Spring Boot & MongoDB in the Backend and React in the frontend.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. MongoDB - 3.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/afrozloya/dayofglory
```

**2. Build and run the backend app using maven**

```bash
cd api
mvn package
java -jar superai/dayofglory-0.0.1-SNAPSHOT
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The backend server will start at <http://localhost:8080>.

**3. Run the frontend app using npm**

```bash
cd frontend
npm install
```

```bash
npm start
```

Frontend server will run on <http://localhost:3000>

## Future Scope
better UI
automation to upload bulk image as task
auto tag with exsiting model with high confidence (value of high should be configurable)
auto tag with exsiting model with medium confidence 
( have a status that it needs manual approval/accept so now user dont need to anotate it just approve it or correct it -- if needed correction then we can have some field to maintain it for latter use --)
Change to gRPC
(REST, gRPC or WebSocket) I think grpc is best due to binary and http2 but due to time will go for http in grpc we will need proxy alsio to connect it from front end














