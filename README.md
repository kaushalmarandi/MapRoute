# 🚀 https://navido.tech Realtime Route Optimizer

This is the **Spring Boot Backend** for the **Navido** application. It provides realtime optimized routes based on traffic metadata, input locations and interacts with a React frontend.


## 📌 Features
- 🛠️ Internally fetches coordinates for the source and destination, to provide users with the most optimized route.
- ⚡ Provides all the navigation instructions, ETA and distance, making easy for the users to just follow the instructions.
- 🗺️ **Fetches optimized routes** for given locations
- 🐳 **Docker support** for containerized deployment
- 🔗 **Connects with a React frontend**

## 🏗️ Tech Stack
- **Java 17+**
- **Spring Boot** (Spring Web)
- **Maven** (Build tool)
- **Docker** (Containerization)
- **Azure** (Backent Deployment)
- **React** (Frontent)

---

### ✅ Application is live, and can be directly tested on https://navido.tech

---

### Backend api testing can be done on https://routeme.azurewebsites.net/maps
#### 1. To get coordinates of source and destination , make a get call on
- ```https://routeme.azurewebsites.net/maps/coordinates```
- with request body in the format
- ```json
  {
    "source" : "Marathahalli bridge",
    "destination" : "Nexus Shantiniketan Mall Bengaluru",
    "mode" : "driving"
  }
- expected output:
- ```json
  {
    "source": {
        "coordinates": {
            "latitude": 12.9566,
            "longitude": 77.7073
        }
    },
    "destination": {
        "coordinates": {
            "latitude": 12.989626,
            "longitude": 77.728109
        }
    }

#### 2. To get the Optimized path, make a post call on
-```https://routeme.azurewebsites.net/maps/route```
- with request body in the format
 - ```json
   {
    "source" : "Marathahalli bridge",
    "destination" : "Nexus Shantiniketan Mall Bengaluru",
    "mode" : "driving"
   }
- expected output:
- ```json
  {
  "totalDistance": 8451.0,
    "eta": "0 hours 29 minutes",
    "steps": [
        {
            "instructions": "Head east on Marathahalli Bridge Service Road",
            "readable_distance": "0 km 260 metres",
            "maneuver": "depart",
            "readable_duration": "0 hours 3 minutes"
        },
        {
            "instructions": "Make a slight left onto Varthur Road",
            "readable_distance": "0 km 423 metres",
            "maneuver": "turn-slight-left",
            "readable_duration": "0 hours 2 minutes"
        },
        {
            "instructions": "Go straight towards ITPB",
            "readable_distance": "0 km 148 metres",
            "maneuver": "continue",
            "readable_duration": "0 hours 1 minutes"
        },
         {
            "instructions": "You have arrived at your destination, on the left",
            "readable_distance": "0 km 0 metres",
            "maneuver": "arrive",
            "readable_duration": "0 hours 0 minutes"
        }
    ]
  }

## 🛠️ Setup & Installation
### 1️⃣ Clone the Repository
```sh
git clone https://github.com/kaushalmarandi/MapRoute
cd MapRoute
```

### 2️⃣ Build & Run
#### Run Locally
```sh
./mvnw spring-boot:run  # For Linux/macOS
mvnw spring-boot:run    # For Windows
```
#### Run with Docker
```sh
docker build -t navido-backend .
docker run -p 8080:8080 navido-backend
```

---

## 📦 Deployment
### 🚀 Deploy with Docker Compose
Create a `docker-compose.yml` file:
```yaml
version: '3.8'
services:
  backend:
    build: .
    ports:
      - "8080:8080"
```
Run:
```sh
docker-compose up -d
```

---



