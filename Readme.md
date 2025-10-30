# Seatscape API Documentation

Welcome to the Seatscape API documentation. This API provides endpoints to manage cinemas, movies, shows, tickets, food items, and food orders.

## Controllers

### Cinema Controller

#### Endpoints

- **GET /cinema/all**: Get all cinemas.
- **GET /cinema/{id}**: Get cinema by ID.
- **GET /cinema/city/{cityname}**: Get cinemas by city.
- **GET /cinema/state/{statename}**: Get cinemas by state.
- **GET /cinema/statecity/{statename}/{cityname}**: Get cinemas by state and city.
- **DELETE /cinema/delete/{id}**: Delete cinema by ID.
- **POST /cinema/put**: Add or update a cinema.

### Food Item Controller

#### Endpoints

- **GET /food/all**: Get all food items.
- **GET /food/id/{id}**: Get food item by ID.
- **GET /food/itemtype/{itemtype}**: Get food items by type.
- **PUT /food/add**: Add a new food item.

### Food Order Controller

#### Endpoints

- **GET /foodorder/getall**: Get all food orders.
- **GET /foodorder/ticketid/{ticketid}**: Get food order by ticket ID.
- **GET /foodorder/id**: Get food order by ID.
- **GET /foodorder/showid/{showid}**: Get food orders by show ID.
- **PUT /foodorder/placeorder**: Place a food order.

### Hall Controller

#### Endpoints

- **GET /hall/getall**: Get all halls.
- **PUT /hall/create/{cinemaid}**: Create halls for a cinema.
- **GET /hall/get/{cinemaid}**: Get halls by cinema ID.

### Movie Controller

#### Endpoints

- **GET /movie/getAll**: Get all movies.
- **GET /movie/get/{id}**: Get movie by ID.
- **GET /movie/get/duration/greater/{duration}**: Get movies with duration greater than a given value.
- **GET /movie/get/duration/lesser/{duration}**: Get movies with duration lesser than a given value.
- **GET /movie/get/language/{lang}**: Get movies by language.
- **GET /movie/get/genre/{genre}**: Get movies by genre.
- **GET /movie/get/title/{keyword}**: Find movies by title containing a keyword.
- **DELETE /movie/delete/{id}**: Delete movie by ID.
- **POST /movie/put**: Add or update a movie.

### Show Controller

#### Endpoints

- **GET /show/all**: Get all shows.
- **GET /show/id/{id}**: Get show by ID.
- **GET /show/cid/{cinemaid}**: Get shows by cinema ID.
- **GET /show/mid/{movieid}**: Get shows by movie ID.
- **GET /show/future**: Get future shows.
- **GET /show/future/{cinemaid}**: Get future shows by cinema ID.
- **GET /show/future/mid/{movieid}**: Get future shows by movie ID.
- **GET /show/city/{cityname}**: Get shows by city name.
- **GET /show/citymovie/{cityname}/{moviename}**: Get shows by city name and movie name.
- **PUT /show/add**: Add a new show.

### Show Wrapper Controller

#### Endpoints

- **PUT /show/add**: Add a new show.

### Ticket Controller

#### Endpoints

- **PUT /ticket/create**: Create a ticket.
- **GET /ticket/getall**: Get all tickets.
- **GET /ticket/showid/{showid}**: Get tickets by show ID.
- **GET /ticket/id/{ticketid}**: Get ticket by ticket ID.
- **GET /ticket/user/{username}**: Get tickets by username.
- **DELETE /ticket/cancel/{id}**: Cancel a ticket by ID.
- **DELETE /ticket/cancel/partial**: Cancel a part of a ticket.
- **GET /ticket/show/{showid}**: Get available seats for a show.

## Usage

To use these endpoints, make HTTP requests to the specified routes with appropriate parameters and payloads.

## Contributors

- Shubhangi Singh


