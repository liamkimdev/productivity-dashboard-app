# Use an official Node.js runtime as a parent image
FROM node:14-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install the dependencies
RUN npm install

# Copy the rest of the application source code to the container
COPY . .

# Set the environment variables for the application
ENV REACT_APP_API_URL=http://localhost:8080/api

# Build the application for production
RUN npm run build

# Set the command to start the application
CMD ["npm", "start"]