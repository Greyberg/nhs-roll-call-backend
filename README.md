# nhs-roll-call-backend
Backend microservice for frontend nhs-roll-call. To collect and surface data about nhs worker availability for the purpose of rota creation.

# Running the service

In the home directory run
```sbt run```

# Testing locally

In the home directory run
```sbt test```

# Setting up the database locally

You will need to install psql

Use ```createdb -U rosegarratt -h localhost -p 5432 -W nhsrollcall``` 
And when prompted for a password, enter `password`

Connect to the database with
```psql -d nhsrollcall```

Create the tables with
```\i create_users_table.sql ```
And
```\i create_availability_table.sql```

Note - this will only work for user rosegarratt. 
TBD - how to create new role. 
The workaround is to edit the connection details to be specific to your username in application.conf

