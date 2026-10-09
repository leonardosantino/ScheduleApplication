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
    
    db.appointments.createIndex(
        { "customer._id": 1, "provider._id": 1, "date": 1, "status": 1 },
        { name: "customer_provider_date_status" }
    )
    
    db.appointments.createIndex(
        { "provider._id": 1, "date": 1, "time": 1, "status": 1 },
        { name: "customer_provider_date_status" }
    )
      
    db.providers.createIndex(
        { slug: 1 },
        { name: "unique_slug", unique: true }
    )
````

Ktlint:

[reports](build/reports/ktlint/ktlintMainSourceSetFormat/ktlintMainSourceSetFormat.txt)