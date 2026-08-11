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
        { "provider._id": 1, "date": 1, "times": 1 },
        { name: "unique_provider_date_times", unique: true }
    )
````