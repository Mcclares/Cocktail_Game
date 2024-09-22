# Cocktail Guessing Game

![Описание изображения](https://github.com/Mcclares/Cocktail_Game/blob/main/CocktailGame.png)

This project is a cocktail guessing game built using **React** for the frontend and **Spring Boot** for the backend.

## Technology Stack:
- **Frontend**: React
- **Backend**: Spring Boot
- **API**: [The CocktailDB API](https://www.thecocktaildb.com/)

## Installation and Running

### Backend (Spring Boot):
1. Navigate to the backend directory:
   ```bash
   cd Logic_cocktail_game

## Build and run the project: For Gradle:
  ```bash
  gradle wrapper
   ```
 
 ```bash
  ./gradlew bootRun
   ```

### Frontend (React):
***This step you do not need to do it, because have already built and add static files in java project. Just run java project.***
## Navigate to the frontend directory:
   ```bash
   cd frontend
   ```
## Install dependencies:

   ```bash
   npm install
   ```
## Build the project:
   ```bash
   npm run build
   ```
After building, copy the generated files from the build folder to the backend/src/main/resources/static folder for deployment along with the backend.

### Preparing for Download and Deployment
 ## Frontend:
 - Organize the files into separate frontend and backend folders so it’s clear where the React application is and where Spring Boot is.
Add instructions for users on how to build the frontend (React) using npm run build.
 ## Backend:
 - Ensure that all dependencies for the Spring Boot project are correctly specified in pom.xml or build.gradle.
If you are including static files, make sure that after building the React app, the files are placed in the correct directory for Spring Boot (src/main/resources/static).
