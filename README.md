# Dockerfiles
## MySQL

Build the image:

```
docker build -t greppers/holus-db:latest -f Dockerfile.db .
```

Start the container:

```
docker run -d greppers/holus-db
```
