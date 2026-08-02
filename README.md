### Application

Spring boot Run:

```bash
    ./gradlew bootRun --args='--spring.profiles.active=dev'
```

Postgres sequence:
```bash
    SELECT sequencename FROM pg_sequences;
    
    SELECT setval('examples_id_seq', (SELECT MAX(id) FROM examples));
```