# Bright Suns
**Welcome to SwapiDraft!**

SwapiDraft = SWAPI + SpotDraft!

This is an integration between the publicly available Star Wars APIs and SpotDraft and this markdown file contains all the information you require to set up, run and experiment with this application.
It also contains a growing list of enhancements which will be added in upcoming versions.

SwapiDraft is a simple java-spring boot application and **v1.0.0** is limited to the following functionalities:

- Sync films and planets from SWAPI and store them in a database.
- Fetch all film details or a specific film's details from the database.
- Fetch all planet details or a specific planet's details from the database.
- Create mock users and store them in the database.
- Fetch all user details or a specific user's details from the database.
- Set custom titles for films or custom names for planets.
- Toggle favourite films and planets.
- Maintain relation between a user and his/her favourite films/planets and vice versa.

Enhancements considered for **v1.1.0**:

- Logging efforts.
- Explicit exception handling and error response handling.
- Cron based asynchronous syncing of films and planets from SWAPI.
- Ability to create new films and planets.
- Ability to update existing films and planets.
- Ability to delete (soft/hard) existing films, planets and users.
- Paginated API response as the application scales.
- Utilise metrics around number of users marking a particular film or planet as favourite.

Now, let's help you set up and run the application. **May the force be with you!**

## Pre-Requisites

- **MongoDB** is SwapiDraft's choice of database and to ensure the application functions as expected, a mongo server is expected to be running on your local machine.
  [Here](https://www.mongodb.com/docs/manual/administration/install-community/) is a reference which you can use to achieve this. If you do not have Home Brew installed on your machine, you can refer [this](https://brew.sh/) documentation.

- **Intellij Idea** is the preferred IDE for this application. It can be downloaded from [here](https://www.jetbrains.com/idea/download/#section=mac).
- Since this is a backend application, the perfect tool to utilise the APIs is **Postman** which can be downloaded from [here](https://www.postman.com/downloads/).

Once the above steps are complete, it's time to launch SwapiDraft!

## APIs

Now that you are done with the pre-requisites, I'm assuming the application is starting up on your machine.
It may take a maximum of about **~100 seconds** to start up due to data syncing and saving activities scheduled in the background.

Let's walk through the various APIs provided in v1.0.0. [Here](https://api.postman.com/collections/6937530-fee6fcdc-6106-47ec-b58a-49496b2b48fd?access_key=PMAT-01GVHZFE2ZZMF58CH796M59538) is a link to the postman collection of these APIs.

### General

Overview of the general APIs.

- **Endpoints**

| Endpoint | Type | Description                         |
|----------|------|-------------------------------------|
| /health  | GET  | SwapiDraft's health check endpoint. |

### Films

Overview of APIs corresponding to films.

- **Endpoints**

| Endpoints   | Method | Description                                                                               |
|-------------|--------|-------------------------------------------------------------------------------------------|
| /films      | GET    | Used to fetch all film details or a specific film's details based on the input parameter. |
| /sync/films | GET    | Used to sync all film details from SWAPI and store in the database.                       |

- **Parameters**

| Parameter | Type   | Mandatory | Description                                                                                                                             |
|-----------|--------|-----------|-----------------------------------------------------------------------------------------------------------------------------------------|
| title     | String | No        | Fetches film details corresponding to the input parameter. The title can be the original title or the custom title defined by the user. |


### Planets

Overview of the APIs corresponding to planets.

- **Endpoints**

| Endpoints     | Method | Description                                                                                   |
|---------------|--------|-----------------------------------------------------------------------------------------------|
| /planets      | GET    | Used to fetch all planet details or a specific planet's details based on the input parameter. |
| /sync/planets | GET    | Used to sync all planet details from SWAPI and store in the database.                         |

- **Parameters**

| Parameter | Type   | Mandatory | Description                                                                                                                        |
|-----------|--------|-----------|------------------------------------------------------------------------------------------------------------------------------------|
| name      | String | No        | Fetches planet details corresponding to the input parameter. The name can be original name or the custom name defined by the user. |

### Users

Overview of the APIS corresponding to users.

- **Endpoints**

| Endpoints | Method | Description                                                                         |
|-----------|--------|-------------------------------------------------------------------------------------|
| /users    | POST   | Creates mock users depending on request parameter and saves to the database.        |
| /users    | GET    | Fetches all user details or a specific user's details based on the input parameter. |

- **Parameters**

| Parameters | Type    | Mandatory | Description                                                                                                           |
|------------|---------|-----------|-----------------------------------------------------------------------------------------------------------------------|
| count      | Integer | No        | Count denotes the number of mock users to be created. If count is not passed, **3** mock users are created and saved. |
| user_id    | String  | No        | Fetches user details corresponding to the input user id.                                                              |

### Favourites

Overview of the favourites API.

- **Endpoints**

| Endpoint    | Method | Description                                                                                                                                                                                    |
|-------------|--------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /favourites | PUT    | Used to toggle favourites for a film or a planet. It also provisions setting a custom title for a film and a custom name for a planet. All this can be achieved based on the input parameters. |

- **Parameters**

| Parameters  | Type   | Mandatory | Description                                                               |
|-------------|--------|-----------|---------------------------------------------------------------------------|
| entity_type | Enum   | Yes       | Defines the type of entity. The values are FILM/PLANET.                   |
| user_id     | String | Yes       | Unique identifier for a user.                                             |
| film_id     | String | Yes/No    | Unique identifier for a film. Either film_id or planet_id is mandatory.   |
| planet_id   | String | Yes/No    | Unique identifier for a planet. Either planet_id or film_id is mandatory. |

- **Request Body**

| Fields             | Type    | Mandatory | Description                                                                                      |
|--------------------|---------|-----------|--------------------------------------------------------------------------------------------------|
| favourite          | Boolean | Yes/No    | Used to toggle between favourite true/false for a film or a planet. Depends on input parameters. |
| custom_film_title  | String  | Yes/No    | Used to set a custom title for a film. Depends on input parameters.                              |
| custom_planet_name | String  | Yes/No    | Used to set a custom name for a planet. Depends on input parameters.                             |

## The End
I hope this markdown covers everything you require to set up, run and experiment with SwapiDraft!

If you have any inputs to enhance this application or want to have a discussion, please feel free to reach out on **sikri96@gmail.com**.

#### Watch out for v1.1.0. 'Til the Spires!