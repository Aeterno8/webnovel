# Webnovel Reading Website - Backend

**NOT STABLE AND READY FOR USE**

This project aims to build a backend system for efficiently storing and managing webnovels extracted by open-source tools. By using a hybrid database approach, combining PostgreSQL and MongoDB, the system ensures optimized storage and fast retrieval of webnovel data.

## Features

- [ ] REST API for accessing and managing webnovel content (**ONGOING**)
- [ ] User authentication and authorization (**ONGOING**)

## Planned Features

- [ ] Full-text search functionality for webnovels
- [ ] Caching mechanisms for faster data retrieval
- [ ] Automatic backup and recovery system
- [ ] Pagination and filtering for API endpoints

## Tech Stack

- **Backend Framework**: Spring Boot
- **Database**:
    - PostgreSQL for relational data (users, novel metadata)
    - MongoDB for NoSQL data (chapters, stored in json)
- **API**: RESTful architecture for managing webnovel data
