# 🚀 https://navido.tech Realtime Route Optimizer

This is the **Spring Boot Backend** for the **Navido** application. It provides realtime optimized routes based on traffic metadata, input locations and interacts with a React frontend.It processes location-based requests and returns step-by-step navigation details, estimated travel time, and total distance. The backend seamlessly integrates with a React frontend and supports containerized deployment using Docker. 

### Note:
- Currently supports Indian addresses only.
- The backend can be modified to get the routes in three different languages, i.e,  ENGLISH, HINDI & KANNADA. However based on 90% of the users and reviews, it's been set to ENGLISH internally, as maximum of the users didn't want too many input fields, and considered single language to be convenient.


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

## Accessing the Application

### Option 1.

#### ✅ Application is live, and can be directly tested on https://navido.tech




---

### Option 2.

#### Backend api testing can be done on https://routeme.azurewebsites.net/maps
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
    "destination" : "Brookefield Mall Bengaluru",
    "mode" : "driving"
   }
- expected output:
- ```json
  {
    "totalDistance": 2093.0,
    "eta": "0 hours 9 minutes",
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
            "readable_duration": "0 hours 3 minutes"
        },
        {
            "instructions": "Go straight towards ITPB",
            "readable_distance": "0 km 148 metres",
            "maneuver": "continue",
            "readable_duration": "0 hours 1 minutes"
        },
        {
            "instructions": "Turn left onto Kundalahalli Main Road",
            "readable_distance": "1 km 221 metres",
            "maneuver": "turn-left",
            "readable_duration": "0 hours 5 minutes"
        },
        {
            "instructions": "Make a sharp left",
            "readable_distance": "0 km 41 metres",
            "maneuver": "turn-sharp-left",
            "readable_duration": "0 hours 1 minutes"
        },
        {
            "instructions": "You have arrived at your destination",
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



