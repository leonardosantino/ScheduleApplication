### Application

Spring boot Run:

```shell
    ./gradlew bootRun --args='--spring.profiles.active=dev'
```

MongoDB:
```shell
    db.getUsers()
    
    db.createUser({
        user: "schedule",
        pwd: "XaY2cMuXdJ4I7aJA",
        roles: [ { role: "readWrite", db: "schedule" } ]
    })
    
    db.bookings.createIndex(
        { "service.providerId": 1, "date": 1, "time": 1 },
        { unique: true, name: "unique" }
    )
````